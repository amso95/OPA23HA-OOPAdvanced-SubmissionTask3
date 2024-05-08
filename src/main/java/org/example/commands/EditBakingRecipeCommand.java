package org.example.commands;

import org.example.FactoryMethod.BakingRecipe;
import org.example.FactoryMethod.IRecipe;
import org.example.builders.RecipeBuilder;
import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class EditBakingRecipeCommand implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();
    RecipeEditor recipeEditor = new RecipeEditor();

    private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public EditBakingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        this.iRecipes = recipeMenu.getIRecipes();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void runCommand() {
        if(!recipes.isEmpty()) {
            for (Recipe recipe : recipes) {
                if(recipe.getBakingOrCooking().equals(GlobalDescription.bakingRecipeMark)) {
                    recipe.printRecipeWithId();
                }
            }
            int idToEdit = inputGetter.getIntInput(GlobalDescription.recipeToEditWithID);
            try {
                for (int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).getBakingOrCooking().equals(GlobalDescription.bakingRecipeMark)
                            && recipes.get(i).getId() == idToEdit) {
                        Recipe recipeToEdit = recipes.get(i);
                        recipeEditor.editRecipe(recipeToEdit);
                        System.out.println(GlobalDescription.editSuccess);
                    }
                }
            } catch (Exception e) {
                System.out.println(GlobalDescription.invalidId);
            }
        }
        else {
            System.out.println(GlobalDescription.noBakingRecipesToEdit);
        }
    }


}
