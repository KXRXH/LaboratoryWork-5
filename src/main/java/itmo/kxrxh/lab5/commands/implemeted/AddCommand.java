package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.builders.Builder;
import itmo.kxrxh.lab5.utils.annotations.Generated;
import itmo.kxrxh.lab5.utils.annotations.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.List;
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

    }

    private <T> T readObject(Class<T> objectType) {
        Class<?> builderClass;
        try {
            builderClass = Class.forName("%s.%sBuilder".formatted(buildersPath, objectType.getSimpleName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Builder object = null;
        try {
            object = (Builder) builderClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (Field field : builderClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NonNull.class)) {
                // TODO: check if field is null
                // Idea: add field class, which contains different flags (Now it is in SmartField.java)
            }
            if (field.isAnnotationPresent(Generated.class)) {
                // TODO: generate value
            }
            // if field type is built-in type or String then read it
            switch (field.getType().getSimpleName()) {
                case "Integer", "int" -> {
                    while (true) {
                        System.out.printf("Enter %s: ", field.getName());
                        try {
                            field.set(object, scanner.nextInt());
                            break;
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input");
                            scanner.nextLine();
                        }
                    }
                }
                case "Long", "long" -> {
                    System.out.printf("Enter %s: ", field.getName());
                    try {
                        field.set(object, scanner.nextLong());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                case "String" -> {
                    System.out.printf("Enter %s: ", field.getName());
                    try {
                        field.set(object, scanner.nextLine());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                case "Double", "double" -> {
                    System.out.printf("Enter %s: ", field.getName());
                    try {
                        field.set(object, scanner.nextDouble());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
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
}
