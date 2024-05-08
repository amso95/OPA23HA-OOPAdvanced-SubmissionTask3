package org.example.builders;

import org.example.objects.RecipeMenu;
import org.example.commands.ICommand;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuBuilder {

    private MenuDirector director = new MenuDirector();
    private String message = "";
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();
    private RecipeMenu recipeMenu = new RecipeMenu();
    public MenuBuilder(RecipeMenu recipeMenu){
        this.recipeMenu = recipeMenu;
    }
    public MenuBuilder setRecipeMenu(RecipeMenu recipeMenu) {
        this.director.recipeMenu(this, recipeMenu);
        return this;
    }

    public MenuBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public MenuBuilder setCommands(String key, ICommand command) {
        commands.put(key, command);
        return this;
    }

    public MenuBuilder setOptions(String option) {
        options.add(option);
        return this;
    }

    public RecipeMenu build(){
        recipeMenu.setCommands(commands);
        recipeMenu.setMessage(message);
        recipeMenu.setOptions(options);
        return recipeMenu;

    }
}
