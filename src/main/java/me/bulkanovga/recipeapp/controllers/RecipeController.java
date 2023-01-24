package me.bulkanovga.recipeapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.bulkanovga.recipeapp.model.Recipe;
import me.bulkanovga.recipeapp.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/download")
    @Operation(description = "Скачать все рецепты в виде json-файла")
    public ResponseEntity<byte[]> downloadRecipes() {
        byte[] bytes = recipeService.getAllInBytes();
        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.json\"")
                .body(bytes);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importRecipes(MultipartFile recipes) {
        recipeService.importRecipes(recipes);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportTxt() {
        byte[] bytes = recipeService.exportTxt();
        if (bytes == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(bytes.length)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                .body(bytes);
    }
}
