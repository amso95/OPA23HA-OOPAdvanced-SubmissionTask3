package org.example.objects;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Recipe{
    private int id;
    private String name;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Instruction> instructions;
    private String creator;
    private String bakingOrCooking;
    private int idCounter = 1;

    public Recipe(String name, ArrayList<Ingredient> ingredients, ArrayList<Instruction> instructions, String creator, String bakingOrCooking) {
        this.id = idCounter;
        idCounter++;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.creator = creator;
        this.bakingOrCooking = bakingOrCooking;
    }
    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Instruction> instructions, String creator, String bakingOrCooking) {
        if(id >= idCounter) {
            idCounter = id + 1;
        }
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.creator = creator;
        this.bakingOrCooking = bakingOrCooking;
    }

    public void printRecipe(){
        String stringToPrint = getStringToPrint("");
        System.out.println(stringToPrint);
    }
    private String getStringToPrint(String stringToPrint){
        stringToPrint += "Name: " + name + ", recipe by: " + creator + "\nIngredients:\n";
        for (Ingredient ingredient: ingredients){
            stringToPrint += ingredient.getName() + ", amount: " + ingredient.getAmount() +"\n";
        }
        stringToPrint += "Instructions:\n";
        for (Instruction instruction: instructions){
            stringToPrint += instruction.getDescription() + "\n";
        }
        return stringToPrint;
    }
    public void printRecipeWithId(){
        String stringToPrint = "";
        stringToPrint += getStringToPrint("ID: " + id + ", ");
        System.out.println(stringToPrint);
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getBakingOrCooking() {
        return bakingOrCooking;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
