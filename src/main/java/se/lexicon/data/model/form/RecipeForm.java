package se.lexicon.data.model.form;

import lombok.*;
import se.lexicon.data.model.entity.RecipeCategory;
import se.lexicon.data.model.entity.RecipeIngredient;
import se.lexicon.data.model.entity.RecipeInstruction;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeForm {
    @NotBlank(message = "The recipe's name must not be empty.")
    private String name;
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();
    private RecipeInstruction recipeInstructions;
    private List<RecipeCategory> recipeCategories = new ArrayList<>();

}
