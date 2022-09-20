package se.lexicon.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.model.entity.RecipeInstruction;

public interface RecipeInstructionsRepository extends CrudRepository<RecipeInstruction, Integer> {
}
