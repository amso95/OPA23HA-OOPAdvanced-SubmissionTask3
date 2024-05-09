package org.example.commands;

import org.example.FactoryPattern.IRecipe;
import org.example.StrategyPattern.AllergyStrategy;
import org.example.objects.GlobalDescription;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class FilterRecipeByAllergy implements ICommand{
    Scanner scanner;
    InputGetter inputGetter = new InputGetter();
    private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public FilterRecipeByAllergy(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.scanner = recipeMenu.getScanner();
        this.iRecipes = recipeMenu.getIRecipes();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        // Check if there is any recipes in the list
        if(!recipes.isEmpty()) {
            String allergy = inputGetter.getStringInput("Which allergy do you want to avoid in the recipes?");
            AllergyStrategy allergyFilter = new AllergyStrategy(recipeMenu);
            allergyFilter.filterByAllergy(allergy);
            // Inform user.
            System.out.println("Filter done.");
        }
        else {
            // Inform user.
            System.out.println(GlobalDescription.noRecipesToFilter);
        }
    }
}
