package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String num : text.toString().split(System.lineSeparator())) {
                if (Integer.parseInt(num) % 2 == 0) {
                    System.out.println(num + "  - четное число");
                } else {
                    System.out.println(num + " - нечетное число");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
