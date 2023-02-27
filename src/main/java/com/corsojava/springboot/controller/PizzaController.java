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
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<List<Pizza>> index(@RequestParam(name = "filtro", required = false) String filtro) {
		
		List<Pizza> result;
		if (filtro!=null && !filtro.isEmpty()) {
			result = pizzaRepository.findByNomeLike("%"+filtro+"%");
		} else {
			result = pizzaRepository.findAll();
		}
		
		if (result.size() > 0)
			return new ResponseEntity<List<Pizza>>(result, HttpStatus.OK);
		else
			return new ResponseEntity<List<Pizza>>(HttpStatus.NO_CONTENT);
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
	public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {
		return new ResponseEntity<Pizza>( pizzaRepository.save(pizza), HttpStatus.OK);
	}
	
	@PutMapping("{id}") //	Essendo di tipo PUT non va in conflitto con il dettaglio (tipo GET) pur avendo stesso indirizzo
	public ResponseEntity<Pizza> edit(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		
		Optional<Pizza> result = pizzaRepository.findById(id);

		if (!result.isPresent()) {		
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		} else {
			pizzaRepository.save(pizza);
			return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
		}    	
	}
	
	@DeleteMapping("{id}")	// Tipo DELETE di /pizze/{id}
	public ResponseEntity<Pizza> delete(@PathVariable("id") Integer id) {
		
		Optional<Pizza> pizzaResult = pizzaRepository.findById(id);		
		if (pizzaResult.isPresent()) {			
			pizzaRepository.deleteById(id);
			return new ResponseEntity<Pizza>( HttpStatus.OK);
		}			
	    else
	    	return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
	}

}