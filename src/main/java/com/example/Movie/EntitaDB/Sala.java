package com.example.Movie.EntitaDB;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Sala {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private int  posti;
	private String nomeSala;
	private int postiDisponibili;
	
	//relazione con spettacolo * 1
	@ManyToOne
	@JoinColumn(name = "id_spettacolo")
	private Spettacolo	 spettacolo;
}
