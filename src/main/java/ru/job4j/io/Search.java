package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not correct arguments. Usage: java -jar START_DIR FILE_EXT");
        }
        Path start = Paths.get(args[0]);
        try {
            search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        } catch (NoSuchFileException exc) {
            System.out.println("Searching path isn't exist");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
