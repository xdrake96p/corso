package com.example.Movie.EntitaDB;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity

public class Film {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String nomeFilm;
	private String Descrizione;
	private short DurataFilm;
	private String locandina;
	private Integer idFilmApi;
	
	//relazione spettacolo 1 1 DA AGGIUSTARE
	@OneToOne
	@JoinTable(name="spettacolo_film")
	@JsonIgnore
	private Spettacolo spettacolo;




	


	
	
}
