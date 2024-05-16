package org.example.commands;

import org.example.FactoryPattern.BakingRecipeFactory;
import org.example.FactoryPattern.IRecipe;
import org.example.FactoryPattern.RecipeFactory;
import org.example.objects.DatabaseConnection;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;
import java.util.ArrayList;
import java.util.Scanner;

public class AddBakingRecipeCommand implements ICommand{
    Scanner scanner;
    private ArrayList<Recipe> recipes;
    private RecipeMenu recipeMenu;

    public AddBakingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.scanner = recipeMenu.getScanner();
    }

    @Override
    public void runCommand() {
        DatabaseConnection messageSender = new DatabaseConnection();
        RecipeFactory bakingRecipeFactory = new BakingRecipeFactory(recipeMenu);
        // To get the next id for instruction.
        int instructionId = messageSender.getNextIdRequest("instruction");
        // To get the next id for ingredient.
        int ingredientId = messageSender.getNextIdRequest("ingredient");
        // To get the next id for recipe.
        int nextId = messageSender.getNextIdRequest("recipe");
        // Create the recipe using factory pattern.
        IRecipe bakingRecipe = bakingRecipeFactory.createRecipe(nextId, instructionId, ingredientId);
        recipes.add(bakingRecipe.getRecipe());
    }
}
