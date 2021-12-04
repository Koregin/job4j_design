package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, String> foundFiles = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty findFile = new FileProperty(Files.size(file), file.getFileName().toString());
        if (!foundFiles.containsKey(findFile)) {
            foundFiles.put(findFile, file.toFile().getAbsolutePath().toString());
        } else {
            System.out.println(foundFiles.get(findFile));
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
