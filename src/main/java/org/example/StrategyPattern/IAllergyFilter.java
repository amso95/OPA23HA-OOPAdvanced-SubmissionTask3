package org.example.StrategyPattern;

import org.example.FactoryMethod.IRecipe;
import org.example.objects.Recipe;

import java.util.ArrayList;

public interface IAllergyFilter {
    void filterByFoodPreference(String allergy);
}