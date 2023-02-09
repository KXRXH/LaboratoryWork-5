package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.annotations.Generated;
import itmo.kxrxh.lab5.utils.annotations.Length;
import itmo.kxrxh.lab5.utils.annotations.NonNull;
import itmo.kxrxh.lab5.utils.annotations.Value;
import itmo.kxrxh.lab5.utils.generators.IdGenerator;
import itmo.kxrxh.lab5.utils.generators.Time;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Add command.
 *
 * @author kxrxh
 */
public final class AddCommand extends CollectionDependent {
    /**
     * Instantiates a new Collection dependent.
     *
     * @param collectionManager Collection manager
     */
    private final Scanner scanner = new Scanner(System.in);
    private static final String buildersPath = "itmo.kxrxh.lab5.types.builders";

    public AddCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        Product product = readObject(Product.class);
        if (product != null) {
            getCollectionManager().add(product);
        }
    }

    private <T> T readObject(Class<T> objectType) {
        Class<?> builderClass;
        try {
            builderClass = Class.forName("%s.%sBuilder".formatted(buildersPath, objectType.getSimpleName()));
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find builder for class: " + objectType.getSimpleName());
            return null;
        }
        Builder object;
        try {
            object = (Builder) builderClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            System.out.println("Can't create builder");
            return null;
        }
        System.out.println(objectType.getSimpleName());
        for (Field field : builderClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().isEnum()) {
                setValueToField(field, object, stringToEnum(field, scanner.nextLine()));
                continue;
            }
            if (field.isAnnotationPresent(Generated.class)) {
                setValueToField(field, object, generateValueByType(field));
                continue;
            }
            // if field type is built-in type or String then read it
            switch (field.getType().getSimpleName()) {
                case "Integer", "int" -> {
                    System.out.println(field.getName() + " (int): ");
                    while (true) {
                        System.out.print(">: ");
                        Integer value = scanner.nextInt();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Long", "long" -> {
                    System.out.println(field.getName() + " (long): ");
                    while (true) {
                        System.out.print(">: ");
                        Long value = scanner.nextLong();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Double", "double" -> {
                    System.out.println(field.getName() + " (double): ");
                    while (true) {
                        System.out.print(">: ");
                        Double value = scanner.nextDouble();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Float", "float" -> {
                    System.out.println(field.getName() + " (float): ");
                    while (true) {
                        System.out.print(">: ");
                        Float value = scanner.nextFloat();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "String" -> {
                    System.out.println(field.getName() + " (String): ");
                    while (true) {
                        System.out.print(">: ");
                        String value = scanner.nextLine();
                        if (checkString(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }

                }
                default -> {
                    // if field type is not built-in type then read it recursively
                    try {
                        field.set(object, readObject(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (T) (object != null ? object.build() : null);
    }

    private <T extends Number> boolean checkNumber(Field field, T value) {
        Value valueAnnotation = null;
        if (field.isAnnotationPresent(Value.class)) {
            valueAnnotation = field.getAnnotation(Value.class);
        }
        if (valueAnnotation != null) {
            if (value.doubleValue() <= valueAnnotation.min()) {
                System.out.printf("Value must be greater than " + valueAnnotation.min());
                return false;
            }
            System.out.println(value.doubleValue());
            System.out.println(valueAnnotation.max());
            if (value.doubleValue() >= valueAnnotation.max()) {
                System.out.printf("Value must be less than " + valueAnnotation.max());
                return false;
            }
        }
        return true;
    }

    boolean checkString(Field field, String value) {
        if (field.isAnnotationPresent(NonNull.class) && value.isEmpty()) {
            System.out.println("Field can't be null");
            return false;
        }
        if (field.isAnnotationPresent(Length.class)) {
            Length lengthAnnotation = field.getAnnotation(Length.class);
            if (value.length() < lengthAnnotation.min()) {
                System.out.printf("Length must be greater than %d\n", lengthAnnotation.min() - 1);
                return false;
            }
            if (value.length() > lengthAnnotation.max()) {
                System.out.printf("Length must be less than %d\n", lengthAnnotation.max() + 1);
                return false;
            }
        }
        return true;
    }

    private <T> T generateValueByType(Field field) {
        return (T) switch (field.getType().getSimpleName()) {
            case "Integer", "int" -> IdGenerator.generateIntId();
            case "Long", "long" -> IdGenerator.generateLongId();
            case "LocalDateTime" -> Time.getTime();
            default -> null;
        };
    }

    private void setValueToField(Field field, Object object, Object value) {
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            System.out.println("Can't set value to field");
        } catch (InputMismatchException e) {
            System.out.println("Wrong input" + e.getMessage());
        }
    }

    private <T> T stringToEnum(Field field, String value) {
        return (T) Enum.valueOf((Class<Enum>) field.getType(), value);
    }
}
