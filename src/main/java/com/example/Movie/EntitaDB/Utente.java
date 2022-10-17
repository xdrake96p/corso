package com.example.Movie.EntitaDB;


import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Utente {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id_utente;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String email;
	private String password;
	private String TipoUtente;
	//enum TipoUtente{Registrato,Admin,nonRegistrato};
	
	//relazione con spettacolo 1 *
	@ManyToMany
	private List<Spettacolo> spettacolo;
	
	//relazione coupon * 1
	@ManyToOne
	@JoinColumn(name="id_coupon")
	private Coupon coupons;
	
	//relazionee storico 1 *
	@OneToMany
	private List<Storico> storico;
	
}
