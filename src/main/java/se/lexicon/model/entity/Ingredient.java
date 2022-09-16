package se.lexicon.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    @Column(nullable = false, unique = true)
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(ingredientId, that.ingredientId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + ingredientId +
                ", name='" + name + '\'' +
                '}';
    }
}
