package com.example.Movie.jpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Film;

public interface FilmRepository  extends JpaRepository<Film, Integer>{
	Optional<Film> findByidFilmApi(Integer id);
	Optional<Film> findBynomeFilm(String nomeFilm);
	
}
