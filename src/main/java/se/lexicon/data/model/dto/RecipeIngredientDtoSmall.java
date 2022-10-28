package se.lexicon.data.model.dto;

import lombok.*;
import se.lexicon.data.enums.Measurement;
import se.lexicon.data.model.entity.Ingredient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeIngredientDtoSmall {
    private int recipeIngredientId;
    private Ingredient ingredient;
    private double measuredAmount;
    private Measurement measurement;
}
