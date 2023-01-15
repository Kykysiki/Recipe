package me.bulkanovga.recipeapp.model;

public class Ingredient {
    private String title;
    private int quantityOfIngredients;
    private String measure;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantityOfIngredients() {
        return quantityOfIngredients;
    }

    public void setQuantityOfIngredients(int quantityOfIngredients) {
        this.quantityOfIngredients = quantityOfIngredients;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
