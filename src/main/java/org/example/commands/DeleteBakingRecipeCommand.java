package org.example.commands;

import org.example.FactoryMethod.BakingRecipe;
import org.example.FactoryMethod.CookingRecipe;
import org.example.FactoryMethod.IRecipe;
import org.example.builders.RecipeBuilder;
import org.example.objects.GlobalDescription;
import org.example.objects.InputGetter;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class DeleteBakingRecipeCommand implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();

    private ArrayList<IRecipe> iRecipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public DeleteBakingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        this.iRecipes = recipeMenu.getIRecipes();
    }
    @Override
    public void runCommand() {
        if(!iRecipes.isEmpty()) {
            for (IRecipe iRecipe : iRecipes) {
                if(iRecipe instanceof BakingRecipe) {
                    iRecipe.printRecipeWithId();
                }
            }
            int idToDelete = inputGetter.getIntInput(GlobalDescription.recipeToDeleteWithID);
            try {
                for (int i = 0; i < iRecipes.size(); i++) {
                    if(iRecipes.get(i) instanceof BakingRecipe
                        && ((BakingRecipe) iRecipes.get(i)).getBakingRecipe().getId() == idToDelete) {
                        iRecipes.remove(i);
                        System.out.println(GlobalDescription.deleteSuccess);
                    }
                }
            } catch (Exception e) {
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            System.out.println(GlobalDescription.noBakingRecipesToDelete);
        }
    }
}
