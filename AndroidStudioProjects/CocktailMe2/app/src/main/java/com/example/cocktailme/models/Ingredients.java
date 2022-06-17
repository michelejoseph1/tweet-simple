package com.example.cocktailme.models;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Ingredients {
    String ingredientName;

    public Ingredients(){}

    public Ingredients(JSONObject jsonObject) throws JSONException {
        ingredientName = jsonObject.getString("ingredients");

    }

    public static List<Ingredients> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Ingredients> ingredients = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            ingredients.add(new Ingredients(movieJsonArray.getJSONObject(i)));
        }
        return ingredients;
    }
    }

