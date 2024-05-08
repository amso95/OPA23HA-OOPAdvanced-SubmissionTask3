package org.example.builders;

import org.example.objects.Ingredient;
import org.example.objects.Instruction;
import org.example.objects.Recipe;

import java.util.ArrayList;

public class IngredientBuilder {
    private int id = Integer.MIN_VALUE;;
    private String name = "";
    private String amount = "";
    private String allergyType = "";

    public IngredientBuilder setId(int id){
        this.id = id;
        return this;
    }
    public IngredientBuilder setName(String name){
        this.name = name;
        return this;
    }
    public IngredientBuilder setAmount(String amount){
        this.amount = amount;
        return this;
    }
    public IngredientBuilder setAllergyType(String allergyType){
        this.allergyType = allergyType;
        return this;
    }

    public Ingredient build(){
        return new Ingredient(id, name, amount, allergyType);
    }
}
