package com.example.Movie.jpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Movie.EntitaDB.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	  public Utente findByEmail(String email);
}
