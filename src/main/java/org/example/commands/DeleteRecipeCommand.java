package org.example.commands;

import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteRecipeCommand implements ICommand{
    Scanner scanner;
    InputGetter inputGetter = new InputGetter();
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public DeleteRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.scanner = recipeMenu.getScanner();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        // Check if there is any recipes in the list
        if(!recipes.isEmpty()) {
            for (Recipe recipe : recipes) {
                System.out.println("ID: " + recipe.getId() + ", " + recipe.getName());
            }
            int idToDelete = inputGetter.getIntInput(GlobalDescription.recipeToDeleteWithID);
            try {
                for (int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).getId() == idToDelete) {
                        DatabaseConnection databaseConnection = new DatabaseConnection();
                        databaseConnection.deleteRecipe(recipes.get(i).getId());
                        recipes.remove(i);
                        // Inform user.
                        System.out.println(GlobalDescription.deleteSuccess);
                        return;
                    }
                }
                // Inform user.
                System.out.println(GlobalDescription.deleteNotFound);
            } catch (Exception e) {
                // Inform user.
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            // Inform user.
            System.out.println(GlobalDescription.noRecipesToDelete);
        }
    }
}
