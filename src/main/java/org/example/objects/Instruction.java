package org.example.objects;

public class Instruction{
    private int id;
    private String description;

    private int idCounter = 1;
    public Instruction(int id, String description) {
        if(id >= idCounter) {
            idCounter = id + 1;
        }
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
