package org.example.FactoryMethod;

import org.example.objects.Ingredient;
import org.example.objects.Recipe;

import java.util.ArrayList;

public interface IRecipe {
    void prepare(int id, int instructionId, int ingredientId);

    void printRecipe();
    void printRecipeWithId();
    ArrayList<Ingredient> getIngredients();
    Recipe getRecipe();

    String getName();
}
