package org.example.commands;

import org.example.FactoryMethod.IRecipe;
import org.example.StrategyPattern.AllergyStrategy;
import org.example.builders.RecipeBuilder;
import org.example.objects.GlobalDescription;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class FilterRecipeByAllergy implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();
    private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public FilterRecipeByAllergy(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        this.iRecipes = recipeMenu.getIRecipes();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        if(!recipes.isEmpty()) {
            String allergy = inputGetter.getStringInput("Which allergy do you want to avoid in the recipes?");
            AllergyStrategy allergyFilter = new AllergyStrategy(recipeMenu);
            allergyFilter.filterByFoodPreference(allergy);
            System.out.println("Filter done.");
        }
        else {
            System.out.println(GlobalDescription.noRecipesToFilter);
        }
    }
}
