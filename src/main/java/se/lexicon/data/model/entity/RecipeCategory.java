package se.lexicon.data.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String category;

    @ManyToMany(mappedBy = "recipeCategories")
    private List<Recipe> recipes;


    public RecipeCategory(String category) {
        this.category = category;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }
    public void removeRecipe(Recipe recipe){
        this.recipes.remove(recipe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCategory that = (RecipeCategory) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(category, that.category) && Objects.equals(recipes, that.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, category, recipes);
    }
}
