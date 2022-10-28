package se.lexicon.data.model.form;

import lombok.*;
import se.lexicon.data.enums.Measurement;
import se.lexicon.data.model.entity.Ingredient;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeIngredientForm {
    private Ingredient ingredient;
    private double measuredAmount;
    private Measurement measurement;
}
