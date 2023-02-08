package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.string.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.*;

import static itmo.kxrxh.lab5.utils.string.StringUtils.toCamelCase;

/**
 * Xml reader class.
 * <p>
 * Used for reading from xml file
 *
 * @author kxrxh
 * @see XMLCore
 * @see XMLHandler
 */
public class XmlReader extends XMLHandler {
    private final Scanner scanner;
    private final Class<?> collection_class;
    private final String builders_path;
    private final String item_name;

    private final Stack<String> tags = new Stack<>();

    protected XmlReader(XMLCore xmlCore, Class<?> collection_class, String item_name, String builders_path) throws FileNotFoundException {
        super(xmlCore);
        this.scanner = new Scanner(new File(xmlCore.fileName));
        this.collection_class = collection_class;
        this.builders_path = builders_path;
        this.item_name = item_name;
    }

    /**
     * Reads next line from file
     *
     * @return next line
     */
    protected String readLine() {
        return scanner.nextLine();
    }

    /**
     * Checks if file has next line
     *
     * @return true if file has next line
     */
    protected boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /*
     * Parse XML file to collection
     * @return parsed collection
     */
    public ModLinkedList parse() {
        // Skip XML header and root tag
        while (hasNextLine()) {
            String line = readLine();
            if (!line.contains("<?") && line.contains("<")) {
                break;
            }
        }
        return (ModLinkedList) parseItem(collection_class);
    }

    /**
     * Parses item from file
     * <p>
     * This method works with recursion. It parses item from file and returns it.
     * If item has inner items, it calls itself for each inner item.
     * <p>
     * Example:
     * {@code parseItem(SomeCollection.class);}
     *
     * @param clazz class of item
     * @param <T>   type of item
     * @return parsed item
     */
    public final <T> T parseItem(Class<T> clazz) {
        T item;
        try {
            item = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Can't create instance of class");
            return null;
        } catch (InvocationTargetException | NoSuchMethodException e) {
            System.out.printf("Class %s doesn't have default constructor%n", clazz.getSimpleName());
            return null;
        }
        while (hasNextLine()) {
            String line = readLine();
            // Skipping lines without tags
            if (!line.contains("<")) {
                continue;
            }
            String[] split = line.split("<")[1].split(">");
            // If tag is closing, return item
            if (split[0].contains("/")) {
                // If tag is closing, return item.
                // Also, it helps to skip inner tags of unknown classes
                if (!tags.isEmpty()) {
                    tags.pop();
                    return item;
                }
                continue;
            }
            if (split.length == 1) {
                try {
                    // If tag is inner, call parseItem for it
                    Class<?> tag_class = Class.forName("%s.%sBuilder".formatted(builders_path, StringUtils.capitalize(split[0])));
                    // If tag is inner, call parseItem for it. Also, it helps to skip inner tags of unknown classes. If tag is product, push it to stack
                    if (tags.contains(item_name) || Objects.equals(split[0], item_name)) {
                        tags.push(split[0]);
                        setValueToField(item, split[0].toLowerCase(), ((Builder) Objects.requireNonNull(parseItem(tag_class))).build());
                    }
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found. Skipping...");
                }
            } else if (tags.contains(item_name)) {
                setValueToField(item, split[0], split[1]);
            }
        }
        return item;
    }

    /**
     * Set value to field
     *
     * @param item       object to set value
     * @param fieldName field name
     * @param value      value to set
     * @param <T>        type of value
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected <T> void setValueToField(Object item, String fieldName, T value) {
        if (item instanceof Collection<?>) {
            ((Collection<T>) item).add(value);
            return;
        }
        Field field = findField(item, toCamelCase(fieldName));
        if (field == null) {
            System.out.println("Field " + fieldName + " not found");
            return;
        }
        field.setAccessible(true);
        if (field.getType().isEnum()) {
            setEnumValue(field, item, value);
        } else {
            setFieldValue(field, item, value);
        }
    }

    private Field findField(Object item, String fieldName) {
        return Arrays.stream(item.getClass().getDeclaredFields()).filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
    }

    private <T> void setEnumValue(Field field, Object item, T value) {
        try {
            field.set(item, Enum.valueOf((Class<Enum>) field.getType(), (String) value));
        } catch (IllegalAccessException e) {
            System.out.println("Error while parsing enum. Check if value is enum or enum has this value: " + value);
        }
    }

    private <T> void setFieldValue(Field field, Object item, T value) {
        try {
            Class<?> type = field.getType();
            switch (type.getSimpleName()) {
                case "LocalDateTime":
                    field.set(item, LocalDateTime.parse((String) value));
                    break;
                case "Integer":
                case "int":
                    field.set(item, Integer.parseInt((String) value));
                    break;
                case "Long":
                case "long":
                    field.set(item, Long.parseLong((String) value));
                    break;
                case "Float":
                case "float":
                    field.set(item, Float.parseFloat((String) value));
                    break;
                case "Double":
                case "double":
                    field.set(item, Double.parseDouble((String) value));
                    break;
                default:
                    field.set(item, value);
            }
        } catch (IllegalAccessException e) {
            System.out.println("Error while parsing " + field.getType().getSimpleName() + ". Check if value is " + field.getType().getSimpleName());
        }
    }
}

