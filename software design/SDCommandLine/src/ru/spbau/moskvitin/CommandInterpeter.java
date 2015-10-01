package ru.spbau.moskvitin;

/**
 * Created by Muali on 08.09.2015.
 */
public interface CommandInterpeter {
    void interprete(String[] args);
    String getCommand();
}
