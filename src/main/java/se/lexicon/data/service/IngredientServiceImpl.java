package se.lexicon.data.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.data.exceptions.ResourceNotFoundException;
import se.lexicon.data.model.dto.IngredientDto;
import se.lexicon.data.model.form.IngredientForm;
import se.lexicon.data.repository.IngredientRepository;
import se.lexicon.data.model.entity.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService{
    IngredientRepository repository;
    ModelMapper modelMapper;

    @Autowired
    public IngredientServiceImpl(IngredientRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IngredientDto create(IngredientForm form) {
        Ingredient ingredient = repository.save(modelMapper.map(form, Ingredient.class));
        return modelMapper.map(ingredient, IngredientDto.class);
    }

    @Override
    public IngredientDto update(IngredientForm form, Integer id) {
        Optional<Ingredient> foundIngredient = repository.findById(id);
        if (foundIngredient.isPresent()){
            foundIngredient.get().setName(form.getName());
            return modelMapper.map(foundIngredient, IngredientDto.class);
        } else throw new IllegalArgumentException("Could not find Ingredient by Id: " + id);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<IngredientDto> findAll() {
        List<Ingredient> listOfIngredients = repository.findAll();
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        listOfIngredients.forEach((ingredient -> ingredientDtoList.add(modelMapper.map(ingredient, IngredientDto.class))));
        return ingredientDtoList;
    }

    @Override
    public IngredientDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id can not be null");
        Ingredient foundById = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient data was not Found")
        );
        return modelMapper.map(foundById, IngredientDto.class);
    }

    @Override
    public IngredientDto findByName(String name) {
        if (name == null) throw new IllegalArgumentException("Name can not be null");
        Ingredient foundByName = repository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Ingredient data was not Found")
        );
        return modelMapper.map(foundByName, IngredientDto.class);
    }
}
