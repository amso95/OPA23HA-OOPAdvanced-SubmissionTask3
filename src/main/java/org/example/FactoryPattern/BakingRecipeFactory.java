package org.example.FactoryPattern;

import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class BakingRecipeFactory extends RecipeFactory{

    private ArrayList<Recipe> recipes;
    private Recipe recipe;

    public BakingRecipeFactory(RecipeMenu recipeMenu){
        recipes = recipeMenu.getRecipes();
    }

    @Override
    public IRecipe makeRecipe() {
        BakingRecipe bakingRecipe = new BakingRecipe() ;
        recipe = bakingRecipe.getRecipe();
        return bakingRecipe;
    }

    @Override
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
