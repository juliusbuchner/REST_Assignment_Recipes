package se.lexicon.data.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.data.exceptions.ResourceNotFoundException;
import se.lexicon.data.model.dto.IngredientDto;
import se.lexicon.data.model.dto.RecipeInstructionDto;
import se.lexicon.data.model.entity.Ingredient;
import se.lexicon.data.model.entity.RecipeInstruction;
import se.lexicon.data.model.form.RecipeInstructionForm;
import se.lexicon.data.repository.RecipeInstructionsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeInstructionsServiceImpl implements RecipeInstructionsService{
    RecipeInstructionsRepository repository;
    ModelMapper modelMapper;

    @Autowired
    public RecipeInstructionsServiceImpl(RecipeInstructionsRepository repository, ModelMapper modelMapper){
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeInstructionDto findById(int id) {
        RecipeInstruction foundById = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Instruction data was not Found"));
        return modelMapper.map(foundById, RecipeInstructionDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<RecipeInstructionDto> findAll() {
        List<RecipeInstruction> instructionList = repository.findAll();
        List<RecipeInstructionDto> instructionDtoList = new ArrayList<>();
        instructionList.forEach((instruction -> instructionDtoList.add(modelMapper.map(instruction, RecipeInstructionDto.class))));
        return instructionDtoList;
    }

    @Override
    @Transactional
    public RecipeInstructionDto create(RecipeInstructionForm form) {
        RecipeInstruction recipeInstruction = repository.save(modelMapper.map(form, RecipeInstruction.class));
        return modelMapper.map(recipeInstruction, RecipeInstructionDto.class);
    }

    @Override
    @Transactional
    public RecipeInstructionDto update(RecipeInstructionForm form, Integer id){
        Optional<RecipeInstruction> foundInstruction = repository.findById(id);
        if (foundInstruction.isPresent()){
            foundInstruction.get().setInstructions(form.getInstructions());
            return modelMapper.map(foundInstruction, RecipeInstructionDto.class);
        } else throw new IllegalArgumentException("Could not find Instruction by Id: " + id);
    }

    @Override
    @Transactional
    public boolean deleteById(int id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
