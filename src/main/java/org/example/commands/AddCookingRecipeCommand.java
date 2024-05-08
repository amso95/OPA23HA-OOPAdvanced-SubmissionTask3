package org.example.commands;

import org.example.FactoryMethod.CookingRecipeFactory;
import org.example.FactoryMethod.IRecipe;
import org.example.FactoryMethod.RecipeFactory;
import org.example.objects.InputGetter;
import org.example.objects.Recipe;
import org.example.objects.RecipeMenu;
import org.example.builders.RecipeBuilder;

import java.util.ArrayList;
import java.util.Scanner;

public class AddCookingRecipeCommand implements ICommand{
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();

    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;

    public AddCookingRecipeCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipes = recipeMenu.getRecipes();
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
    }

    @Override
    public void runCommand() {
        RecipeFactory cookingRecipeFactory = new CookingRecipeFactory(recipeMenu);
        int instructionId = getInstructionId();
        int ingredientId = getIngredientId();
        IRecipe cookingRecipe = cookingRecipeFactory.createRecipe(recipes.size() + 1, instructionId, ingredientId);
        //iRecipes.add(cookingRecipe);
        recipes.add(cookingRecipe.getRecipe());
        System.out.println(recipes.size());
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
