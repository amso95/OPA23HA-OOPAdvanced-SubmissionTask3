package org.example.objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DatabaseConnection {
    public ArrayList<Recipe> getRequest(){
        try{
            URL url = new URL("http://localhost:8080/recipe/get-all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Make the connection.
            connection.connect();
            // Get response code.
            int code = connection.getResponseCode();
            // Check if response is successful.
            if(code >= 200 && code <= 299){
                InputStream stream = connection.getInputStream();
                String json = getJsonString(stream);
                if(json != null) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<ArrayList<Recipe>>() {
                    }.getType();
                    ArrayList<Recipe> recipes = gson.fromJson(json, listType);
                    return recipes;
                }
            }
            else{
                // Inform user.
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
        // If database GET-request fails, return a new empty ArrayList instead of null
        // so the program do not crash.
        return new ArrayList<>();
    }

    public int getNextIdRequest(String urlRequestMapping){
        try{
            URL url = new URL("http://localhost:8080/" + urlRequestMapping + "/get-next-id");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // Make the connection.
            connection.connect();
            // Get response code.
            int code = connection.getResponseCode();
            // Check if response is successful.
            if(code >= 200 && code <= 299){
                InputStream stream = connection.getInputStream();
                String json = getJsonString(stream);
                if(json != null) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<Integer>() {}.getType();
                    int nextId = gson.fromJson(json, type);
                    return nextId;
                }
            }
            else{
                // Inform user.
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
        // If database GET-request fails, return min value of Integer.
        return Integer.MIN_VALUE;
    }
    public void recipeToDatabase(Recipe recipe, String urlMapping, String method){
        try{
            URL url = new URL("http://localhost:8080/recipe/" + urlMapping);
            HttpURLConnection connection = setHttpRequestSettingsForPutAndPost(url, method);
            String jsonInputString = setJsonRecipeObject(recipe);

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
            }
            // Make the connection.
            connection.connect();
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
        for(Ingredient ingredient: recipe.getIngredients()){
            ingredientToDatabase(ingredient, recipe, urlMapping, method);
        }
        for(Instruction instruction: recipe.getInstructions()){
            instructionToDatabase(instruction, recipe, urlMapping, method);
        }
    }
    public void ingredientToDatabase(Ingredient ingredient, Recipe recipe, String urlMapping, String method){
        try{
            URL url = new URL("http://localhost:8080/ingredient/" + urlMapping);
            HttpURLConnection connection = setHttpRequestSettingsForPutAndPost(url, method);
            String jsonInputString = setJsonIngredientObject(ingredient, recipe);

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
            }
            // Make the connection.
            connection.connect();
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
    }
    public void instructionToDatabase(Instruction instruction, Recipe recipe, String urlMapping, String method){
        try{
            URL url = new URL("http://localhost:8080/instruction/" + urlMapping);
            HttpURLConnection connection = setHttpRequestSettingsForPutAndPost(url, method);
            String jsonInputString = setJsonInstructionObject(instruction, recipe);

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
            }
            // Make the connection.
            connection.connect();
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
    }

    public void deleteRecipe(int id){
        try{
            URL url = new URL("http://localhost:8080/recipe/delete?id=" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            // Make the connection.
            connection.connect();
            // Get response code.
            int code = connection.getResponseCode();
            // Check if response is successful.
            if(code >= 200 && code <= 299){
                System.out.println("Delete success! Status code: " + code);
            }
            else{
                // Inform user.
                System.out.println("Error status code: " + code);
            }
        }
        catch (Exception e){
            // Inform user.
            outputErrorMessage(e);
        }
    }
    private String setJsonRecipeObject(Recipe recipe){
        String jsonInputString = "{\"id\": " + recipe.getId() + "," +
                "\"name\": \"" + recipe.getName() +"\"," +
                "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                "}";
        return jsonInputString;
    }
    private String setJsonIngredientObject(Ingredient ingredient, Recipe recipe){
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
        return jsonInputString;
    }

    private String setJsonInstructionObject(Instruction instruction, Recipe recipe){
        String jsonInputString = "{\"id\": " + instruction.getId() + "," +
                "\"description\": \"" + instruction.getDescription() +"\"," +
                "\"recipe\": " + "{\"id\": " + recipe.getId() + "," +
                "\"name\": \"" + recipe.getName() +"\"," +
                "\"creator\": " + "\"" + recipe.getCreator() + "\"," +
                "\"bakingOrCooking\": " + "\"" + recipe.getBakingOrCooking() + "\"" +
                "}" +
                "}";
        return jsonInputString;
    }
    private String getJsonString(InputStream stream){
        try {
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder jsonBuilder = new StringBuilder();
            String inputLine = reader.readLine();
            while (inputLine != null){
                jsonBuilder.append(inputLine);
                inputLine = reader.readLine();
            }
            String json = jsonBuilder.toString();
            return json;
        }
        catch (Exception e){
            System.out.println();
        }
        return null;
    }
    private HttpURLConnection setHttpRequestSettingsForPutAndPost(URL url, String method){
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            return connection;
        }
        catch (Exception e){
            System.out.println(GlobalDescription.error);
        }
        return null;
    }
    private void outputErrorMessage(Exception e){
        System.out.println("Nothing to connect to, everything crashed.");
        System.out.println(e.getMessage());
    }
}
