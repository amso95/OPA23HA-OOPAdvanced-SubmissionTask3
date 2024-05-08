package org.example.StrategyPattern;

import org.example.FactoryMethod.IRecipe;
import org.example.builders.RecipeBuilder;
import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AllergyStrategy implements IAllergyFilter {
    Scanner scanner;
    RecipeBuilder recipeBuilder;

    InputGetter inputGetter = new InputGetter();
    private ArrayList<IRecipe> iRecipes;
    private ArrayList<Recipe> recipes;
    /* To have the same correct reference value. */
    private RecipeMenu recipeMenu;
    public AllergyStrategy(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.recipeBuilder = recipeMenu.getRecipeBuilder();
        this.scanner = recipeMenu.getScanner();
        this.iRecipes = recipeMenu.getIRecipes();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void filterByFoodPreference(String allergy) {
        /* Check if the recipe have the food preference and after that sort the recipes in alphabetic order.
        *  Use: Quick sort Algorithm. */
        ArrayList<Recipe> wantedRecipes = getRecipesWithoutIngredient(allergy, recipes);
        Recipe[] recipeArray = makeListIntoArray(wantedRecipes);
        quickSort(recipeArray, 0, recipeArray.length - 1);
        if(recipeArray.length > 0) {
            System.out.println("--- Sorted ---");
            System.out.println("Following recipe can be used:");
            for (int i = 0; i < recipeArray.length; i++) {
                System.out.println(recipeArray[i].getName());
            }
        }
        else {
            System.out.println("Couldn't find any recipe without allergy type '" + allergy + "'.");
        }
    }

    private ArrayList<Recipe> getRecipesWithoutIngredient(String allergy, ArrayList<Recipe> recipes){
        boolean foundAllergy = false;
        ArrayList<Recipe> wantedRecipes = new ArrayList<>();
        for(int i = 0; i < recipes.size(); i++){
            for(int j = 0; j < recipes.get(i).getIngredients().size(); j++){
                if(recipes.get(i).getIngredients().get(j).getAllergyType().equalsIgnoreCase(allergy)){
                    foundAllergy = true;
                    break;
                }
            }
            if(!foundAllergy) {
                wantedRecipes.add(recipes.get(i));
            }
            foundAllergy = false;
        }
        return wantedRecipes;
    }

    private Recipe[] makeListIntoArray(ArrayList<Recipe> recipes){
        Recipe[] recipeArray = new Recipe[recipes.size()];
        for(int i = 0; i < recipes.size(); i++){
            recipeArray[i] = recipes.get(i);
        }
        return recipeArray;
    }

   private void quickSort(Recipe[] recipeArray, int min, int max){
       if(min < max){
           int pivotIndex = partition(recipeArray, min, max);
           quickSort(recipeArray, min, pivotIndex - 1);
           quickSort(recipeArray, pivotIndex + 1, max);
       }
   }
   private int partition(Recipe[] recipeArray, int min, int max){
       int pivotIndex = max;
       Recipe pivotValue = recipeArray[pivotIndex];

       int middleIndex = min;
       swap(recipeArray, pivotIndex, max);
       for(int i = min; i < max; i++){
           if(recipeArray[i].getName().compareToIgnoreCase(pivotValue.getName()) <= 0){
               swap(recipeArray, middleIndex, i);
               middleIndex++;
           }
       }
        swap(recipeArray, middleIndex, max);
       return middleIndex;
   }

   private void swap(Recipe[] recipeArray, int index1, int index2){
       Recipe temp = recipeArray[index1];
       recipeArray[index1] = recipeArray[index2];
       recipeArray[index2] = temp;
   }
}
