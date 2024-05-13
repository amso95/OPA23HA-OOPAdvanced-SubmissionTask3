package org.example.commands;

import org.example.FactoryPattern.BakingRecipeFactory;
import org.example.FactoryPattern.IRecipe;
import org.example.FactoryPattern.RecipeFactory;
import org.example.objects.InputGetter;
import org.example.objects.MessageSender;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class AddBakingRecipeCommand implements ICommand{
    Scanner scanner;
    InputGetter inputGetter = new InputGetter();
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public AddBakingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.scanner = recipeMenu.getScanner();
    }

    @Override
    public void runCommand() {
        MessageSender messageSender = new MessageSender();
        RecipeFactory bakingRecipeFactory = new BakingRecipeFactory(recipeMenu);
        int instructionId = messageSender.getNextInstructionIdRequest();
        int ingredientId = messageSender.getNextIngredientIdRequest();
        int nextId = messageSender.getNextRecipeIdRequest();
        IRecipe bakingRecipe = bakingRecipeFactory.createRecipe(nextId, instructionId, ingredientId);
        recipes.add(bakingRecipe.getRecipe());
    }
}
