package org.example.commands;

import org.example.FactoryMethod.IRecipe;
import org.example.objects.GlobalDescription;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;

public class ShowFullRecipeCommand implements ICommand{
    private ArrayList<IRecipe> iRecipes;
    /* To have the same correct reference value. */
    private ArrayList<Recipe> recipes;
    private RecipeMenu recipeMenu;
    InputGetter inputGetter = new InputGetter();

    public ShowFullRecipeCommand(RecipeMenu recipeMenu) {
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.iRecipes = recipeMenu.getIRecipes();
    }

    @Override
    public void runCommand() {
        System.out.println(GlobalDescription.printHeader);
        if(!recipes.isEmpty()) {
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println("Index: " + i + ", " + recipes.get(i).getName());
            }
            int indexChoice = inputGetter.getIntInput(GlobalDescription.fullVersionIndex);
            try{
                recipes.get(indexChoice).printRecipe();
            }catch (Exception e){
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            System.out.println(GlobalDescription.noRecipesToPrint);
        }
    }
}
