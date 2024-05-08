package org.example.FactoryMethod;

import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class CookingRecipeFactory extends RecipeFactory{
    private ArrayList<Recipe> recipes;
    private Recipe recipe;

    public CookingRecipeFactory(RecipeMenu recipeMenu){
        recipes = recipeMenu.getRecipes();
    }
    @Override
    public IRecipe makeRecipe() {
        CookingRecipe cookingRecipe = new CookingRecipe() ;
        //recipes = cookingRecipe.getCookingRecipes();
        recipe = cookingRecipe.getRecipe();
        return cookingRecipe;
    }


    @Override
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
