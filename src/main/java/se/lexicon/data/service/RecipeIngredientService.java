package se.lexicon.data.service;

import se.lexicon.data.model.dto.RecipeIngredientDto;
import se.lexicon.data.model.form.RecipeIngredientForm;

import java.util.List;

public interface RecipeIngredientService {
    RecipeIngredientDto findById(int id);
    List<RecipeIngredientDto> findAll();

    RecipeIngredientDto create(RecipeIngredientForm form);

    RecipeIngredientDto update(RecipeIngredientForm form, Integer id);

    boolean deleteById(int id);
}
