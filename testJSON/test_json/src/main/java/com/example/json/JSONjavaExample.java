package com.example.json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import com.example.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

/**
 *
 * @author DELL
 */
public class JSONjavaExample {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        
        Person person = new Person();
        person.setBirthdate(simpleDateFormat.parse("1973-05-08"));
        person.setFirstName("Piotr");
        person.setLastName("Bobiński");

        System.out.println(person);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("firstName", person.getFirstName())
                .add("lastName", person.getLastName())
                .add("birthdate", simpleDateFormat.format(person.getBirthdate()));

        JsonObject jsonObject = objectBuilder.build();

        String jsonString;
        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(jsonObject);
            jsonString = writer.toString();
        }
        
        try (Writer writer = new FileWriter(new File("person.json"), StandardCharsets.UTF_8)) {
            Json.createWriter(writer).write(jsonObject);
        }

        System.out.println(jsonString);

        ObjectMapper objectMapper = new ObjectMapper();

        String personAsString = objectMapper.writeValueAsString(person);
        
        System.out.println(personAsString);
        
        Person per = objectMapper.readValue(personAsString, Person.class);	
        
        System.out.println(per);
        
        Person perFromFile = objectMapper.readValue(new File("person.json"), Person.class);	
        
        System.out.println(perFromFile);

    }
    
}
