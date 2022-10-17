package com.example.Movie.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {

}
