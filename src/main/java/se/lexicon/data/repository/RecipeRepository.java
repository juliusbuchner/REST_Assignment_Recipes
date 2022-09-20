package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.model.entity.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r WHERE r.recipeName = :name")
    Recipe findByRecipeName(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeIngredients) i WHERE i.ingredient.name = :name")
    Recipe findByRecipeIngredientsContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeCategories) c WHERE c.category = :name")
    Recipe findByRecipeCategories(@Param("name") String name);

    @Query("SELECT r FROM Recipe r, IN (r.recipeCategories) c WHERE c.category = :name1 AND c.category = :name2")
    Recipe findByRecipeCategoriesAndRecipeCategories(@Param("name1") String name1, @Param("name2") String name2);
}
