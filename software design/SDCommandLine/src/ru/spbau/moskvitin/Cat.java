package ru.spbau.moskvitin;

import java.io.*;

/**
 * Created by Muali on 08.09.2015.
 */
public class Cat implements CommandInterpeter {

    @Override
    public void interprete(String[] args) {
        if (args != null && args.length >= 2) {
            try (InputStream is = new BufferedInputStream(new FileInputStream(args[1]))) {
                byte[] buffer = new byte[bufferSize];
                int readed;
                while ((readed = is.read(buffer)) != -1) {
                    System.out.write(buffer, 0, readed);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("File not found:" + args[1]);
            } catch (IOException ex) {
                System.err.println(ex.toString());
            }
        }
    }

    @Override
    public String getCommand() {
        return "cat";
    }

    private final int bufferSize = 1024;
}
