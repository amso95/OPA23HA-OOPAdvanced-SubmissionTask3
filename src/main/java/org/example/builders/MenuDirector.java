package org.example.builders;

import org.example.commands.*;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuDirector {

    public RecipeMenu recipeMenu(MenuBuilder builder, RecipeMenu recipeMenu){
        /* Create a finished menu. */
        String message = "What do you want to do?";
        builder.setOptions("1. Add new cooking recipe.");
        builder.setOptions("2. Add new baking recipe.");
        builder.setOptions("3. Edit a recipe.");
        builder.setOptions("4. Delete a recipe.");
        builder.setOptions("5. Show all recipe.");
        builder.setOptions("6. Filter recipe by allergy.");
        builder.setOptions("7. Filter recipe by ingredient.");
        builder.setOptions("8. Show full recipe.");
        builder.setOptions("0. Quit program.");
        builder.setMessage(message);
        builder.setCommands("1", new AddCookingRecipeCommand(recipeMenu));
        builder.setCommands("2", new AddBakingRecipeCommand(recipeMenu));
        builder.setCommands("3", new EditRecipeCommand(recipeMenu));
        builder.setCommands("4", new DeleteRecipeCommand(recipeMenu));
        builder.setCommands("5", new ShowAllRecipesCommand(recipeMenu));
        builder.setCommands("6", new FilterRecipeByAllergy(recipeMenu));
        builder.setCommands("7", new FilterRecipeContainIngredient(recipeMenu));
        builder.setCommands("8", new ShowFullRecipeCommand(recipeMenu));
        builder.setCommands("0", new QuitProgramCommand(recipeMenu));
        return recipeMenu;
    }
}
