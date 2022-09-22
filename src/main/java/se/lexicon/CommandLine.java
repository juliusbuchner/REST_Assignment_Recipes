package se.lexicon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.data.enums.Measurement;
import se.lexicon.data.repository.IngredientRepository;
import se.lexicon.data.repository.RecipeIngredientRepository;
import se.lexicon.data.repository.RecipeInstructionsRepository;
import se.lexicon.data.repository.RecipeRepository;
import se.lexicon.model.entity.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    public CommandLine(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, RecipeIngredientRepository recipeIngredientRepository, RecipeInstructionsRepository recipeInstructionsRepository, EntityManager entityManager) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeInstructionsRepository = recipeInstructionsRepository;
        this.entityManager = entityManager;
    }

    private final IngredientRepository ingredientRepository;

    private final RecipeRepository recipeRepository;

    private final RecipeIngredientRepository recipeIngredientRepository;

    private final RecipeInstructionsRepository recipeInstructionsRepository;

    private final EntityManager entityManager;

    @Override
    public void run(String... args) throws Exception {
        createNewIngredient("Egg");
        createNewIngredient("Flour");
        createNewIngredient("Milk");
        RecipeIngredient egg = createNewRecipeIngredient("Egg", 3, Measurement.STS);
        RecipeIngredient flour = createNewRecipeIngredient("Flour", 3, Measurement.DL);
        RecipeIngredient milk = createNewRecipeIngredient("Milk", 6, Measurement.DL);
        saveRecipeIngredient(egg);
        saveRecipeIngredient(flour);
        saveRecipeIngredient(milk);
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        addToRecipeIngredientList(egg, recipeIngredients);
        addToRecipeIngredientList(flour, recipeIngredients);
        addToRecipeIngredientList(milk, recipeIngredients);
        RecipeInstruction instruction = createNewInstruction("Mix everything and pour in a pan.");
        saveInstructions(instruction);
        List<RecipeCategory> category = new ArrayList<>();
        createNewCategory(category,"Easy To Make");
        createNewRecipe("Pancakes", recipeIngredients, instruction, category);
    }

    private RecipeInstruction saveInstructions(RecipeInstruction instruction) {
        return recipeInstructionsRepository.save(instruction);
    }

    private Recipe createNewRecipe(String name, List<RecipeIngredient> recipeIngredients, RecipeInstruction instruction, List<RecipeCategory> category) {
        return recipeRepository.save(new Recipe(name, recipeIngredients, instruction, category));
    }

    private boolean createNewCategory(List<RecipeCategory> category, String name) {
        return category.add(new RecipeCategory(name));
    }

    private static RecipeInstruction createNewInstruction(String instructions) {
        return new RecipeInstruction(instructions);
    }

    private static boolean addToRecipeIngredientList(RecipeIngredient ingredient, List<RecipeIngredient> recipeIngredients) {
        return recipeIngredients.add(ingredient);
    }

    private RecipeIngredient saveRecipeIngredient(RecipeIngredient ingredient) {
        return recipeIngredientRepository.save(ingredient);
    }

    private RecipeIngredient createNewRecipeIngredient(String ingredient, int measuredAmount, Measurement measurement) {
        return new RecipeIngredient(ingredientRepository.findByName(ingredient), measuredAmount, measurement);
    }

    private Ingredient createNewIngredient(String ingredient) {
        return ingredientRepository.save(new Ingredient(ingredient));
    }
}