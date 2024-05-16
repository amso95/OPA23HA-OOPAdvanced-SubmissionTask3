package org.example.commands;

import org.example.objects.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EditRecipeCommand implements ICommand{
    Scanner scanner;
    InputGetter inputGetter = new InputGetter();
    RecipeEditor recipeEditor = new RecipeEditor();
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public EditRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.scanner = recipeMenu.getScanner();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        // Check if there is any recipes in the list.
        if(!recipes.isEmpty()) {
            // Print the ID and name of the recipes.
            for (Recipe recipe : recipes) {
                System.out.println("ID: " + recipe.getId() + " Name: " + recipe.getName());
            }
            int idToEdit = inputGetter.getIntInput(GlobalDescription.recipeToEditWithID);
            try {
                for (int i = 0; i < recipes.size(); i++) {
                    // Find the recipe with the inputted ID and edit it.
                    if(recipes.get(i).getId() == idToEdit) {
                        Recipe recipeToEdit = recipes.get(i);
                        recipeEditor.editRecipe(recipeToEdit);
                        // Update recipe in database.
                        DatabaseConnection databaseConnection = new DatabaseConnection();
                        databaseConnection.recipeToDatabase(recipeToEdit, "edit", "PUT");
                        // Inform user.
                        System.out.println(GlobalDescription.editSuccess);
                        return;
                    }
                }
                // Inform user.
                System.out.println(GlobalDescription.editNotFound);
            } catch (Exception e) {
                // Inform user.
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            // Inform user.
            System.out.println(GlobalDescription.noRecipesToEdit);
        }
    }


}
