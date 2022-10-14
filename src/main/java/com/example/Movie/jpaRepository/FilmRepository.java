package com.example.Movie.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Film;

public interface FilmRepository  extends JpaRepository<Film, Integer>{

}
