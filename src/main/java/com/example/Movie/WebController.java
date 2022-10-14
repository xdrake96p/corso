package com.example.Movie;

import org.springframework.stereotype.Controller;

import com.example.Movie.jpaRepository.CouponRepository;
import com.example.Movie.jpaRepository.FilmRepository;
import com.example.Movie.jpaRepository.SalaRepository;
import com.example.Movie.jpaRepository.SpettacoloRepository;
import com.example.Movie.jpaRepository.StoricoRepository;
import com.example.Movie.jpaRepository.UtenteRepository;

@Controller
public class WebController {

	private UtenteRepository utenteRepository;
	private StoricoRepository storicoRepository;
	private SpettacoloRepository spettacoloRepository;
	private SalaRepository salaRepository;
	private FilmRepository filmRepository;
	private CouponRepository couponRepository;

}
