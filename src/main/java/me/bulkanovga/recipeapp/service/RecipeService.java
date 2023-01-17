package me.bulkanovga.recipeapp.service;

import me.bulkanovga.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe get(long id);

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);

    List<Recipe> getAll();
}
