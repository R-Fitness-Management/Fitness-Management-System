package com.fitness.management;

import java.io.Serializable;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String fitnessGoals;
    private String dietaryPreferences;
    private String dietaryRestrictions;
    private String email; 

   
    public Profile(String name, int age, String email, String fitnessGoals, String dietaryPreferences, String dietaryRestrictions) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.fitnessGoals = fitnessGoals;
        this.dietaryPreferences = dietaryPreferences;
        this.dietaryRestrictions = dietaryRestrictions;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFitnessGoals() {
        return fitnessGoals;
    }

    public void setFitnessGoals(String fitnessGoals) {
        this.fitnessGoals = fitnessGoals;
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(String dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", fitnessGoals='" + fitnessGoals + '\'' +
                ", dietaryPreferences='" + dietaryPreferences + '\'' +
                ", dietaryRestrictions='" + dietaryRestrictions + '\'' +
                '}';
    }
}
