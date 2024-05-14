package org.example.FactoryPattern;

import org.example.objects.*;
import org.example.builders.RecipeBuilder;

import java.util.ArrayList;

public class CookingRecipe implements IRecipe{
    InputGetter inputGetter = new InputGetter();
    private Recipe cookingRecipe;
    private ArrayList<Recipe> cookingRecipes = new ArrayList<>();
    private String bakingOrCooking = GlobalDescription.cookingRecipeMark;
    @Override
    public void prepare(int id, int instructionId, int ingredientId) {
        /* Implement code that will fill in what's needed to make a cooking recipe. */
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
        cookingRecipe = recipeBuilder.setId(id).setName(recipeName).setIngredients(ingredients).setInstructions(instructions).setCreator(recipeCreator).setBakingOrCooking(bakingOrCooking).build();
        cookingRecipes.add(cookingRecipe);
        // Add recipe to database.
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.recipeToDatabase(cookingRecipe, "create", "POST");
        // Inform user.
        System.out.println("Cooking recipe '" + recipeName + "' is added.");
    }

    @Override
    public ArrayList<Ingredient> getIngredients() {
        return cookingRecipe.getIngredients();
    }

    @Override
    public Recipe getRecipe() {
        return cookingRecipe;
    }

    @Override
    public String getName() {
        return cookingRecipe.getName();
    }

    public Recipe getCookingRecipe() {
        return cookingRecipe;
    }

    public ArrayList<Recipe> getCookingRecipes() {
        return cookingRecipes;
    }
}
