package org.example.FactoryMethod;

import org.example.objects.Ingredient;
import org.example.objects.Instruction;
import org.example.objects.Recipe;

import java.util.ArrayList;

public abstract class RecipeFactory {

    public IRecipe createRecipe(int id, int instructionId, int ingredientId){
        IRecipe recipe = makeRecipe();
        recipe.prepare(id, instructionId, ingredientId);
        return recipe;
    }

    public abstract IRecipe makeRecipe();

    public abstract ArrayList<Recipe> getRecipes();

}
