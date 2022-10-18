package com.example.Movie.jpaRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Movie.EntitaDB.Film;
import com.example.Movie.EntitaDB.Spettacolo;

public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer> {
		Optional<Spettacolo>findByFilm(Film film);
}
