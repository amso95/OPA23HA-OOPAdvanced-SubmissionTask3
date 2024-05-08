package org.example.objects;

import org.example.builders.IngredientBuilder;
import org.example.builders.InstructionBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class InputGetter {
    Scanner scanner = new Scanner(System.in);

    public String getStringInput(String message){
        while (true){
            System.out.println(message);
            String inputSting = scanner.nextLine();
            if(!inputSting.isBlank()){
                return inputSting;
            }
            System.out.println("Invalid input");
        }
    }
    public String getStringInputCanBeEmpty(String message){
        System.out.println(message);
        String inputSting = scanner.nextLine();
        return inputSting;
    }
    public int getIntInput(String message){
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("That's not a number.");
            }
        }
    }
    public ArrayList<Ingredient> addIngredientsToList(boolean continueToAdd, ArrayList<Ingredient> ingredients, int ingredientId){
        int idToSet = ingredientId;
        while(continueToAdd) {
            System.out.println(GlobalDescription.ingredientsHeader);
            String ingredientName = getStringInput("""
                    What is the name of the ingredient?
                    If you don't want to add more ingredients write:
                    'DONE' and press enter.""");
            if(ingredientName.equals("DONE")){
                continueToAdd = false;
            }
            else{
                String amount = getStringInput("What is the amount of " + ingredientName + " you need for the recipe?");
                String allergyType = getStringInputCanBeEmpty("Is " + ingredientName + " a type of allergy? If yes write which one.\nOtherwise write none or press enter to continue.");
                IngredientBuilder ingredientBuilder = new IngredientBuilder();
                ingredients.add(ingredientBuilder.setId(idToSet).setName(ingredientName).setAmount(amount).setAllergyType(allergyType).build());
                idToSet++;
                System.out.println("Ingredient is now added.");
                System.out.println();
            }
        }
        return ingredients;
    }
    public ArrayList<Instruction> addInstructionsToList(boolean continueToAdd, ArrayList<Instruction> instructions, int instructionId){
        int idToSet = instructionId;
        while(continueToAdd) {
            System.out.println(GlobalDescription.instructionsHeader);
            String description = getStringInput("""
                    What is the description for the instruction?
                    If you don't want to add more instructions write:
                    'DONE' and press enter.""");
            if(description.equals("DONE")){
                continueToAdd = false;
            }
            else{
                InstructionBuilder instructionBuilder = new InstructionBuilder();
                instructions.add(instructionBuilder.setId(idToSet).setDescription(description).build());
                idToSet++;
                System.out.println("Instruction is now added.\n");
            }
        }
        return instructions;
    }



}
