package se.lexicon.data.model.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IngredientForm {
    @NotBlank(message = "The ingredient's name must not be empty.")
    private String name;
}
