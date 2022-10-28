package se.lexicon.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.data.model.dto.RecipeIngredientDto;
import se.lexicon.data.model.form.RecipeIngredientForm;
import se.lexicon.data.service.RecipeIngredientService;

import java.util.List;

@RestController
@RequestMapping("/recipe-ingredient")
public class RecipeIngredientController {
    private final RecipeIngredientService service;
    public RecipeIngredientController(RecipeIngredientService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<RecipeIngredientDto> create(@RequestBody RecipeIngredientForm form){
        System.out.println("### In Create Method ###");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(form));
    }
    @GetMapping
    public ResponseEntity<List<RecipeIngredientDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
