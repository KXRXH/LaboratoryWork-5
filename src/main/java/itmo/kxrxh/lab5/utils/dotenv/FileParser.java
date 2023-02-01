package itmo.kxrxh.lab5.utils.dotenv;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * FileParser class. Parses .env file.
 *
 * @author kxrxh
 */
public final class FileParser {
    /**
     * Parse map.
     *
     * @param path Path to .env file.
     * @return Map of environment variables. Key - variable name, value - variable value.
     */
    public static @NotNull Map<String, String> parse(String path) {
        File file = new File(path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("DotEnv: File not found");
        }
        Map<String, String> result = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("#")) {
                continue;
            }
            String[] split = line.split("=", 2);
            if (split.length != 2) {
                continue;
            }
            result.put(split[0], split[1]);
        }
        scanner.close();
        return result;
    }
}
