package org.example.objects;

import org.example.commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RecipeMenu {
    Scanner scanner = new Scanner(System.in);
    private boolean running = true;
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();
    private String message;

    public RecipeMenu(){
    }
    public RecipeMenu(HashMap<String, ICommand> commands, String message, ArrayList<String> options){
        this.commands = commands;
        this.message = message;
        this.options = options;
    }
    public void run(){
        while(running) {
            System.out.println(GlobalDescription.welcomeHeader);
            while (true) {
                System.out.println(message);
                for (String option : options) {
                    System.out.println(option);
                }
                String input = scanner.nextLine();
                if (commands.containsKey(input)) {
                    commands.get(input).runCommand();
                    break;
                }
                System.out.println(GlobalDescription.invalidInput);
            }
        }
    }

    public void getRecipesFromDatabase(){
        DatabaseConnection messageSender = new DatabaseConnection();
        recipes = messageSender.getRequest();
    }
    public void setRunning(boolean running) {
        this.running = running;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }

    public void setCommands(HashMap<String, ICommand> commands) {
        this.commands = commands;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
