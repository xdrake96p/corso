package com.example.Movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.EntitaDB.Utente;
import com.example.Movie.entita.AllMovies;
import com.example.Movie.entita.MovieDetails;
import com.example.Movie.jpaRepository.UtenteRepository;
import com.example.Movie.servizi.AllMoviesService;
import com.example.Movie.servizi.MovieDetailsService;


@RestController
//@CrossOrigin(allowedHeaders = "*", origins = "*",methods = {RequestMethod.POST,RequestMethod.DELETE,RequestMethod.GET,RequestMethod.PUT})
//@CrossOrigin(origins = "http://localhost:4200")
public class TheMovieDBController {
    @Autowired
    private AllMoviesService moviesService;
    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private MovieDetailsService detailsService;
    
    @CrossOrigin(origins="*")
    @GetMapping("/moviesTypes/{name}")//http://localhost:8080/moviesTypes/top_rated      /now_playing  /popular   /top_rated  /upcoming
    public AllMovies Type(@PathVariable String name) throws Exception { 
        return moviesService.movies(name);
    }
    @CrossOrigin(origins="*")
    @GetMapping("/movieDetails/{id}")//http://localhost:8080/movieDetails/550  restituisce i dettagli del film tramite id
    public MovieDetails details(@PathVariable long id) {
        return detailsService.movieDetails(id);
        
    }

    @PostMapping("/search/{name}")//http://localhost:8080/search/street   //restituisce una lista di film con quel nome iniziale
    public AllMovies SearchingMovie(@PathVariable String name){
        return moviesService.searchingMovies(name);
    }
    @CrossOrigin(origins="*")
    @GetMapping("/user")//ritorna tutti gli utenti
    public List<Utente> getUtenti(){
    	return utenteRepository.findAll();
    }
    @CrossOrigin(origins="*")
    @GetMapping("/user/{id}")//ritorna il singolo utente con quel id
    public Utente getUtente(@PathVariable int id){
    	return utenteRepository.findById(id).get();
    }
    
}
