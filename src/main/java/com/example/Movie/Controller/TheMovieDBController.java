package com.example.Movie.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.EntitaDB.Sala;
import com.example.Movie.EntitaDB.Utente;
import com.example.Movie.entita.AllMovies;
import com.example.Movie.entita.MovieDetails;
import com.example.Movie.jpaRepository.CouponRepository;
import com.example.Movie.jpaRepository.FilmRepository;
import com.example.Movie.jpaRepository.SalaRepository;
import com.example.Movie.jpaRepository.SpettacoloRepository;
import com.example.Movie.jpaRepository.StoricoRepository;
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
	private UtenteRepository utenteRepository;
    @Autowired
	private StoricoRepository storicoRepository;
    @Autowired
	private SpettacoloRepository spettacoloRepository;
    @Autowired
	private SalaRepository salaRepository;
    @Autowired
	private FilmRepository filmRepository;
    @Autowired
	private CouponRepository couponRepository;


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
    
    @CrossOrigin(origins="*")
    @GetMapping("/login/{utente}")//stavi facendo qui
    public Utente setUtente(@PathVariable Utente utente){
    	Utente	ut=utenteRepository.findByEmail(utente.getEmail());
    	System.out.println(ut.toString());
    	return null;
    }
    
    @CrossOrigin(origins="*")
    @GetMapping("/sala")//ritorna tutte le sale
    public List<Sala> getSale(){
    	return salaRepository.findAll();
    }
 
    
    
    
}
