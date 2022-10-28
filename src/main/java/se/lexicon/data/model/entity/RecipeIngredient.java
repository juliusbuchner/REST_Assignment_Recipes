package se.lexicon.data.model.entity;

import lombok.*;
import se.lexicon.data.enums.Measurement;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recipeIngredientId;

    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    private Ingredient ingredient;

    private double measuredAmount;

    private Measurement measurement;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private Recipe recipe;


    public RecipeIngredient(Ingredient ingredient, double measuredAmount, Measurement measurement) {
        this.ingredient = ingredient;
        this.measuredAmount = measuredAmount;
        this.measurement = measurement;
    }
}
