package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not correct arguments. Usage: java -jar -path=source.csv -delimiter=\";\" -out=stdout -filter=columns");
        }
        ArgsName argsCSV = ArgsName.of(args);
        if (argsCSV.get("path") != null && argsCSV.get("delimiter") != null
                && argsCSV.get("out") != null && argsCSV.get("filter") != null) {
            throw new IllegalArgumentException("Not correct arguments. Usage: java -jar -path=source.csv -delimiter=\";\" -out=stdout -filter=columns");
        }
        if (!Files.exists(Path.of(argsCSV.get("path")))) {
            throw new IllegalArgumentException("File not found");
        }
        if (argsCSV.get("delimiter").length() != 1) {
            throw new IllegalArgumentException("Delimiter should be not more than one character");
        }
        handle(argsCSV);
    }

    public static void handle(ArgsName argsName) throws Exception {
        String sourceCSV = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        PrintWriter printer = new PrintWriter(out);
        if ("stdout".equals(out)) {
            printer = new PrintWriter(System.out);
        }
        try (Scanner scanner = new Scanner(new FileReader(sourceCSV));
             BufferedWriter bufWrite = new BufferedWriter(printer)) {
            String readLine = scanner.nextLine();
            List<Boolean> headerAllowList = new ArrayList<>();
            List<String> filters = List.of(filter.split(","));
            for (String str : readLine.split(delimiter)) {
                if (filters.contains(str)) {
                    headerAllowList.add(true);
                } else {
                    headerAllowList.add(false);
                }
            }
            bufWrite.write(String.join(delimiter, filters));
            bufWrite.newLine();
            while (scanner.hasNextLine()) {
                Scanner scanRecord = new Scanner(scanner.nextLine()).useDelimiter(delimiter);
                List<String> record = new ArrayList<>();
                int index = 0;
                while (scanRecord.hasNext()) {
                    String value = scanRecord.next();
                    if (headerAllowList.get(index)) {
                        record.add(value);
                    }
                    index++;
                }
                bufWrite.write(String.join(delimiter, record));
                bufWrite.newLine();
            }
        }
    }
}
