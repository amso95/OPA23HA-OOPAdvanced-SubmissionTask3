package org.example.builders;

import org.example.objects.Ingredient;
import org.example.objects.Instruction;

public class InstructionBuilder {
    private int id = Integer.MIN_VALUE;;
    private String description = "";

    public InstructionBuilder setId(int id){
        this.id = id;
        return this;
    }
    public InstructionBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public Instruction build(){
        return new Instruction(id, description);
    }
}
