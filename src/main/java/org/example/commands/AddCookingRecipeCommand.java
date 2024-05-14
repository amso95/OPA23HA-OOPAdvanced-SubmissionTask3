package org.example.commands;

import org.example.FactoryPattern.CookingRecipeFactory;
import org.example.FactoryPattern.IRecipe;
import org.example.FactoryPattern.RecipeFactory;
import org.example.objects.DatabaseConnection;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class AddCookingRecipeCommand implements ICommand{
    Scanner scanner;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public AddCookingRecipeCommand(RecipeMenu recipeMenu) {
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.scanner = recipeMenu.getScanner();
    }

    @Override
    public void runCommand() {
        DatabaseConnection messageSender = new DatabaseConnection();
        RecipeFactory cookingRecipeFactory = new CookingRecipeFactory(recipeMenu);
        int instructionId = messageSender.getNextIdRequest("instruction");
        int ingredientId = messageSender.getNextIdRequest("ingredient");
        int nextId = messageSender.getNextIdRequest("recipe");
        IRecipe cookingRecipe = cookingRecipeFactory.createRecipe(nextId, instructionId, ingredientId);
        recipes.add(cookingRecipe.getRecipe());
    }
}
