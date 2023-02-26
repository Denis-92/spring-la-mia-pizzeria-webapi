package com.corsojava.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springboot.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
