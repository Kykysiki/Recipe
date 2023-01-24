package me.bulkanovga.recipeapp.model;

import lombok.Data;

@Data
public class Ingredient {
    private String title;
    private int quantityOfIngredients;
    private String measure;

    @Override
    public String toString() {
        return title + " - " + quantityOfIngredients + " " + measure;
    }
}
