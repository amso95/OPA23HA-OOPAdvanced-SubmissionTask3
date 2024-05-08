package org.example;

import org.example.builders.*;
import org.example.StrategyPattern.AllergyStrategy;
import org.example.StrategyPattern.IngredientStrategy;
import org.example.objects.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /* Check to make sure that builder method works. */
        //RecipeBuilder recipeBuilder = new RecipeBuilder();
        //ArrayList<Ingredient> ingredients = new ArrayList<>();
        //ArrayList<Instruction> instructions = new ArrayList<>();
        //ingredients.add(new Ingredient("Egg", "2", "Egg"));
        //ingredients.add(new Ingredient("Milk", "2 dl", "Lactose"));
        //ingredients.add(new Ingredient("Flour", "4 dl", "Gluten"));
        //instructions.add(new Instruction( "Mix all indigents together in a large bowl."));
        //instructions.add(new Instruction("Fry the pancake batter in a frying pan at medium-high heat."));
        //Recipe recipe = recipeBuilder.setName("Pancakes").setIngredients(ingredients).setInstructions(instructions).setCreator("TS").setBakingOrCooking("c").build();
        //recipe.printRecipe();
        /* Check to make sure that factory method works. */
        //RecipeFactory cookingRecipeFactory = new CookingRecipeFactory();
        //IRecipe cookingRecipe = cookingRecipeFactory.createRecipe();
        //RecipeFactory bakingRecipeFactory = new BakingRecipeFactory();
        //IRecipe bakingRecipe = bakingRecipeFactory.createRecipe();

        /* Check to make sure that strategy method works. */
        //AllergyStrategy foodPreferenceStrategy = new AllergyStrategy();
        //foodPreferenceStrategy.filterByFoodPreference("gluten");
        //IngredientStrategy ingriedientStrategy = new IngredientStrategy();
        //ingriedientStrategy.filterByIngredient("egg");

        /* Check to make sure that messageSender is working with HTTP-requests. */
        MessageSender messageSender = new MessageSender();
        messageSender.getRequest();
        //messageSender.postRecipe(recipe);

        /* Check to make sure that the menu with commands works. */
        /* Make this to the correct menu. Add a MenuDirector and MenuBuilder into the builder pattern. Look at lesson code from 26/4-2024 */
        /*HashMap<String, ICommand> commands = new HashMap<>();
        ArrayList<String> options = new ArrayList<>();
        options.add("1. Add new recipe.");
        options.add("2. Show all recipe.");
        options.add("0. Quit program.");
        RecipeHandler recipeHandler1 = new RecipeHandler(commands, "What do you want to do?", options);
        commands.put("1", new AddRecipeCommand(recipeHandler1));
        commands.put("2", new ShowAllRecipesCommand(recipeHandler1));
        commands.put("0", new QuitProgramCommand(recipeHandler1));
        recipeHandler1.run();*/
        RecipeMenu recipeHandler = new RecipeMenu();
        recipeHandler.getRecipesFromDatabase();
        MenuBuilder menuBuilder = new MenuBuilder(recipeHandler);
        recipeHandler = menuBuilder.setRecipeMenu(recipeHandler).build();
        recipeHandler.run();
    }
}