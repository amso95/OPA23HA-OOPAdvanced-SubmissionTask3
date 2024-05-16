package org.example.StrategyPattern;

import org.example.FactoryPattern.IRecipe;
import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class IngredientStrategy implements IIngredientFilter{
    Scanner scanner;
    //RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();
    //private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;
    public IngredientStrategy(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        //this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        //this.iRecipes = recipeMenu.getIRecipes();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void filterByIngredient(String containIngredient) {
        /* Check if the recipe have the ingredient and after that sort the recipes in alphabetic order.
        *  Use: Merge Sort. */
        ArrayList<Recipe> wantedRecipes = getRecipesWithIngredient(containIngredient, recipes);
        Recipe[] recipeArray = inputGetter.makeListIntoArray(wantedRecipes);
        mergeSortAlphabetically(recipeArray);
        if(recipeArray.length > 0) {
            System.out.println(GlobalDescription.alphabeticallySortedHeader);
            System.out.println(GlobalDescription.filteredRecipes);
            for (int i = 0; i < recipeArray.length; i++) {
                System.out.println(recipeArray[i].getName());
            }
        }
        else {
            System.out.println("Couldn't find any recipe with ingredient '" + containIngredient + "'.");
        }
    }
    /* Sort out the recipes that do have the given ingredient. */
    private ArrayList<Recipe> getRecipesWithIngredient(String containIngredient, ArrayList<Recipe> recipes){
        ArrayList<Recipe> wantedRecipes = new ArrayList<>();
        for(int i = 0; i < recipes.size(); i++){
            for(int j = 0; j < recipes.get(i).getIngredients().size(); j++){
                if(recipes.get(i).getIngredients().get(j).getName().equalsIgnoreCase(containIngredient)){
                    wantedRecipes.add(recipes.get(i));
                    break;
                }
            }
        }
        return wantedRecipes;
    }
    /* Merge sort function that sort by recipe name alphabetically. */
    private void mergeSortAlphabetically(Recipe[] recipeArray){
        if(recipeArray.length < GlobalDescription.sortIfLengthIsBiggerThanTwo){
            return;
        }
        // Find the middle value of the array.
        int middle = recipeArray.length / 2;
        // Part up into smaller arrays.
        Recipe left[] = new Recipe[middle];
        Recipe right[] = new Recipe[recipeArray.length - middle];
        // Copy data to the corresponding array.
        for(int i = 0;  i < left.length; i++){
            left[i] = recipeArray[i];
        }
        for(int i = 0;  i < right.length; i++){
            right[i] = recipeArray[i + middle];
        }

        mergeSortAlphabetically(left);
        mergeSortAlphabetically(right);

        // Sort and merge left and right array
        int indexLeft = 0;
        int indexRight = 0;

        for(int i = 0; i < recipeArray.length; i++) {
            if(indexLeft >= left.length){
                recipeArray[i] = right[indexRight];
                indexRight++;
            }
            else if (indexRight >= right.length) {
                recipeArray[i] = left[indexLeft];
                indexLeft++;
            }
            else if (left[indexLeft].getName().toLowerCase().compareTo(right[indexRight].getName().toLowerCase()) <= 0) {
                recipeArray[i] = left[indexLeft];
                indexLeft++;
            }
            else {
                recipeArray[i] = right[indexRight];
                indexRight++;
            }
        }
    }
}
