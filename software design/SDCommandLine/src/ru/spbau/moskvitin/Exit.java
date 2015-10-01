package ru.spbau.moskvitin;

/**
 * Created by Muali on 08.09.2015.
 */
public class Exit implements CommandInterpeter {
    @Override
    public void interprete(String[] args) {
        System.exit(0);
    }

    @Override
    public String getCommand() {
        return "exit";
    }
}
