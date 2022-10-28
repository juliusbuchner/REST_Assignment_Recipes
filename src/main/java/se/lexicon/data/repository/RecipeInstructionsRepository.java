package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.data.model.entity.RecipeInstruction;

public interface RecipeInstructionsRepository extends JpaRepository<RecipeInstruction, Integer> {
}
