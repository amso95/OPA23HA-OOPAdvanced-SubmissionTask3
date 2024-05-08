package org.example.commands;

import org.example.objects.RecipeMenu;

public class QuitProgramCommand implements ICommand{
    private RecipeMenu recipeMenu;

    public QuitProgramCommand(RecipeMenu recipeMenu) {
        /* To have the same correct reference value. */
        this.recipeMenu = recipeMenu;
    }

    @Override
    public void runCommand() {
        recipeMenu.setRunning(false);
    }
}
