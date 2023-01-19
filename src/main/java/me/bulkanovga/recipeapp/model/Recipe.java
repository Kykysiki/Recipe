package me.bulkanovga.recipeapp.model;

import lombok.Data;

import java.util.List;
@Data
public class Recipe {
    private String recipeName;
    private int cookingTime;

    private List<Ingredient> ingredients;
    private List<String> steps;
}
