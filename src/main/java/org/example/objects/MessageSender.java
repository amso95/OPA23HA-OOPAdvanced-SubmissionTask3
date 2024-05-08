package org.example.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.FactoryMethod.IRecipe;
import org.example.objects.Recipe;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MessageSender {
    public ArrayList<Recipe> getRequest(){
        try{
            URL url = new URL("http://localhost:8080/recipe/get-all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect(); //This will make the connection

            int code = connection.getResponseCode();
            if(code >= 200 && code <= 299){ //response is successful
                //System.out.println(connection.getResponseMessage());
                //System.out.println(connection.getContent());

                InputStream stream = connection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(stream);
                BufferedReader reader = new BufferedReader(streamReader);

                StringBuilder jsonBuilder = new StringBuilder();
                String inputLine = reader.readLine();
                while (inputLine != null){
                    jsonBuilder.append(inputLine);
                    inputLine = reader.readLine();
                }
                String json = jsonBuilder.toString();

                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Recipe>>() {}.getType();
                ArrayList<Recipe> recipes = gson.fromJson(json, listType);

                //for(Recipe recipe: recipes){
                //    recipe.printRecipe();
                //}

                //System.out.println(json);

                return recipes;
            }
            else{
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void postRecipe(Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/recipe/create");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + recipe.getId() + "," +
                    "\"name\": \"" + recipe.getName() +"\"," +
                    "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                    "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
        for(Ingredient ingredient: recipe.getIngredients()){
            postIngredient(ingredient, recipe);
        }
        for(Instruction instruction: recipe.getInstructions()){
            postInstruction(instruction, recipe);
        }
    }
    public void postIngredient(Ingredient ingredient, Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/ingredient/create");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + ingredient.getId() + "," +
                    "\"name\": \"" + ingredient.getName() +"\"," +
                    "\"amount\": " + "\"" + ingredient.getAmount() + "\"," +
                    "\"allergyType\": " + "\"" + ingredient.getAllergyType() + "\"," +
                    "\"recipe\": " + "{\"id\": " + recipe.getId() + "," +
                        "\"name\": \"" + recipe.getName() +"\"," +
                        "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                        "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                        "}" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
    }
    public void postInstruction(Instruction instruction, Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/instruction/create");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + instruction.getId() + "," +
                    "\"description\": \"" + instruction.getDescription() +"\"," +
                    "\"recipe\": " + "{\"id\": " + recipe.getId() + "," +
                        "\"name\": \"" + recipe.getName() +"\"," +
                        "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                        "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                        "}" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
    }

    public void deleteRecipe(int id){
        try{
            URL url = new URL("http://localhost:8080/recipe/delete?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect(); //This will make the connection

            int code = connection.getResponseCode();
            if(code >= 200 && code <= 299){ //response is successful
                System.out.println("Delete success! Status code: " + code);
            }
            else{
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
    }

    public void putRecipe(Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/recipe/edit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + recipe.getId() + "," +
                    "\"name\": \"" + recipe.getName() +"\"," +
                    "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                    "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
        for(Ingredient ingredient: recipe.getIngredients()){
            putIngredient(ingredient, recipe);
        }
        for(Instruction instruction: recipe.getInstructions()){
            putInstruction(instruction, recipe);
        }
    }
    public void putIngredient(Ingredient ingredient, Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/ingredient/edit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + ingredient.getId() + "," +
                    "\"name\": \"" + ingredient.getName() +"\"," +
                    "\"amount\": " + "\"" + ingredient.getAmount() + "\"," +
                    "\"allergyType\": " + "\"" + ingredient.getAllergyType() + "\"," +
                    "\"recipe\": " + "{\"id\": " + recipe.getId() + "," +
                    "\"name\": \"" + recipe.getName() +"\"," +
                    "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                    "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                    "}" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }

    }
    public void putInstruction(Instruction instruction, Recipe recipe){
        try{
            URL url = new URL("http://localhost:8080/instruction/edit");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            String jsonInputString = "{\"id\": " + instruction.getId() + "," +
                    "\"description\": \"" + instruction.getDescription() +"\"," +
                    "\"recipe\": " + "{\"id\": " + recipe.getId() + "," +
                    "\"name\": \"" + recipe.getName() +"\"," +
                    "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                    "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                    "}" +
                    "}";

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            connection.connect(); //This will make the connection

            //printResponseCode(connection.getResponseCode());
        }
        catch (Exception e){
            System.out.println("Nothing to connect to, everything crashed.");
            System.out.println(e.getMessage());
        }
    }
    private void printResponseCode(int code){
        // Successful response
        if(code >= 200 && code <= 299){
            System.out.println("Success! Status code: " + code);
        }
        else{
            System.out.println("Error status code: " + code);
        }
    }

}
