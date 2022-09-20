package se.lexicon.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.model.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, Integer> {
}
