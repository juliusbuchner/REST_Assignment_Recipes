package se.lexicon.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.data.model.dto.IngredientDto;
import se.lexicon.data.model.form.IngredientForm;
import se.lexicon.data.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService){
        this.ingredientService = ingredientService;
    }
    @GetMapping
    public ResponseEntity<List<IngredientDto>> findAll(){
        System.out.println("### Get Ingredients has been found. ###");
        return ResponseEntity.ok(ingredientService.findAll());
    }
    @PostMapping
    public ResponseEntity<IngredientDto> create(@RequestBody IngredientForm ingredientForm){
        System.out.println("### In Create method");
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.create(ingredientForm));
    }
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> findByIngredientId(@PathVariable("id") Integer id){
                return ResponseEntity.ok(ingredientService.findById(id));
    }
    @GetMapping("/search")
    public ResponseEntity<IngredientDto> findByRecipeName(@RequestParam("name") String name){
        return ResponseEntity.ok(ingredientService.findByName(name));
    }
    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> update(@PathVariable("id") Integer id, IngredientForm ingredientForm){
        System.out.println("### Delete Method ###");
        System.out.println("id = " + id);
        System.out.println("name = " + ingredientForm);
        ingredientService.update(ingredientForm, id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        System.out.println("Id: " + id);
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
