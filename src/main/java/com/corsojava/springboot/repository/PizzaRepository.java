package com.corsojava.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.springboot.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
	public List<Pizza> findByNomeLike(String keyword);

}
