package com.corsojava.springboot.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "offerte")
public class Offerta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String titolo;
	
	private LocalDate inizioOfferta;
	
	private LocalDate fineOfferta;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	private Pizza pizza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getInizioOfferta() {
		return inizioOfferta;
	}

	public void setInizioOfferta(LocalDate inizioOfferta) {
		this.inizioOfferta = inizioOfferta;
	}

	public LocalDate getFineOfferta() {
		return fineOfferta;
	}

	public void setFineOfferta(LocalDate fineOfferta) {
		this.fineOfferta = fineOfferta;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
