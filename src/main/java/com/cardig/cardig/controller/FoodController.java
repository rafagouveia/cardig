package com.cardig.cardig.controller;

import com.cardig.cardig.food.Food;
import com.cardig.cardig.food.FoodRepository;
import com.cardig.cardig.food.FoodRequestDTO;
import com.cardig.cardig.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository repository;

    @GetMapping("/{id}")
    public FoodResponseDTO getFood(@PathVariable Long id){

        Food food = repository.findById(id).orElse(null);
        if (food == null){
            return null;
        }
        return new FoodResponseDTO(food);
    }

    @GetMapping
    public List<FoodResponseDTO> getAll(){

        return repository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO  data){
        Food foodData = new Food(data);
        repository.save(foodData);
        return;
    }

    @PutMapping("/{id}")
    public void updateFood(@RequestBody FoodRequestDTO data, @PathVariable Long id){
        Food foodData = new Food(data);
        repository.findById(id).map(food -> {
            food.setTitle(foodData.getTitle());
            food.setImage(foodData.getImage());
            food.setPrice(foodData.getPrice());
            return repository.save(food);
        }).orElseGet(() -> {
            return repository.save(foodData);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id){
        repository.deleteById(id);
    }
}
