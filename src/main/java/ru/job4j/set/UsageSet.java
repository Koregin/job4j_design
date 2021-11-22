package ru.job4j.set;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UsageSet {
    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        strings.add("one");
        strings.add("two");
        strings.add("three");
        strings.add("two");
        strings.addAll(List.of("one", "four", "five"));
        strings.stream()
                .filter(s -> s.length() < 5)
                .forEach(System.out::println);
    }
}
