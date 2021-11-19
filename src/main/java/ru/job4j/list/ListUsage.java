package ru.job4j.list;

import java.util.*;
import java.util.List;

public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");
        rsl.sort(Comparator.reverseOrder());
        for (String s : rsl) {
            System.out.println(s);
        }
        List<String> list = rsl.subList(1, 2);
        for (String s : list) {
            System.out.println("Текущий элемент: " + s);
        }
    }
}
