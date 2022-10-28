package se.lexicon.data.service;

import org.springframework.transaction.annotation.Transactional;
import se.lexicon.data.model.dto.RecipeDto;
import se.lexicon.data.model.form.RecipeForm;

import java.util.List;

public interface RecipeService {
    RecipeDto create(RecipeForm form);
    RecipeDto update(RecipeForm form, Integer id);
    boolean deleteById(Integer id);
    List<RecipeDto> findAll();
    RecipeDto findById(Integer id);
    RecipeDto findByName(String name);
    RecipeDto findByCategory(String category);

    @Transactional
    RecipeDto addCategoryToRecipe(Integer recipeId, Integer categoryId);

    @Transactional
    RecipeDto removeCategoryFromRecipe(Integer recipeId, Integer categoryId);

    @Transactional
    RecipeDto addRecipeIngredientToRecipe(Integer recipeId, Integer ingredientId);

    @Transactional
    RecipeDto removeRecipeIngredientFromRecipe(Integer recipeId, Integer ingredientId);
}
