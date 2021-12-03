package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            StringBuilder sb = new StringBuilder();
            final int STATUS = 0;
            final int DATETIME = 1;
            boolean unavailable = false;
            while ((line = read.readLine()) != null) {
                String[] pair = line.split("\\s+");
                if (!unavailable && ("400".equals(pair[STATUS]) || "500".equals(pair[STATUS]))) {
                    unavailable = true;
                    sb.append(pair[DATETIME]).append(";");
                }
                if (unavailable && (!"400".equals(pair[STATUS]) && !"500".equals(pair[STATUS]))) {
                    unavailable = false;
                    sb.append(pair[DATETIME]).append(";");
                    out.println(sb);
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
