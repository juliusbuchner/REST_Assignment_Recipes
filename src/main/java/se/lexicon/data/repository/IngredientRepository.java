package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.lexicon.data.model.entity.Ingredient;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    @Query("SELECT i FROM Ingredient i WHERE i.name = :name")
    Optional<Ingredient> findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE %:name%")
    Optional<Ingredient> findByNameLike(@Param("name") String name);
}
