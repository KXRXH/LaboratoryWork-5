package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.string.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

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
        this.item_name = item_name.toLowerCase();
    }

    /**
     * Reads next line from file
     *
     * @return next line
     */
    public String readLine() {
        return scanner.nextLine();
    }

    /**
     * Checks if file has next line
     *
     * @return true if file has next line
     */
    public boolean hasNextLine() {
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
            } else {
                // if tags contains tag product, set value to field
                if (tags.contains(item_name)) {
                    setValueToField(item, split[0], split[1]);
                }
            }
        }
        return item;
    }

    /**
     * Set value to field
     *
     * @param item       object to set value
     * @param field_name field name
     * @param value      value to set
     * @param <T>        type of value
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    protected <T> void setValueToField(Object item, String field_name, T value) {
        if (item instanceof Collection<?>) {
            // Unsafe cast, but it's ok with right xml file
            ((Collection<T>) item).add(value);
            return;
        }
        // Get field by name. If field not found, return null
        Field field = Arrays.stream(item.getClass().getDeclaredFields()).filter(f -> f.getName().equals(field_name)).findFirst().orElse(null);
        if (Objects.isNull(field)) {
            System.out.printf("Field %s not found%n", field_name);
            return;
        }
        field.setAccessible(true);
        Class<?> type = field.getType();
        // Get value of generic enum type
        if (type.isEnum()) {
            try {
                field.set(item, Enum.valueOf((Class<Enum>) type, (String) value));
            } catch (IllegalAccessException e) {
                System.out.printf("Error while parsing enum. Check if value is enum or enum has this value: %s%n", value);
            }
            return;
        }
        // Set value to field.
        switch (type.getSimpleName()) {
            case "LocalDateTime" -> {
                try {
                    field.set(item, LocalDateTime.parse((String) value));
                } catch (IllegalAccessException e) {
                    System.out.println("Error while parsing LocalDateTime. Check if value is LocalDateTime");
                }
            }
            case "Integer", "int" -> {
                try {
                    field.set(item, Integer.parseInt((String) value));
                } catch (IllegalAccessException e) {
                    System.out.println("Error while parsing Integer. Check if value is integer");
                }
            }
            case "Long", "long" -> {
                try {
                    field.set(item, Long.parseLong((String) value));
                } catch (IllegalAccessException e) {
                    System.out.println("Error while parsing Long. Check if value is long");
                }
            }
            case "Float", "float" -> {
                try {
                    field.set(item, Float.parseFloat((String) value));
                } catch (IllegalAccessException e) {
                    System.out.println("Error while parsing Float. Check if value is float");
                }
            }
            case "Double", "double" -> {
                try {
                    field.set(item, Double.parseDouble((String) value));
                } catch (IllegalAccessException e) {
                    System.out.println("Error while parsing Double. Check if value is double");
                }
            }
            default -> {
                // Default case is String and other classes
                try {
                    field.set(item, value);
                } catch (IllegalAccessException e) {
                    System.out.printf("Error while parsing %s. Check if value is %s%n", type.getSimpleName(), type.getSimpleName());
                }
            }
        }
    }
}

