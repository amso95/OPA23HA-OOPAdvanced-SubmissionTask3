package org.example.FactoryPattern;

import org.example.objects.Ingredient;
import org.example.objects.Recipe;

import java.util.ArrayList;

public interface IRecipe {
    void prepare(int id, int instructionId, int ingredientId);

    ArrayList<Ingredient> getIngredients();
    Recipe getRecipe();

    String getName();
}
