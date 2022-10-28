package se.lexicon.data.model.dto;

import lombok.*;
import se.lexicon.data.model.entity.Recipe;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeCategoryDto {
    private Integer categoryId;
    private String category;
    private List<Recipe> recipes = new ArrayList<>();
}
