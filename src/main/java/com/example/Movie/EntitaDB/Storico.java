package com.example.Movie.EntitaDB;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Storico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_storico;
	private String NomeSala;
	private String NomeFilm;
	private Integer posto;
	private BigDecimal prezzo;
	private Time orario;
	private String TipoPagamento;

	// private Utente ute;
	/*
	 * enum TipoPagamento { Contati, Carta, Online };
	 */

	// relazione con spettacolo * * funziona
	@ManyToMany(mappedBy = "storico")
	private List<Spettacolo> spettacolo;

	// relazione con utente * 1 funziona 
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_utente")
	private Utente utente;
}
