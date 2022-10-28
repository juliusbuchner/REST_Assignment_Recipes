package se.lexicon.data.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.data.exceptions.ResourceNotFoundException;
import se.lexicon.data.model.dto.RecipeIngredientDto;
import se.lexicon.data.model.form.RecipeIngredientForm;
import se.lexicon.data.model.entity.RecipeIngredient;
import se.lexicon.data.repository.RecipeIngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService{
    RecipeIngredientRepository repository;
    ModelMapper modelMapper;
    @Autowired
    public RecipeIngredientServiceImpl(RecipeIngredientRepository repository, ModelMapper modelMapper){
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeIngredientDto findById(int id) {
        RecipeIngredient foundById = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("RecipeIngredient data was not Found"));
        return modelMapper.map(foundById, RecipeIngredientDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeIngredientDto> findAll() {
        List<RecipeIngredient> listOfRecipesIngredient = repository.findAll();
        List<RecipeIngredientDto> recipeIngredientDtoList = new ArrayList<>();
        listOfRecipesIngredient.forEach((ingredient -> recipeIngredientDtoList.add(modelMapper.map(ingredient, RecipeIngredientDto.class))));
        return recipeIngredientDtoList;
    }

    @Override
    @Transactional
    public RecipeIngredientDto create(RecipeIngredientForm form) {
        RecipeIngredient recipeIngredient = repository.save(modelMapper.map(form, RecipeIngredient.class));
        return modelMapper.map(recipeIngredient, RecipeIngredientDto.class);
    }

    @Override
    @Transactional
    public RecipeIngredientDto update(RecipeIngredientForm form, Integer id){
        Optional<RecipeIngredient> foundIngredient = repository.findById(id);
        if (foundIngredient.isPresent()){
            foundIngredient.get().setIngredient(form.getIngredient());
            foundIngredient.get().setMeasurement(form.getMeasurement());
            foundIngredient.get().setMeasuredAmount(form.getMeasuredAmount());
            return modelMapper.map(foundIngredient, RecipeIngredientDto.class);
        } else throw new IllegalArgumentException("Could not find RecipeIngredient by Id: " + id);
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
