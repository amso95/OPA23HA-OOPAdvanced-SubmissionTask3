package org.example.FactoryMethod;

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
        /*RecipeBuilder recipeBuilder = new RecipeBuilder();
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ArrayList<Instruction> instructions = new ArrayList<>();
        ingredients.add(new Ingredient("Egg", "2", ""));
        ingredients.add(new Ingredient("Milk", "2 dl", ""));
        ingredients.add(new Ingredient("Flour", "4 dl", ""));
        ingredients.add(new Ingredient("Cacao", "4 tbsp", ""));
        instructions.add(new Instruction( "Turn oven on to 175 degrees."));
        instructions.add(new Instruction( "Mix all indigents together."));
        instructions.add(new Instruction( "Add batter to baking tin."));
        instructions.add(new Instruction("Put in oven and set a timer for 15 min."));
        Recipe recipe = recipeBuilder.setName("Muffins").setIngredients(ingredients).setInstructions(instructions).build();
        recipe.printRecipe();*/
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
        MessageSender messageSender = new MessageSender();
        messageSender.postRecipe(bakingRecipe);
        System.out.println("Baking recipe '" + recipeName + "' is added.");
    }
    @Override
    public void printRecipe() {
        bakingRecipe.printRecipe();
    }

    @Override
    public void printRecipeWithId() {
        bakingRecipe.printRecipeWithId();
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
