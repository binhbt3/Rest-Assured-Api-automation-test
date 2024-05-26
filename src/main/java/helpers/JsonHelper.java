package helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Log4j2
public class JsonHelper {

    public static void updateValueJsonFile(String sourceFile, String desFile, String keyName, String value){
        Reader reader;

        try {
            reader = Files.newBufferedReader(Paths.get(sourceFile));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original Json Object: " + jsonObject);
            jsonObject.addProperty(keyName, value);
            log.info("Json Object after modify: " + jsonObject);
            File jsonFile = new File(desFile);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonFile).getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String sourceFile, String desFile, String keyName, int value){
        Reader reader;

        try {
            reader = Files.newBufferedReader(Paths.get(sourceFile));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original Json Object: " + jsonObject);
            jsonObject.addProperty(keyName, value);
            log.info("Json Object after modify: " + jsonObject);
            File jsonFile = new File(desFile);
            OutputStream outputStream = new FileOutputStream(jsonFile);
//            outputStream.write(gson.toJson(jsonFile).getBytes());
            outputStream.write(jsonObject.toString().getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String sourceFile, String desFile, String keyName, boolean value){
        Reader reader;

        try {
            reader = Files.newBufferedReader(Paths.get(sourceFile));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original Json Object: " + jsonObject);
            jsonObject.addProperty(keyName, value);
            log.info("Json Object after modify: " + jsonObject);
            File jsonFile = new File(desFile);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonFile).getBytes());
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateValueJsonFile(String sourceFile, String desFile,  String keyProperty, String keyPropertyChild, String value){
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(sourceFile));

            Gson gson = new Gson();
            //Convert Json file to Json Object
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            System.out.println("Original JSON: " + jsonObject);

            //Update value if exist key
            jsonObject.getAsJsonObject(keyProperty).addProperty(keyPropertyChild, value);

            System.out.println("Modified JSON: " + jsonObject);

            //Store new Json data to new file
            File jsonFile = new File(desFile);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewPropertyAsArrayInJson(String sourceFilePath, String desFilePath, String propertyKey, JsonArray jsonArray){
        Reader reader;
        try{
            reader = Files.newBufferedReader(Paths.get(sourceFilePath));

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original JSON: " + jsonObject);
            jsonObject.add(propertyKey, jsonArray);
            log.info("Updated JSON: " + jsonObject);
            File jsonFile = new File(desFilePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewPropertyAsObjectInJson(String sourceFilePath, String desFilePath, Map objectMap, String propertyKey){
        Reader reader;
        try{
            reader = Files.newBufferedReader(Paths.get(sourceFilePath));

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original JSON: " + jsonObject);
            JsonElement jsonElement = gson.toJsonTree(objectMap);
            jsonObject.add(propertyKey, jsonElement);
            log.info("Updated JSON: " + jsonObject);

            // Store new Json data to file
            File jsonFile = new File(desFilePath);
            OutputStream outputStream = new FileOutputStream(jsonFile);
            outputStream.write(gson.toJson(jsonObject).getBytes());
            outputStream.flush();

            //Close reader
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void removePropertyInJson(String sourceFilePath, String desFilePath, String propertyKey){
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(sourceFilePath));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            log.info("Original JSON: " + jsonObject);
            jsonObject.remove(propertyKey);
            log.info("udated JSON: " + jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
