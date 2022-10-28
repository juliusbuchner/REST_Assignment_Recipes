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
    private List<RecipeIngredient> recipeIngredients;

    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction recipeInstructions;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "recipe_recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<RecipeCategory> recipeCategories;

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstructions, List<RecipeCategory> recipeCategories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        setRecipeToRecipeIngredients(recipeIngredients);
        this.recipeInstructions = recipeInstructions;
        setCategoriesToRecipe(recipeCategories);
    }

    public void setCategoriesToRecipe(List<RecipeCategory> recipeCategories){
        this.recipeCategories = recipeCategories;
        getRecipeCategories().forEach(recipeCategory -> recipeCategory.addRecipe(this));
    }

    public void addToRecipeCategories(RecipeCategory recipeCategory) {
        this.recipeCategories.add(recipeCategory);
        recipeCategory.addRecipe(this);
    }
    public void removeFromRecipeCategories(RecipeCategory recipeCategory){
        this.recipeCategories.remove(recipeCategory);
        recipeCategory.removeRecipe(this);
    }

    public void setRecipeToRecipeIngredients(List<RecipeIngredient> recipeIngredients){
        this.recipeIngredients = recipeIngredients;
        getRecipeIngredients().forEach(recipeIngredient -> recipeIngredient.setRecipe(this));
    }
    public void addToListOfRecipeIngredients(RecipeIngredient ingredient){
        recipeIngredients.add(ingredient);
        ingredient.setRecipe(this);
    }
    public void removeFromListOfRecipeIngredients(RecipeIngredient ingredient){
        recipeIngredients.remove(ingredient);
        ingredient.setRecipe(null);
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
