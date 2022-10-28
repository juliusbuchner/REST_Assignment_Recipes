package se.lexicon.data.model.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeCategoryDto {
    private Integer categoryId;
    private String category;
    private List<RecipeDtoSmall> recipes;
}
