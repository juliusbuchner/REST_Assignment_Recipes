package se.lexicon.data.model.form;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeCategoryForm {
    private Integer categoryId;

    private String category;
}
