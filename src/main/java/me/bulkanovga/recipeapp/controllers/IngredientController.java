package me.bulkanovga.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.bulkanovga.recipeapp.model.Ingredient;
import me.bulkanovga.recipeapp.service.IngredientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }
    @GetMapping
    @Operation(description = "Отображение всех ингредиентов")
    private List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PostMapping
    @Operation(description = "Добавление ингредиента")
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle())) {
            return ResponseEntity.badRequest().body("Название ингредиента не может быть пустым");
        }
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }
    @GetMapping("/{id}")
    @Operation(description = "Отображение ингредиента")
    public Ingredient getIngredient(@PathVariable("id") long id) {
        return this.ingredientService.get(id);
    }
    @PutMapping("/{id}")
    @Operation(description = "Изменение ингредиента")
    public ResponseEntity<?> updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getTitle())) {
            return ResponseEntity.badRequest().body("Название ингредиента не может быть пустым");
        }
        return ResponseEntity.ok(ingredientService.update(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление ингредиента")
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }
}
