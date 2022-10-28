package se.lexicon.data.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    @Column(unique = true)
    private String recipeName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction recipeInstructions;

    @ManyToMany(mappedBy = "recipes")
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstructions, List<RecipeCategory> recipeCategories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        setRecipeToRecipeIngredients(recipeIngredients);
        this.recipeInstructions = recipeInstructions;
        this.recipeCategories = recipeCategories;
    }

    public void addRecipeIngredients(RecipeIngredient recipeIngredients) {
        this.recipeIngredients.add(recipeIngredients);
    }

    public void addToRecipeCategories(RecipeCategory recipeCategories) {
        this.recipeCategories.add(recipeCategories);
    }

    public void setRecipeToRecipeIngredients(List<RecipeIngredient> recipeIngredients){
        getRecipeIngredients().forEach(recipeIngredient -> recipeIngredient.setRecipe(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(recipeId, recipe.recipeId) && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(recipeIngredients, recipe.recipeIngredients) && Objects.equals(recipeInstructions, recipe.recipeInstructions) && Objects.equals(recipeCategories, recipe.recipeCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeIngredients, recipeInstructions, recipeCategories);
    }
}
