package org.example.commands;

import org.example.FactoryMethod.BakingRecipeFactory;
import org.example.FactoryMethod.CookingRecipeFactory;
import org.example.FactoryMethod.IRecipe;
import org.example.FactoryMethod.RecipeFactory;
import org.example.builders.RecipeBuilder;
import org.example.objects.Ingredient;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;

import java.util.ArrayList;
import java.util.Scanner;

public class AddBakingRecipeCommand implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();

    private ArrayList<Recipe> recipes;
    //private ArrayList<IRecipe> iRecipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public AddBakingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        //this.iRecipes = recipeMenu.getIRecipes();
    }

    @Override
    public void runCommand() {
        /* Change so this is an add-cooking-recipe-command class.
         *  This method have to get to CookingRecipe.prepare where the following
         *  code below should be. */
        RecipeFactory bakingRecipeFactory = new BakingRecipeFactory(recipeMenu);
        int instructionId = getInstructionId();
        int ingredientId = getIngredientId();
        IRecipe bakingRecipe = bakingRecipeFactory.createRecipe(recipes.size() + 1, instructionId, ingredientId);
        //iRecipes.add(bakingRecipe);
        recipes.add(bakingRecipe.getRecipe());
    }

    private int getInstructionId(){
        int instructionId = 1;
        for(int i = 0; i < recipes.size(); i++){
            for(int j = 0; j < recipes.get(i).getInstructions().size(); j++){
                instructionId++;
            }
        }
        return instructionId;
    }
    private int getIngredientId(){
        int ingredientId = 1;
        for(int i = 0; i < recipes.size(); i++){
            for(int j = 0; j < recipes.get(i).getIngredients().size(); j++){
                ingredientId++;
            }
        }
        return ingredientId;
    }
}
