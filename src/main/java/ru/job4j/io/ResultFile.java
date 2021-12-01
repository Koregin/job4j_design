package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                ))) {
            for (int row = 1; row < 10; row++) {
                for (int col = 1; col < 10; col++) {
                    out.printf("%d%s", row * col, "|");
                }
                out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
