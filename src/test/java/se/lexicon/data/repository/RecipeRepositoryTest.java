package se.lexicon.data.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.data.enums.Measurement;
import se.lexicon.model.entity.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    TestEntityManager entityManager;

    Recipe testRecipe;

    public List<Ingredient> ingredients(){
        return Arrays.asList(
                new Ingredient("Egg"),
                new Ingredient("Flour"),
                new Ingredient("Milk")
        );
    }

    public List<RecipeIngredient> recipeIngredients(){
        return Arrays.asList(
                new RecipeIngredient(ingredients().get(0), 3, Measurement.STS),
                new RecipeIngredient(ingredients().get(1), 3, Measurement.DL),
                new RecipeIngredient(ingredients().get(2), 6, Measurement.DL)
        );
    }

    public List<RecipeCategory> recipeCategories(){
        return Arrays.asList(
                new RecipeCategory("Vegetarian"),
                new RecipeCategory("Easy To Make")
        );
    }

    public List<RecipeInstruction> instructions(){
        return Arrays.asList(
                new RecipeInstruction("Put everything together and then pour it in a pan")
        );
    }

    public List<Recipe> recipes(){
        return Arrays.asList(
                new Recipe("Pancakes", recipeIngredients(), instructions().get(0), recipeCategories())
        );
    }

    @BeforeEach
    void setUp(){
        List<Recipe> persistedRecipe = recipes().stream()
                .map(entityManager::persist).toList();

        testRecipe = persistedRecipe.get(0);
    }

    @Test
    void findByRecipeName() {
        String name = "Pancakes";

        List<Recipe> found = new ArrayList<>();
        found.add(recipeRepository.findByRecipeName(name));
        System.out.println(found.get(0));
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(1,found.size());
    }

    @Test
    void findByRecipeIngredientsContainsIgnoreCase() {
        String name = "Egg";

        List<Recipe> found = new ArrayList<>();
        found.add(recipeRepository.findByRecipeIngredientsContainsIgnoreCase(name));
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(1, found.size());
    }

    @Test
    void findByRecipeCategories() {
        String name = "Easy To Make";

        List<Recipe> found = new ArrayList<>();
        found.add(recipeRepository.findByRecipeCategories(name));
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(1, found.size());
    }

    @Test
    void findByRecipeCategoriesAndRecipeCategories() {
        String name1 = "Vegetarian";
        String name2 = "Easy To Make";

        List<Recipe> found = new ArrayList<>();
        found.add(recipeRepository.findByRecipeCategoriesAndRecipeCategories(name1, name2));
        Assertions.assertFalse(found.isEmpty());
        Assertions.assertEquals(1, found.size());
    }
}