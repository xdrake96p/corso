package com.example.Movie.jpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Spettacolo;

public interface SpettacoloRepository extends JpaRepository<Spettacolo, Integer> {

}
