package ru.spbau.moskvitin;

import java.nio.file.Paths;

/**
 * Created by Muali on 08.09.2015.
 */
public class PrintWorkingDirectory implements CommandInterpeter {
    @Override
    public void interprete(String[] args) {
        String wd = Paths.get("").toAbsolutePath().toString();
        System.out.println(wd);
    }

    @Override
    public String getCommand() {
        return "pwd";
    }
}
