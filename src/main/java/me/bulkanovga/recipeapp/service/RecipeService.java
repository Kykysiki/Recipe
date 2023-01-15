package me.bulkanovga.recipeapp.service;

import me.bulkanovga.recipeapp.model.Recipe;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe get(long id);
}
