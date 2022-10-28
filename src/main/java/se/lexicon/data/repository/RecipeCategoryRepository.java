package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.data.model.entity.RecipeCategory;

import java.util.Optional;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, Integer> {
    @Query("SELECT r FROM RecipeCategory r WHERE r.category = :category")
    Optional<RecipeCategory> findByCategory(@Param("category") String category);
}
