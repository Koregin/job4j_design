package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        byte b = 25;
        short s = 30;
        int i = 25678;
        long l = 455L;
        float f = 3.14F;
        double d = 99.9;
        boolean bool = true;
        char ch = 'a';
        LOG.debug("Byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, boolean: {}, char: {}",
                b, s, i, l, f, d, bool, ch);
    }
}
