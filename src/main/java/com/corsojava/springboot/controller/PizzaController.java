package com.corsojava.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.springboot.model.Pizza;
import com.corsojava.springboot.repository.PizzaRepository;

@RestController
@CrossOrigin
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@GetMapping()	// Gestisce GET di /pizze
	public List<Pizza> index() {
		return pizzaRepository.findAll();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> show(@PathVariable("id") Integer id) {
		Optional<Pizza> result = pizzaRepository.findById(id);
		if (result.isPresent()) {
			return new ResponseEntity<Pizza>(result.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/create")	// Gestisce POST di /pizze/create
	public Pizza create(@RequestBody Pizza pizza) {
		return pizzaRepository.save(pizza);
	}
	
	@PutMapping("{id}") //	Essendo di tipo PUT non va in conflitto con il dettaglio (tipo GET) pur avendo stesso indirizzo
	public Pizza edit(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		Pizza pizzaResult = pizzaRepository.getReferenceById(id);
		pizzaResult.setNome(pizza.getNome());
		pizzaResult.setDescrizione(pizza.getDescrizione());
		pizzaResult.setPrezzo(pizza.getPrezzo());
		pizzaResult.setFoto(pizza.getFoto());
		return pizzaRepository.save(pizzaResult);
	}
	
	@DeleteMapping("{id}")	// Tipo DELETE di /pizze/{id}
	public void delete(@PathVariable("id") Integer id) {
		pizzaRepository.deleteById(id);
	}

}