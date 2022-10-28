package se.lexicon.data.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.data.exceptions.ResourceNotFoundException;
import se.lexicon.data.model.dto.RecipeDto;
import se.lexicon.data.model.form.RecipeForm;
import se.lexicon.data.model.entity.Recipe;
import se.lexicon.data.repository.RecipeCategoryRepository;
import se.lexicon.data.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{
    RecipeRepository recipeRepository;
    RecipeCategoryRepository categoryRepository;
    ModelMapper modelMapper;
    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper){
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional
    public RecipeDto create(RecipeForm form) {
        Recipe recipe = recipeRepository.save(modelMapper.map(form, Recipe.class));
        return modelMapper.map(recipe, RecipeDto.class);
    }

    @Override
    @Transactional
    public RecipeDto update(RecipeForm form, Integer id) {
        Optional<Recipe> foundRecipe = recipeRepository.findById(id);
        if (foundRecipe.isPresent()){
            foundRecipe.get().setRecipeName(form.getName());
            foundRecipe.get().setRecipeInstructions(form.getRecipeInstructions());
            return modelMapper.map(foundRecipe, RecipeDto.class);
        } else throw new IllegalArgumentException("Could not find Recipe by Id: " + id);
    }

    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        if (recipeRepository.findById(id).isPresent()){
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecipeDto> findAll() {
        List<Recipe> listOfRecipes = recipeRepository.findAll();
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        listOfRecipes.forEach((recipe -> recipeDtoList.add(modelMapper.map(recipe, RecipeDto.class))));
        return recipeDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDto findById(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id can not be null");
        Recipe foundById = recipeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("RecipeIngredient data was not Found"));
        return modelMapper.map(foundById, RecipeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDto findByName(String name) {
        if (name == null) throw new IllegalArgumentException("Name can not be null");
        Recipe foundByName = recipeRepository.findByRecipeName(name).orElseThrow(
                () -> new ResourceNotFoundException("Recipe data was not Found")
        );
        return modelMapper.map(foundByName, RecipeDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeDto findByCategory(String category) {
        if (category == null) throw new IllegalArgumentException("Category can not be null");
        if (categoryRepository.findByCategory(category).isEmpty()) throw new IllegalArgumentException("This recipe has no categories");
        Recipe foundByCategory = recipeRepository.findByRecipeCategories(category).orElseThrow(
                () -> new ResourceNotFoundException("Recipe data was not Found")
        );
        return modelMapper.map(foundByCategory, RecipeDto.class);
    }
}
