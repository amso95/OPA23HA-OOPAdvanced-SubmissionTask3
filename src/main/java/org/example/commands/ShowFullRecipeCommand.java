package org.example.commands;

import org.example.FactoryPattern.IRecipe;
import org.example.objects.GlobalDescription;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class ShowFullRecipeCommand implements ICommand{
    /* To have the same correct reference value. */
    private ArrayList<Recipe> recipes;
    private RecipeMenu recipeMenu;
    InputGetter inputGetter = new InputGetter();

    public ShowFullRecipeCommand(RecipeMenu recipeMenu) {
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
    }

    @Override
    public void runCommand() {
        System.out.println(GlobalDescription.printHeader);
        // Check if there is any recipes in the list
        if(!recipes.isEmpty()) {
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("Index: " + i + ", " + recipes.get(i).getName());
            }
            int indexChoice = inputGetter.getIntInput(GlobalDescription.fullVersionIndex);
            try{
                recipes.get(indexChoice).printRecipe();
            }catch (Exception e){
                // Inform user.
                System.out.println(GlobalDescription.invalidIndex);
            }
        }
        else {
            // Inform user.
            System.out.println(GlobalDescription.noRecipesToPrint);
        }
    }
}
