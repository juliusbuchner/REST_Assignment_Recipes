package se.lexicon.data.service;

import se.lexicon.data.model.dto.IngredientDto;
import se.lexicon.data.model.form.IngredientForm;

import java.util.List;

public interface IngredientService {
    IngredientDto create(IngredientForm form);
    IngredientDto update(IngredientForm form, Integer id);
    boolean deleteById(Integer id);
    List<IngredientDto> findAll();
    IngredientDto findById(Integer id);
    IngredientDto findByName(String name);
}
