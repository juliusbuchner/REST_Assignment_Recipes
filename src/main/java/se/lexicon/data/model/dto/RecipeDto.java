package se.lexicon.data.model.dto;

import lombok.*;
import se.lexicon.data.model.entity.RecipeCategory;
import se.lexicon.data.model.entity.RecipeIngredient;
import se.lexicon.data.model.entity.RecipeInstruction;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeDto {
    private Integer recipeId;
    private String recipeName;
    private List<RecipeIngredient> recipeIngredients;
    private RecipeInstruction recipeInstructions;
    private List<RecipeCategory> recipeCategories;
}
