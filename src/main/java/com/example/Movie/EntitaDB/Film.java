package com.example.Movie.EntitaDB;

import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Film {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nomeFilm;
	private String Descrizione;
	private Time DurataFilm;
	private String locandina;
	private String trailer;
	private Integer idFilmApi;
	
	//relazione spettacolo 1 * 
	@OneToMany
	private List<Spettacolo> spettacolo;
	
}
