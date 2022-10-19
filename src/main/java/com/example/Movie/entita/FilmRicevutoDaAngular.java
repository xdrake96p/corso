package com.example.Movie.entita;

import java.math.BigDecimal;
import java.time.LocalDate;


import lombok.Data;

@Data
public class FilmRicevutoDaAngular {
	private int id;
	private LocalDate data;
	private String orario;
	private int sala;
	private BigDecimal prezzoBiglietto;

}
