package ru.job4j;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        final int PARAM = 0;
        final int VALUE = 1;
        if (args.length < 1) {
            throw new IllegalArgumentException("Error arguments. Argument should be 'key=value'");
        }
        for (String string : args) {
            String[] pair = string.split("=");
            if (pair[PARAM].length() < 1 || pair.length != 2) {
                throw new IllegalArgumentException("Error arguments. Argument should be 'key=value'");
            }
            values.put(pair[PARAM].substring(1), pair[VALUE]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
