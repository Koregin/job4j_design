package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            StringBuilder sb = new StringBuilder();
            boolean unavailable = false;
            while ((line = read.readLine()) != null) {
                String[] pair = line.split("\\s+");
                if (!unavailable && (pair[0].equals("400") || pair[0].equals("500"))) {
                    unavailable = true;
                    sb.append(pair[1]).append(";");
                }
                if (unavailable && (!pair[0].equals("400") && !pair[0].equals("500"))) {
                    unavailable = false;
                    sb.append(pair[1]).append(";");
                    out.println(sb);
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
