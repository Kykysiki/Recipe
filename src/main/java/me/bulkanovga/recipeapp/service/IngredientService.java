package me.bulkanovga.recipeapp.service;

import me.bulkanovga.recipeapp.model.Ingredient;

import java.util.List;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    Ingredient update(long id, Ingredient ingredient);

    Ingredient remove(long id);

    List<Ingredient> getAll();
}
