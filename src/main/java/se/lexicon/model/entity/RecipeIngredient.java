package se.lexicon.model.entity;

import se.lexicon.data.enums.Measurement;

import javax.persistence.*;

@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeIngredientId;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Ingredient ingredient;

    private double measuredAmount;

    private Measurement measurement;

    @ManyToOne
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Ingredient ingredient, double measuredAmount, Measurement measurement, Recipe recipe) {
        this.ingredient = ingredient;
        this.measuredAmount = measuredAmount;
        this.measurement = measurement;
        this.recipe = recipe;
    }

    public Integer getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getMeasuredAmount() {
        return measuredAmount;
    }

    public void setMeasuredAmount(double measuredAmount) {
        this.measuredAmount = measuredAmount;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
