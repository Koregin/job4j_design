package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length < 1) {
            exception();
        }
        for (String string : args) {
            String[] pair = new String[2];
            if (checkParams(string)) {
                 pair = string.split("=");
            } else {
                exception();
            }
            if (pair.length != 2) {
                exception();
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    private void exception() {
        throw new IllegalArgumentException("Error arguments. Argument should be '-key=value'");
    }

    private boolean checkParams(String params) {
        return params.startsWith("-") && !params.endsWith("=") && params.contains("=");
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
