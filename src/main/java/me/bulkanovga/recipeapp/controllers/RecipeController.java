package me.bulkanovga.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.bulkanovga.recipeapp.model.Recipe;
import me.bulkanovga.recipeapp.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @Operation(description = "Отображение всех рецептов")
    private List<Recipe> getAll() {
        return this.recipeService.getAll();
    }

    @PostMapping
    @Operation(description = "Добавление рецепта")
    public ResponseEntity<?> addRecipe(@RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getRecipeName())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipeService.add(recipe));
    }

    @GetMapping("/{id}")
    @Operation(description = "Отображение рецепта")
    public Recipe getRecipe(@PathVariable("id") long id) {
        return this.recipeService.get(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Изменение рецепта")
    public ResponseEntity<?> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        if (StringUtils.isBlank(recipe.getRecipeName())) {
            return ResponseEntity.badRequest().body("Название рецепта не может быть пустым");
        }
        return ResponseEntity.ok(recipeService.update(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление рецепта")
    public Recipe deleteRecipe(@PathVariable("id") long id) {
        return recipeService.remove(id);
    }
}
