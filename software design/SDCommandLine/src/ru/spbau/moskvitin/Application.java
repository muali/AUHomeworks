package ru.spbau.moskvitin;

import java.util.HashMap;
import java.util.Scanner;

public class Application {

    void registerCommand(CommandInterpeter interpeter) {
        if (interpeter == null) {
            return;
        }
        if (interpeters.get(interpeter.getCommand()) != null) {
            throw new IllegalArgumentException("Interpreter for this command already exists");
        }
        interpeters.put(interpeter.getCommand(), interpeter);
    }

    public void executionLoop() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] splitted = command.split("//s+");
            if (splitted.length != 0) {
                CommandInterpeter interpeter = interpeters.get(splitted[0]);
                if (interpeter == null) {
                    System.err.println("Error: command not found");
                    continue;
                }
                interpeter.interprete(splitted);
            }
        }
    }

    public static void main(String[] args) {
	    Application application = new Application();
        application.registerCommand(new Cat());
        application.registerCommand(new Exit());
        application.registerCommand(new PrintWorkingDirectory());
        application.registerCommand(new WordCount());
        application.executionLoop();
    }

    private HashMap<String, CommandInterpeter> interpeters = new HashMap<>();
}
