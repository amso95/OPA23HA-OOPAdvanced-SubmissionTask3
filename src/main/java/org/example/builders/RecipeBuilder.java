package org.example.builders;

import org.example.objects.Ingredient;
import org.example.objects.Instruction;
import org.example.objects.Recipe;

import java.util.ArrayList;

public class RecipeBuilder {
    private int id = Integer.MIN_VALUE;
    private String name = "";
    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Instruction> instructions = new ArrayList<>();
    private String creator = "";
    private String bakingOrCooking = "";
    public RecipeBuilder setId(int id){
        this.id = id;
        return this;
    }
    public RecipeBuilder setName(String name){
        this.name = name;
        return this;
    }
    public RecipeBuilder setIngredients(ArrayList<Ingredient> ingredients){
        this.ingredients = ingredients;
        return this;
    }
    public RecipeBuilder setInstructions(ArrayList<Instruction> instructions){
        this.instructions = instructions;
        return this;
    }
    public RecipeBuilder setCreator(String creator){
        this.creator = creator;
        return this;
    }
    public RecipeBuilder setBakingOrCooking(String bakingOrCooking) {
        this.bakingOrCooking = bakingOrCooking;
        return this;
    }

    public Recipe build(){
        return new Recipe(id, name, ingredients, instructions, creator, bakingOrCooking);
    }
}
