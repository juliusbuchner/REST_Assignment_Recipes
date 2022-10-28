package se.lexicon.data.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.lexicon.data.model.entity.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
class IngredientRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    Ingredient testIngredient;

    @Autowired
    IngredientRepository repository;

    public List<Ingredient> ingredients(){
        return Arrays.asList(
                new Ingredient("Egg"),
                new Ingredient("Flour"),
                new Ingredient("Milk")
        );
    }

    @BeforeEach
    void setUp(){
        List<Ingredient> persistedIngredient = ingredients().stream()
                .map(entityManager::persist).toList();

        testIngredient = persistedIngredient.get(0);
    }

    @Test
    void findByName() {
        String name = "Egg";

        List<Ingredient> found = new ArrayList<>();

//        found.add(repository.findByName(name));
        Assertions.assertEquals(1,found.size());
    }

    @Test
    void findByNameLike() {
        String name = "eg";

        List<Ingredient> found = new ArrayList<>();

//        found.add(repository.findByName(name));
        Assertions.assertEquals(1,found.size());
    }
}