package com.example.resturant.Activity.Domain;

public class Time {
    private String Value;
    private int Id;
    public Time() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
    @Override
    public String toString() {
        return  Value;
    }
}
