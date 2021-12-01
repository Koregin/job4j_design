package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> log404 = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            log404 = in.lines()
                    .filter(s -> s.contains(" 404 "))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return log404;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
