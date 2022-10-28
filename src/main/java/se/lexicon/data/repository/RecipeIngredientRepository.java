package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.data.model.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
}
