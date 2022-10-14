package com.example.Movie.EntitaDB;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Spettacolo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_spettacolo;
	private Date dataSpettacolo;
	private Time orario;
	private BigDecimal prezzoSpettacolo;
	
	//relazione utente * 1 
	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;
	
	
	//relazione film * 1 
	@ManyToOne
	@JoinColumn(name="id_film")
	private Film film;
	
	
	
	//relazione con sala 1 *
	@OneToMany
	private List<Sala> sala;
	
	
	
	
	//relazione Coupon 1 a 1
	@OneToOne(mappedBy = "spettacolo")
	private Coupon coupon;
	
	
	//relazione storico * *
	@ManyToMany
	private List<Storico> storico;
}
