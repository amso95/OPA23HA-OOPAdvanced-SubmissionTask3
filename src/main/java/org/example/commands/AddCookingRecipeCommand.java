package org.example.commands;

import org.example.FactoryPattern.CookingRecipeFactory;
import org.example.FactoryPattern.IRecipe;
import org.example.FactoryPattern.RecipeFactory;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class AddCookingRecipeCommand implements ICommand{
    Scanner scanner;
    //RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();

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
        RecipeFactory cookingRecipeFactory = new CookingRecipeFactory(recipeMenu);
        int instructionId = inputGetter.getInstructionId(recipes);
        int ingredientId = inputGetter.getIngredientId(recipes);
        /* Is this ok or is 1 a magic number? */
        int nextId = recipes.size() + 1;
        IRecipe cookingRecipe = cookingRecipeFactory.createRecipe(nextId, instructionId, ingredientId);
        recipes.add(cookingRecipe.getRecipe());
    }
}
