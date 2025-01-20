package com.example.animalinfoapp.models;

public class Animal {

    private String name;
    private String place_of_found;
    private String description;

    public Animal() {
        // Default constructor required for calls to DataSnapshot.getValue(Animal.class)
    }

    public Animal(String name, String place_of_found, String description) {
        this.name = name;
        this.place_of_found = place_of_found;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfFound() {
        return place_of_found;
    }

    public void setPlaceOfFound(String place_of_found) {
        this.place_of_found = place_of_found;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
