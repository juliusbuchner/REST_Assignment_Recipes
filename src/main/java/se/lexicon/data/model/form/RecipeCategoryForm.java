package se.lexicon.data.model.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeCategoryForm {
    @NotBlank(message = "The category's name can not be blank")
    private String category;
}
