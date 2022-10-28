package se.lexicon.data.service;

import se.lexicon.data.model.dto.RecipeInstructionDto;
import se.lexicon.data.model.form.RecipeInstructionForm;

import java.util.Collection;

public interface RecipeInstructionsService {
    RecipeInstructionDto findById(int id);
    Collection<RecipeInstructionDto> findAll();
    RecipeInstructionDto create(RecipeInstructionForm form);
    RecipeInstructionDto update(RecipeInstructionForm form, Integer id);
    boolean deleteById(int id);
}
