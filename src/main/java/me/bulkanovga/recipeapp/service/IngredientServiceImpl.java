package me.bulkanovga.recipeapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.bulkanovga.recipeapp.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();

    private Long counter = 0L;

    private final Path path;

    private final ObjectMapper objectMapper;

    public IngredientServiceImpl(@Value("${application.file.ingredietns}") String path) {
        try {
            this.path = Paths.get(path);
            this.objectMapper = new ObjectMapper();
        } catch (InvalidPathException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostConstruct
    public void init() {
        readFromFile();
    }

    public void readFromFile() {
        try {
            byte[] file = Files.readAllBytes(path);
            Map<Long, Ingredient> mapFromFile = objectMapper.readValue(file, new TypeReference<>() {
            });
            ingredientMap.putAll(mapFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToJsonFile() {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(ingredientMap);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        Ingredient newIngredient = ingredientMap.put(this.counter++, ingredient);
        saveToJsonFile();
        return newIngredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            Ingredient newIngredient = ingredientMap.put(id, ingredient);
            saveToJsonFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        Ingredient ingredient = ingredientMap.remove(id);
        saveToJsonFile();
        return ingredient;
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }
}
