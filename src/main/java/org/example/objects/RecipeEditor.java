package org.example.objects;

import java.util.ArrayList;

public class RecipeEditor {
    InputGetter inputGetter = new InputGetter();
    public String editStringVariable(String message, String name, String variableName){
        String input = editOption(message, name, variableName);
        if(input.equals("y")){
            return inputGetter.getStringInput("Write updated " + variableName);
        }
        return "";
    }
    public String editOption(String message, String name, String variableName){
        System.out.println(message + name);
        return inputGetter.getStringInputCanBeEmpty("Do you want edit " + variableName + "\n'y' for Yes and anything else for No.");
    }

    public void printInstructions(ArrayList<Instruction> instructions){
        for(int i = 0; i < instructions.size(); i++){
            System.out.println("Index: " + i + ", description: " + instructions.get(i).getDescription());
        }
    }
    public void printIngredients(ArrayList<Ingredient> ingredients){
        for(int i = 0; i < ingredients.size(); i++){
            System.out.println("Index: " + i + ", name: " + ingredients.get(i).getName() +
                    ", amount: " + ingredients.get(i).getAmount()+ ", allergy type: " + ingredients.get(i).getAllergyType());
        }
    }
    public void editInstructionVariable(ArrayList<Instruction> instructions){
        int indexInput = inputGetter.getIntInput("Write the index of the instruction you want to edit.");
        try{
            Instruction instructionToEdit = instructions.get(indexInput);
            String descriptionEdit = inputGetter.getStringInput("Instruction description is: " + instructionToEdit.getDescription() + "\nWrite updated description");
            instructionToEdit.setDescription(descriptionEdit);

        }
        catch (Exception e){
            System.out.println(GlobalDescription.noInstructionAtIndex);
        }
    }
    public void editIngredientVariables(ArrayList<Ingredient> ingredients){
        int indexInput = inputGetter.getIntInput("Write the index of the ingredient you want to edit.");
        try{
            Ingredient ingredientToEdit = ingredients.get(indexInput);
            String edit  = editStringVariable("Ingredient name is: ", ingredientToEdit.getName(), "name");
            if(!edit.equals("")){
                ingredientToEdit.setName(edit);
            }
            edit  = editStringVariable("Ingredient amount is: ", ingredientToEdit.getAmount(), "amount");
            if(!edit.equals("")){
                ingredientToEdit.setAmount(edit);
            }
            edit  = editStringVariable("Ingredient allergy type is: ", ingredientToEdit.getAllergyType(), "allergyType");
            if(!edit.equals("")){
                ingredientToEdit.setAllergyType(edit);
            }
        }
        catch (Exception e){
            System.out.println(GlobalDescription.noIngredientAtIndex);
        }
    }

    public void editRecipe(Recipe recipeToEdit){
        String edit  = editStringVariable("Recipe name is: ", recipeToEdit.getName(), "name");
        if(!edit.equals("")){
            recipeToEdit.setName(edit);
        }
        edit = editStringVariable("Recipe creator is: ", recipeToEdit.getCreator(), "creator");
        if(!edit.equals("")){
            recipeToEdit.setCreator(edit);
        }
        System.out.println(GlobalDescription.instructionsHeader);
        printInstructions(recipeToEdit.getInstructions());
        edit = editOption("There is a list of ", "instructions", "instructions");
        if (edit.equals("y")){
            editInstructionVariable(recipeToEdit.getInstructions());
        }
        System.out.println(GlobalDescription.ingredientsHeader);
        printIngredients(recipeToEdit.getIngredients());
        edit = editOption("There is a list of ", "ingredients", "ingredients");
        if (edit.equals("y")){
            editIngredientVariables(recipeToEdit.getIngredients());
        }
    }
}
