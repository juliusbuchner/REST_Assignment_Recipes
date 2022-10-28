package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.data.model.entity.Recipe;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r WHERE r.recipeId = :id")
    Recipe findById(@Param("id") int id);
    @Query("SELECT r FROM Recipe r WHERE r.recipeName = :name")
    Optional<Recipe> findByRecipeName(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeIngredients) i WHERE i.ingredient.name = :name")
    Optional<Recipe> findByRecipeIngredientsContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeCategories) c WHERE c.category = :name")
    Optional<Recipe> findByRecipeCategories(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeCategories) c WHERE c.category = :name1 AND c.category = :name2")
    Optional<Recipe> findByRecipeCategoriesAndRecipeCategories(@Param("name1") String name1, @Param("name2") String name2);
}
