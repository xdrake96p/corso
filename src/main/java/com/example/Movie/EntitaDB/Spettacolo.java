package com.example.Movie.EntitaDB;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
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

import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Spettacolo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_spettacolo;
	private LocalDate dataSpettacolo;
	private String orario; //non so il perche'
	private BigDecimal prezzoSpettacolo;
	
	//relazione film 1 1 
	@OneToOne(mappedBy = "spettacolo")
	//@JoinColumn(name="id_film")
	//@OneToOne(mappedBy = "spettacolo")
	private Film film;
	
	//relazione utente * 1 
	@ManyToMany
	@JoinColumn(name = "id_utente")
	private List<Utente> utente;
	
	

	
	
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
