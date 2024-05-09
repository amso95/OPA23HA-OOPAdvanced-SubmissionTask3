package org.example;

import org.example.builders.*;
import org.example.objects.*;

public class Main {
    public static void main(String[] args) {
        // Create a RecipeMenu so all lists are created as well.
        RecipeMenu recipeMenu = new RecipeMenu();
        // Get recipes from database and add into respective list.
        recipeMenu.getRecipesFromDatabase();
        // Create a MenuBuilder to be able to create a menu.
        MenuBuilder menuBuilder = new MenuBuilder(recipeMenu);
        // Use setRecipeMenu to create an already implemented menu.
        recipeMenu = menuBuilder.setRecipeMenu(recipeMenu).build();
        // Run program.
        recipeMenu.run();
    }
}