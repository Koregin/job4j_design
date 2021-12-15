package ru.job4j.io.find;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    @Override
    public String toString() {
        return "ArgsName{"
                + "values=" + values
                + '}';
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        final int PARAM = 0;
        final int VALUE = 1;
        if (args.length < 1) {
            throw new IllegalArgumentException("Error arguments. Argument should be '-key=value'");
        }
        for (String string : args) {
            String[] pair = new String[2];
            if (!checkParams(string)) {
                throw new IllegalArgumentException("Error arguments. Argument should be '-key=value'");
            }
            pair = string.split("=");
            if (pair.length != 2 || pair[PARAM].length() < 2) {
                throw new IllegalArgumentException("Error arguments. Argument should be '-key=value'");
            }
            values.put(pair[PARAM].substring(1), pair[VALUE]);
        }
    }

    private boolean checkParams(String params) {
        return params.startsWith("-") && !params.endsWith("=") && params.contains("=");
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
