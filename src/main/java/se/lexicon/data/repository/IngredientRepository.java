package se.lexicon.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lexicon.model.entity.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    @Query("SELECT i FROM Ingredient i WHERE i.name = :name")
    Ingredient findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE %:name%")
    Ingredient findByNameLike(@Param("name") String name);
}
