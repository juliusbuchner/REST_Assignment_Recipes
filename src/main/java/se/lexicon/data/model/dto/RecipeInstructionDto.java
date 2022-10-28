package se.lexicon.data.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecipeInstructionDto {
    private String instructionId;
    private String instructions;
}
