package com.example.resturant.Activity.Domain;

public class Location {
    private int Id;
    private String Loc;

    public Location() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoc() {
        return Loc;
    }

    @Override
    public String toString() {
        return this.getLoc(); // يعرض اسم الموقع بدلاً من اسم الكلاس
    }

}
