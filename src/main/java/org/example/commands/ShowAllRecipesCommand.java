package org.example.commands;

import org.example.FactoryMethod.IRecipe;
import org.example.objects.GlobalDescription;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class ShowAllRecipesCommand implements ICommand{
    private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public ShowAllRecipesCommand(RecipeMenu recipeMenu) {
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.iRecipes = recipeMenu.getIRecipes();
    }

    @Override
    public void runCommand() {
        System.out.println(GlobalDescription.printHeader);
        if(!recipes.isEmpty()) {
            for (Recipe recipe : recipes) {
                System.out.println(GlobalDescription.namePrint + recipe.getName());
            }
        }
        else {
            System.out.println(GlobalDescription.noRecipesToPrint);
        }
    }
}
