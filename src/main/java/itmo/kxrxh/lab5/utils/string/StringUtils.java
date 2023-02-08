package itmo.kxrxh.lab5.utils.string;

public class StringUtils {
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String toSnakeCase(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String toCamelCase(String input) {
        StringBuilder builder = new StringBuilder();
        boolean shouldCapitalizeNext = false;
        for (var c : input.toCharArray()) {
            if (c == '_') {
                shouldCapitalizeNext = true;
            } else {
                if (shouldCapitalizeNext) {
                    builder.append(Character.toUpperCase(c));
                    shouldCapitalizeNext = false;
                } else {
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }

}