package org.example.FactoryPattern;

import org.example.objects.*;
import org.example.builders.RecipeBuilder;

import java.util.ArrayList;

public class BakingRecipe implements IRecipe{
    InputGetter inputGetter = new InputGetter();
    private Recipe bakingRecipe;
    private ArrayList<Recipe> bakingRecipes = new ArrayList<>();
    private String bakingOrCooking = GlobalDescription.bakingRecipeMark;
    @Override
    public void prepare(int id, int instructionId, int ingredientId) {
        /* Implement code that will fill in what's needed to make a baking recipe. */
        RecipeBuilder recipeBuilder = new RecipeBuilder();
        boolean addIngredients = true;
        boolean addInstructions = true;
        String recipeName = inputGetter.getStringInput("What is the name of the recipe?");
        String recipeCreator = inputGetter.getStringInput("What is the creators name?");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        ingredients = inputGetter.addIngredientsToList(addIngredients, ingredients, ingredientId);
        instructions = inputGetter.addInstructionsToList(addInstructions, instructions, instructionId);
        /* Add recipe with a recipe builder. */
        bakingRecipe = recipeBuilder.setId(id).setName(recipeName).setIngredients(ingredients).setInstructions(instructions).setCreator(recipeCreator).setBakingOrCooking(bakingOrCooking).build();
        bakingRecipes.add(bakingRecipe);
        // Add recipe to database.
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.recipeToDatabase(bakingRecipe, "create", "POST");
        // Inform user.
        System.out.println("Baking recipe '" + recipeName + "' is added.");
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        return bakingRecipe.getIngredients();
    }

    @Override
    public Recipe getRecipe() {
        return bakingRecipe;
    }

    @Override
    public String getName() {
        return bakingRecipe.getName();
    }

    public Recipe getBakingRecipe() {
        return bakingRecipe;
    }

    public ArrayList<Recipe> getBakingRecipes() {
        return bakingRecipes;
    }
}
