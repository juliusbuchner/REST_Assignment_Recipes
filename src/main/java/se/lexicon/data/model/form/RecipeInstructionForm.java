package se.lexicon.data.model.form;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeInstructionForm {
    @NotBlank(message = "The instructions can not be blank")
    private String instructions;
}
