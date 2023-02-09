package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.Terminal.Colors;
import itmo.kxrxh.lab5.utils.annotations.Generated;
import itmo.kxrxh.lab5.utils.annotations.Length;
import itmo.kxrxh.lab5.utils.annotations.NonNull;
import itmo.kxrxh.lab5.utils.annotations.Value;
import itmo.kxrxh.lab5.utils.generators.IdGenerator;
import itmo.kxrxh.lab5.utils.generators.Time;
import itmo.kxrxh.lab5.utils.strings.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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

    @SuppressWarnings("unchecked")
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
        System.out.printf("%s%s%s%n", Colors.ANSI_RED, objectType.getSimpleName(), Colors.ANSI_RESET);
        for (Field field : builderClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().isEnum()) {
                System.out.printf("\u001B[35m%s (%s): %n\u001B[0m", StringUtils.capitalize(field.getName()), field.getType().getSimpleName());
                while (true) {
                    System.out.print(">: ");
                    String value = scanner.nextLine();
                    try {
                        setValueToField(field, object, stringToEnum(field, value));
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.printf("%sEnum value is not valid%n", Colors.ANSI_RED);
                        System.out.printf("%sEnum constants: %s%n" + Colors.ANSI_RESET, Colors.ANSI_RED, Arrays.toString(field.getType().getEnumConstants()));
                    }
                }
                continue;
            }
            if (field.isAnnotationPresent(Generated.class)) {
                setValueToField(field, object, generateValueByType(field));
                continue;
            }
            // if field type is built-in type or String then read it
            switch (field.getType().getSimpleName()) {
                case "Integer", "int" -> {
                    System.out.printf("\u001B[35m%s (int): %n\u001B[0m", StringUtils.capitalize(field.getName()));
                    while (true) {
                        System.out.print(">: ");
                        Integer value;
                        try {
                            value = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.printf("%sInput is not of type Integer%s%n", Colors.ANSI_RED, Colors.ANSI_RESET);
                            scanner.nextLine();
                            continue;
                        }
                        scanner.nextLine();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Long", "long" -> {
                    System.out.printf("\u001B[35m%s (long): %n\u001B[0m", StringUtils.capitalize(field.getName()));
                    while (true) {
                        System.out.print(">: ");
                        Long value = scanner.nextLong();
                        scanner.nextLine();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Double", "double" -> {
                    System.out.printf("\u001B[35m%s (double): %n\u001B[0m", StringUtils.capitalize(field.getName()));
                    while (true) {
                        System.out.print(">: ");
                        Double value = scanner.nextDouble();
                        scanner.nextLine();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "Float", "float" -> {
                    System.out.printf("\u001B[35m%s (float): %n\u001B[0m", StringUtils.capitalize(field.getName()));
                    while (true) {
                        System.out.print(">: ");
                        Float value = scanner.nextFloat();
                        scanner.nextLine();
                        if (checkNumber(field, value)) {
                            setValueToField(field, object, value);
                            break;
                        }
                    }
                }
                case "String" -> {
                    System.out.printf("\u001B[35m%s (String): %n\u001B[0m", StringUtils.capitalize(field.getName()));
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
        return (T) object.build();
    }

    private <T extends Number> boolean checkNumber(Field field, T value) {
        Value valueAnnotation = null;
        if (field.isAnnotationPresent(Value.class)) {
            valueAnnotation = field.getAnnotation(Value.class);
        }
        if (valueAnnotation != null) {
            if (value.doubleValue() <= valueAnnotation.min()) {
                System.out.printf("%sValue must be greater than %s%s".formatted(Colors.ANSI_RED, valueAnnotation.min(), Colors.ANSI_RESET));
                return false;
            }
            if (value.doubleValue() >= valueAnnotation.max()) {
                System.out.printf("%sValue must be less than %s%s".formatted(Colors.ANSI_RED, valueAnnotation.max(), Colors.ANSI_RESET));
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
                System.out.printf("\u001B[31mLength must be greater than %d\n\u001B[0m", lengthAnnotation.min() - 1);
                return false;
            }
            if (value.length() > lengthAnnotation.max()) {
                System.out.printf("\u001B[31mLength must be less than %d\n\u001B[0m", lengthAnnotation.max() + 1);
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
            System.out.println("\u001B[31mCan't set value to field\u001B[0m");
        } catch (InputMismatchException e) {
            System.out.printf("%sWrong input%s%s%n", Colors.ANSI_RED, e.getMessage(), Colors.ANSI_RESET);
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Enum<?> stringToEnum(Field field, String value) {
        return (Enum<?>) Enum.valueOf((Class<Enum>) field.getType(), value);

    }
}
