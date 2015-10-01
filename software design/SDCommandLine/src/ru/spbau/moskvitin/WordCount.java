package ru.spbau.moskvitin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Muali on 08.09.2015.
 */
public class WordCount implements CommandInterpeter {

    @Override
    public void interprete(String[] args) {
        if (args != null && args.length >= 2) {
            long lineCount = 0;
            long wordCount = 0;
            long byteCount = 0;
            try (Scanner scanner = new Scanner(new FileInputStream(args[1]))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("[\\W]");
                    lineCount++;
                    wordCount += words.length;
                }
                File file = new File(args[1]);
                byteCount = file.length();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found: " + args[1]);
                return;
            }
            System.out.println(String.format("%d %d %d", lineCount, wordCount, byteCount));
        } else {
            System.out.println("0 0 0");
        }
    }

    @Override
    public String getCommand() {
        return "wc";
    }
}
