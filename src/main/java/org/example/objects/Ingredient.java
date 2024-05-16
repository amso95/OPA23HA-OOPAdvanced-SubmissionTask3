package org.example.objects;

public class Ingredient {
    private int id;
    private String name;
    private String amount;
    private String allergyType;
    private int idCounter = 1;

    public Ingredient(int id, String name, String amount,String allergyType) {
        if(id >= idCounter) {
            idCounter = id + 1;
        }
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.allergyType = allergyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    public int getId() {
        return id;
    }
}
