package org.example.StrategyPattern;

import org.example.FactoryPattern.IRecipe;
import org.example.objects.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AllergyStrategy implements IAllergyFilter {
    Scanner scanner;
    InputGetter inputGetter = new InputGetter();
    private ArrayList<Recipe> recipes;
    private RecipeMenu recipeMenu;
    public AllergyStrategy(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
        this.scanner = recipeMenu.getScanner();
        this.recipes = recipeMenu.getRecipes();
    }
    @Override
    public void filterByAllergy(String allergy) {
        /* Check if the recipe have the food preference and after that sort the recipes in alphabetic order.
        *  Use: Quick sort Algorithm. */
        ArrayList<Recipe> wantedRecipes = getRecipesWithoutIngredient(allergy, recipes);
        Recipe[] recipeArray = inputGetter.makeListIntoArray(wantedRecipes);
        quickSort(recipeArray, 0, recipeArray.length - 1);
        if(recipeArray.length > 0) {
            System.out.println(GlobalDescription.alphabeticallySortedHeader);
            System.out.println(GlobalDescription.filteredRecipes);
            for (int i = 0; i < recipeArray.length; i++) {
                System.out.println(recipeArray[i].getName());
            }
        }
        else {
            System.out.println("Couldn't find any recipe without allergy type '" + allergy + "'.");
        }
    }
    /* Sort out the recipes that don't have an ingredient with given allergy. */
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
    /* A function that implements QuickSort. */
   private void quickSort(Recipe[] recipeArray, int min, int max){
       if(min < max){
           int pivotIndex = partition(recipeArray, min, max);
           quickSort(recipeArray, min, pivotIndex - 1);
           quickSort(recipeArray, pivotIndex + 1, max);
       }
   }
    /* A function that takes last element as pivot,
       the pivot element is placed at its correct position
       in alphabetically sorted array, and places all smaller
       (e.g. letters in the beginning of the alphabet) to left
       of pivot and all greater elements (e.g. letters in the end of
       the alphabet) to right of the pivot. */
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
    /* A function to swap 2 elements in an array. */
   private void swap(Recipe[] recipeArray, int index1, int index2){
       Recipe temp = recipeArray[index1];
       recipeArray[index1] = recipeArray[index2];
       recipeArray[index2] = temp;
   }
}
