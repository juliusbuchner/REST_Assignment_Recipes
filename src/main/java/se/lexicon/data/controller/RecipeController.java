package se.lexicon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import se.lexicon.data.model.dto.RecipeDto;
import se.lexicon.data.model.dto.RecipeIngredientDto;
import se.lexicon.data.model.form.RecipeForm;
import se.lexicon.data.service.RecipeIngredientService;
import se.lexicon.data.service.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeIngredientService ingredientService;

    @Autowired
    public RecipeController(RecipeService recipeService, RecipeIngredientService ingredientService){
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> findAll(){
        System.out.println("### Get Recipes has been found. ###");

        return ResponseEntity.ok(recipeService.findAll());
    }
    @GetMapping("/ingredient")
    public ResponseEntity<List<RecipeIngredientDto>> findAllIngredients(){
        System.out.println("### Get RecipeIngredients has been found. ###");
        return ResponseEntity.ok(ingredientService.findAll());
    }

    @PostMapping
    public ResponseEntity<RecipeDto> create(@RequestBody RecipeForm recipeForm){
        System.out.println("### In Create method");
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.create(recipeForm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> findByRecipeId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(recipeService.findById(id));
    }

    @GetMapping("/search/name")
    public ResponseEntity<RecipeDto> findByRecipeName(@RequestParam("name") String name){
        return ResponseEntity.ok(recipeService.findByName(name));
    }
    @GetMapping("/search/category")
    public ResponseEntity<RecipeDto> findByRecipeCategory(@RequestParam("category") String category){
        return ResponseEntity.ok(recipeService.findByCategory(category));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto> update(@PathVariable("id") Integer id, RecipeForm recipeForm){
        System.out.println("### Delete Method ###");
        System.out.println("id = " + id);
        System.out.println("recipe = " + recipeForm);
        recipeService.update(recipeForm, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        System.out.println("Id: " + id);
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{recipeId}/category/add/{categoryId}")
    public ResponseEntity<RecipeDto> addCategoryToRecipe(@PathVariable("recipeId") Integer recipeId, @PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(recipeService.addCategoryToRecipe(recipeId, categoryId));
    }
    @PutMapping("/{recipeId}/category/remove/{categoryId}")
    public ResponseEntity<RecipeDto> removeCategoryFromRecipe(@PathVariable("recipeId") Integer recipeId, @PathVariable("categoryId") Integer categoryId){
        return ResponseEntity.ok(recipeService.removeCategoryFromRecipe(recipeId, categoryId));
    }
    @PutMapping("/{recipeId}/recipe-ingredient/add/{ingredientId}")
    public ResponseEntity<RecipeDto> addIngredientToRecipe(@PathVariable("recipeId") Integer recipeId, @PathVariable("ingredientId") Integer ingredientId){
        return ResponseEntity.ok(recipeService.addRecipeIngredientToRecipe(recipeId, ingredientId));
    }
    @PutMapping("/{recipeId}/recipe-ingredient/remove/{ingredientId}")
    public ResponseEntity<RecipeDto> removeIngredientFromRecipe(@PathVariable("recipeId") Integer recipeId, @PathVariable("ingredientId") Integer ingredientId){
        return ResponseEntity.ok(recipeService.removeRecipeIngredientFromRecipe(recipeId, ingredientId));
    }
}