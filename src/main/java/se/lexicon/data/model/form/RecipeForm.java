package se.lexicon.data.model.form;

import lombok.*;
import se.lexicon.data.model.entity.RecipeCategory;
import se.lexicon.data.model.entity.RecipeInstruction;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeForm {
    @NotBlank(message = "The recipe's name can not be blank.")
    private String name;
    private RecipeInstruction recipeInstructions;
    private Set<RecipeCategory> recipeCategory;

}
