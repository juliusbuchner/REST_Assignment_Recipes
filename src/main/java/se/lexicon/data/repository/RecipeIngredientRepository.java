package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.data.model.entity.RecipeIngredient;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
//    @Query("SELECT i FROM RecipeIngredient i WHERE i.recipeIngredientId = :id")
//    RecipeIngredient findById(@Param("id") int id);
}
