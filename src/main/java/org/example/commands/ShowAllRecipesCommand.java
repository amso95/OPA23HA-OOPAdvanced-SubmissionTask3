package org.example.commands;

import org.example.objects.GlobalDescription;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class ShowAllRecipesCommand implements ICommand{
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public ShowAllRecipesCommand(RecipeMenu recipeMenu) {
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
    }

    @Override
    public void runCommand() {
        System.out.println(GlobalDescription.printHeader);
        // Check if there is any recipes in the list
        if(!recipes.isEmpty()) {
            for (Recipe recipe : recipes) {
                System.out.println("Name: " + recipe.getName());
            }
        }
        else {
            // Inform user.
            System.out.println(GlobalDescription.noRecipesToPrint);
        }
    }
}
