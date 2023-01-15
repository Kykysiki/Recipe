package me.bulkanovga.recipeapp.service;

import me.bulkanovga.recipeapp.model.Ingredient;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);
}
