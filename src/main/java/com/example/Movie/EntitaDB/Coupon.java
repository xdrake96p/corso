package com.example.Movie.EntitaDB;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Coupon {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_coupon;
	private String CodiceCoupon;
	private int PercentualeSconto;
	
	//relazione utente 1 *
	@OneToMany
	private List<Utente> utente;
	
	//relazione spettacolo 1 1
	@OneToOne
	@JsonBackReference
	@JoinTable(name = "coupon_spettacolo")
	private Spettacolo spettacolo;
}
