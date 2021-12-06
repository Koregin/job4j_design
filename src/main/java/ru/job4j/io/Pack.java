package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Pack {
    public static void main(String[] args) throws IOException {
        ArgsName zipArgs = ArgsName.of(args);
        if (zipArgs.get("o") == null  || zipArgs.get("d") == null) {
            throw new IllegalArgumentException("Not correct arguments. Usage: java -jar pack.jar -d=SOURCE_DIR -e=FILTER_EXT -o=TARGET_ZIP");
        }
        File targetFile = new File(zipArgs.get("o"));
        Path sourceDir = Paths.get(zipArgs.get("d"));
        if (!Files.exists(sourceDir)) {
            throw new IllegalArgumentException("The specified directory doesn't exist");
        }
        Path filterExt = zipArgs.get("e") != null ? Paths.get(zipArgs.get("e").substring(zipArgs.get("e").lastIndexOf(".") + 1)) : Paths.get("");
        List<Path> pathsForZip =  Search.search(sourceDir, p -> !p.toFile().getName().endsWith("." + filterExt));
        List<File> filesForZip = pathsForZip.stream().map(Path::toFile).collect(Collectors.toList());
        Zip.packFiles(filesForZip, targetFile);
    }
}
