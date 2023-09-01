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
}
