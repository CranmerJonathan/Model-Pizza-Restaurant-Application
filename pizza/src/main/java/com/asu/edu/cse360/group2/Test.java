package com.asu.edu.cse360.group2;


import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;


public class Test implements Serializable {
    String name;
    String email;
    int age;
    boolean isDeveloper;

    public Test(){
        name = "tester";
        age = 19;
    }

   

    public Test(String name, int age){
        this.name = name;
        this.age = age;
    }
    
    public String getName(){
        return this.name;
    }


    public int getAge(){
        return this.age;
    }

    public void setName(String n){
        this.name = n;
    }
 
    
    public void setAge(int n){
        this.age = n;
    }

    public static String serializeToJSON(Test t) {
        
        Gson gson = new Gson();
        String p = gson.toJson(t);
        return p;   
    }
}


