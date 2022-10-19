package com.example.Movie.jpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
	Optional<Sala> findBynomeSala(String nome);
}
