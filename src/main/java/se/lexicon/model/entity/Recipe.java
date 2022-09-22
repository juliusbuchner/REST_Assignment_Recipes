package se.lexicon.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    private String recipeName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH}, mappedBy = "recipe")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private RecipeInstruction recipeInstructions;

    @ManyToMany(mappedBy = "recipes")
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String recipeName, List<RecipeIngredient> recipeIngredients, RecipeInstruction recipeInstructions, List<RecipeCategory> recipeCategories) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        setRecipeToRecipeIngredients(recipeIngredients);
        this.recipeInstructions = recipeInstructions;
        this.recipeCategories = recipeCategories;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void addRecipeIngredients(RecipeIngredient recipeIngredients) {
        this.recipeIngredients.add(recipeIngredients);
    }

    public RecipeInstruction getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(RecipeInstruction recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    public List<RecipeCategory> getRecipeCategories() {
        return recipeCategories;
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

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeCategories=" + recipeCategories +
                ", recipeIngredients=" + recipeIngredients +
                ", recipeInstructions=" + recipeInstructions +
                '}';
    }
}
