package org.example.commands;

import org.example.FactoryMethod.CookingRecipe;
import org.example.FactoryMethod.IRecipe;
import org.example.builders.RecipeBuilder;
import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteRecipeCommand implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();

    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public DeleteRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        if(!recipes.isEmpty()) {
            for (Recipe recipe : recipes) {
                recipe.printRecipeWithId();
            }
            int idToDelete = inputGetter.getIntInput(GlobalDescription.recipeToDeleteWithID);
            try {
                for (int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).getId() == idToDelete) {
                        MessageSender messageSender = new MessageSender();
                        messageSender.deleteRecipe(recipes.get(i).getId());
                        recipes.remove(i);
                        System.out.println(GlobalDescription.deleteSuccess);
                    }
                }
            } catch (Exception e) {
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            System.out.println(GlobalDescription.noCookingRecipesToDelete);
        }
    }
}
