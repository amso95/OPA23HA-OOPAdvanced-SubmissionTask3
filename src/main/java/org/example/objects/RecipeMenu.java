package org.example.objects;

import org.example.FactoryMethod.IRecipe;
import org.example.builders.*;
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
    private RecipeBuilder recipeBuilder = new RecipeBuilder();
    private String notApplicableInputMessage = "Invalid input.";
    private String message;
    private ArrayList<IRecipe> iRecipes = new ArrayList<>();

    public RecipeMenu(){
        /*message = "What do you want to do?";
        this.options.add("1. Add new cooking recipe.");
        this.options.add("2. Show all recipe.");
        this.options.add("3. Delete a cooking recipe.");
        this.options.add("0. Quit program.");
        this.commands.put("1", new AddCookingRecipeCommand(this));
        this.commands.put("2", new ShowAllRecipesCommand(this));
        this.commands.put("3", new DeleteCookingRecipeCommand(this));
        this.commands.put("0", new QuitProgramCommand(this));*/
    }
    public RecipeMenu(HashMap<String, ICommand> commands, String message, ArrayList<String> options){
        this.commands = commands;
        this.message = message;
        this.options = options;
    }
    public void run(){
        while(running) {
            System.out.println("Welcome to your recipe organizer!");
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
                System.out.println(notApplicableInputMessage);
            }
        }
    }

    public void getRecipesFromDatabase(){
        MessageSender messageSender = new MessageSender();
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

    public boolean isRunning() {
        return running;
    }

    public HashMap<String, ICommand> getCommands() {
        return commands;
    }

    public void setCommands(HashMap<String, ICommand> commands) {
        this.commands = commands;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    /*public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }*/

    public RecipeBuilder getRecipeBuilder() {
        return recipeBuilder;
    }

    public void setRecipeBuilder(RecipeBuilder recipeBuilder) {
        this.recipeBuilder = recipeBuilder;
    }

    public String getNotApplicableInputMessage() {
        return notApplicableInputMessage;
    }

    public void setNotApplicableInputMessage(String notApplicableInputMessage) {
        this.notApplicableInputMessage = notApplicableInputMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<IRecipe> getIRecipes() {
        return iRecipes;
    }

    public void setIRecipes(ArrayList<IRecipe> iRecipes) {
        this.iRecipes = iRecipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
