package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            StringBuilder sb = new StringBuilder();
            boolean unavailable = false;
            while ((line = read.readLine()) != null) {
                String[] pair = line.split(" ");
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

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Analizy().unavailable("./data/sourcelog1.log", "unavailable1.csv");
        new Analizy().unavailable("./data/sourcelog2.log", "unavailable2.csv");
    }
}
