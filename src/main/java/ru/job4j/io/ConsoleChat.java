package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botAnswers = readPhrases();
        String userAnswer = "";
        boolean stopAction = false;
        List<String> forLog = new ArrayList<>();
        try (BufferedReader bufRead = new BufferedReader(
                new InputStreamReader(System.in))) {
            System.out.println("Начинаем чат:");
            while (!OUT.equals(userAnswer)) {
                userAnswer = bufRead.readLine();
                forLog.add(userAnswer);
                if (STOP.equals(userAnswer)) {
                    stopAction = true;
                } else if (CONTINUE.equals(userAnswer)) {
                    stopAction = false;
                }
                if (!stopAction && !OUT.equals(userAnswer)) {
                    String botAnswer = botAnswers.get((int) (Math.random() * botAnswers.size()));
                    System.out.println(botAnswer);
                    forLog.add(botAnswer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        saveLog(forLog);
        System.out.println("До свидания!");
    }

    private List<String> readPhrases() {
        List<String> phrasesList = new ArrayList<>();
        String phrase = "";
        try (BufferedReader botAns =
                new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            while ((phrase = botAns.readLine()) != null) {
                phrasesList.add(phrase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phrasesList;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bufLogs =
                new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            for (String line : log) {
                bufLogs.write(line);
                bufLogs.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/consoleChat.log", "./data/botAnswers.txt");
        cc.run();
    }
}
