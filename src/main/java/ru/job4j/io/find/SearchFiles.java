package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles extends SimpleFileVisitor<Path> {
    Predicate<Path> condition;
    List<Path> listPath = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            listPath.add(file);
        }
        return CONTINUE;
    }

    public SearchFiles(Predicate<Path> pathPredicate) {
        this.condition = pathPredicate;
    }

    public List<Path> getPaths() {
        return listPath;
    }
}
