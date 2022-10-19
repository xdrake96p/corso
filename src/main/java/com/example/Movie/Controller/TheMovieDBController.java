package com.example.Movie.Controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movie.EntitaDB.Coupon;
import com.example.Movie.EntitaDB.Film;
import com.example.Movie.EntitaDB.Sala;
import com.example.Movie.EntitaDB.Spettacolo;
import com.example.Movie.EntitaDB.Utente;
import com.example.Movie.entita.AllMovies;
import com.example.Movie.entita.FilmRicevutoDaAngular;
import com.example.Movie.entita.MovieDetails;
import com.example.Movie.entita.SalaCreazione;
import com.example.Movie.entita.utenteLoggato;
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

	@PersistenceContext
	EntityManager manager;

	@CrossOrigin(origins = "*")
	@GetMapping("/moviesTypes/{name}") // http://localhost:8080/moviesTypes/top_rated /now_playing /popular /top_rated
										// /upcoming
	public AllMovies Type(@PathVariable String name) throws Exception {
		return moviesService.movies(name);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/movieDetails/{id}") // http://localhost:8080/movieDetails/550 restituisce i dettagli del film
										// tramite id
	public MovieDetails details(@PathVariable long id) {
		return detailsService.movieDetails(id);

	}

	@CrossOrigin(origins = "*")
	@PostMapping("/search/{name}") // http://localhost:8080/search/street //restituisce una lista di film con quel
									// nome iniziale
	public AllMovies SearchingMovie(@PathVariable String name) {
		return moviesService.searchingMovies(name);
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/homefilm")
	public List<Film> getFilmHome() {

		return filmRepository.findAll();

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/filminfo/{id}")
	public List<Spettacolo> getInfoFilmDettaglio(@PathVariable int id) {
		Optional<Film> a = filmRepository.findByidFilmApi(id);
		Film as = a.get();
		List<Spettacolo> spettacoloso = spettacoloRepository.findByFilm(as);
		List<Spettacolo> supporto= new ArrayList<>();
		LocalDate now = LocalDate.now();
		for(Spettacolo ciclo : spettacoloso) {
			if(ciclo.getDataSpettacolo().isBefore(now)) {
				supporto.add(ciclo);
			}
		}
		spettacoloso.removeAll(supporto);

		return spettacoloso;

	}

	@CrossOrigin(origins = "*")
	@GetMapping("/user") // ritorna tutti gli utenti
	public List<Utente> getUtenti() {
		return utenteRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/user/{id}") // ritorna il singolo utente con quel id
	public Utente getUtente(@PathVariable int id) {
		return utenteRepository.findById(id).get();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public ResponseEntity<Object> setLoginUtente(@RequestBody utenteLoggato utente) {
		Optional<Utente> i = Optional.ofNullable(utenteRepository.findByEmail(utente.getEmail()));
		if (i.isPresent()) {
			Utente loggo = i.get();
			if (loggo.getPassword().equals(utente.getPassword())) {
				return new ResponseEntity<Object>(loggo, HttpStatus.OK);
			}
			return new ResponseEntity<Object>(i, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(i, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/sala") // ritorna tutte le sale
	public List<Sala> getSale() {
		return salaRepository.findAll();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/registrazione")
	public ResponseEntity<Object> registrazione(@RequestBody Utente u) { // mettere controllo dell email
		Optional<Utente> i = Optional.ofNullable(utenteRepository.findByEmail(u.getEmail()));
		if(i.isPresent()) {
			return new ResponseEntity<Object>(u, HttpStatus.BAD_REQUEST);
		}else {
			utenteRepository.save(u);
		return new ResponseEntity<Object>(u, HttpStatus.OK);
		}
		
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/addFilmRepository")
	public ResponseEntity<Object> addFilm(@RequestBody FilmRicevutoDaAngular filmRicevutoDaAngular) { // da finire manca
																										// la sala
		MovieDetails m = details(filmRicevutoDaAngular.getId());
		Optional<Film> filmoso = filmRepository.findByidFilmApi(filmRicevutoDaAngular.getId());

		if (filmoso.isPresent()) {
			Optional<Sala> sala= salaRepository.findById(filmRicevutoDaAngular.getSala());
			
			Sala salosa=sala.get();
			
			Spettacolo s = new Spettacolo();
			s.setDataSpettacolo(filmRicevutoDaAngular.getData());
			s.setOrario(filmRicevutoDaAngular.getOrario());
			s.setPrezzoSpettacolo(filmRicevutoDaAngular.getPrezzoBiglietto());
			s.setFilm(filmoso.get());
			spettacoloRepository.save(s);
			salosa.setSpettacolo(s);
			salaRepository.save(salosa);
			
		} else {// il film non è presente nel db e quindi l aggiungo e creo anche l evento
			Film a = new Film();
			Optional<Sala> sala= salaRepository.findById(filmRicevutoDaAngular.getSala());
			Sala salosa=sala.get();
			a.setNomeFilm(m.getOriginal_title());
			a.setDurataFilm(m.getRuntime());
			a.setDescrizione(m.getOverview());
			a.setIdFilmApi((int) m.getId());
			a.setLocandina(m.getPoster_path());
			filmRepository.save(a);
			Spettacolo s = new Spettacolo();
			s.setDataSpettacolo(filmRicevutoDaAngular.getData());
			s.setOrario(filmRicevutoDaAngular.getOrario());
			s.setPrezzoSpettacolo(filmRicevutoDaAngular.getPrezzoBiglietto());
			s.setFilm(a);
			spettacoloRepository.save(s);
			salosa.setSpettacolo(s);
			salaRepository.save(salosa);
		}
		return new ResponseEntity<Object>("", HttpStatus.OK);

	}

	/*@CrossOrigin(origins = "*")
	@GetMapping("/ritornafilm")
	public List<Film> getFilm() { // funziona forse lo miglioro

		return null;

	}*/

	@CrossOrigin(origins = "*")
	@GetMapping("/ritornaSpettacolo")
	public List<Spettacolo> getSpettacolo() { // funziona forse lo miglioro
		return spettacoloRepository.findAll();

	}

	@CrossOrigin(origins = "*")
	@PostMapping("/addCoupon")
	public ResponseEntity<Object> addCoupon(@RequestBody CouponRicevutoDaAngular id) {// sembra che funziona
		Coupon coupon = new Coupon();
		coupon.setCodiceCoupon(id.getNomeCoupon());
		coupon.setPercentualeSconto(id.getValoreCoupon());
		Spettacolo s = spettacoloRepository.getById(id.getIdFIlmDaApplicareCoupon());
		coupon.setSpettacolo(s);
		couponRepository.save(coupon);
		return new ResponseEntity<Object>("", HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/inserisciSala")
	public ResponseEntity<Object> addSala(@RequestBody SalaCreazione sala) {// sembra che funziona
		System.out.println(sala);
		Optional<Sala>optional = salaRepository.findBynomeSala(sala.getNome());
		if(optional.isPresent()) {
			return new ResponseEntity<Object>(sala, HttpStatus.BAD_GATEWAY);
		}else {
			Sala salosa= new Sala();
			salosa.setNomeSala(sala.getNome());
			salosa.setPosti(sala.getNumerSala());
			salosa.setPostiDisponibili(sala.getNumerSala());
			
			salaRepository.save(salosa);
		}
		return new ResponseEntity<Object>(sala, HttpStatus.OK);
	}

}
