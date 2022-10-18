package com.example.Movie.EntitaDB;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	//@OneToMany
	@ManyToOne
	@JoinColumn(name="spettacolo_film",referencedColumnName = "idFilm")
	//@JoinColumn(name="spettacolo_film",referencedColumnName = "idFilm")
	private  Film film;
	
	//relazione utente * 1 
	@ManyToMany
	@JoinColumn(name = "id_utente")
	private List<Utente> utente;
	
	

	
	
	//relazione con sala 1 *
	@OneToMany
	private List<Sala> sala;
	
	

	//relazione Coupon 1 a 1
	@OneToOne(mappedBy = "spettacolo")
	@JsonManagedReference
	private Coupon coupon;
	
	
	//relazione storico * *
	@ManyToMany
	private List<Storico> storico;
}
