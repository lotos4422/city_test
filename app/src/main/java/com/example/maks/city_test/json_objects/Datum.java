package com.example.maks.city_test.json_objects;

import androidx.annotation.NonNull;

import java.util.List;

public class Datum {

    public String name;
    public String nameEn;
    public String nameUa;
    public List<City> cities = null;

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}