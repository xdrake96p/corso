package com.example.Movie.data;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.example.Movie.entita.Genres;
import com.example.Movie.entita.MovieCredit;
import com.example.Movie.entita.MovieDetails;
import com.example.Movie.entita.MovieType;

@Configuration
public class TheMovieDBConfig {

    @Autowired
    private RestTemplate template;

    @Value("${apiKey}")
    private  String API_KEY;

    @Bean(name = "movieType")
    @Scope("prototype")
    public MovieType getMovieType(String name) {
        MovieType quote = template.getForObject(
                "https://api.themoviedb.org/3/movie/" + name + "?api_key="+API_KEY+"&language=it-IT", MovieType.class
        );
        return quote;
    }

    @Bean(name = "genre")//restituisce i film con quel id del genere(azione id:28 ecc )
    @Scope("prototype")
    public Genres getGenres(long id) {
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key="+API_KEY+"&language=it-IT", Genres.class);
    }

    @Bean(name="movieDetails")
    @Scope("prototype")
    public MovieDetails getMovieDetails(long id){
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key="+API_KEY+"&language=it-IT", MovieDetails.class);
    }

    @Bean(name = "movieCredit")
    @Scope("prototype")
    public MovieCredit getMovieCredit(long id){
        return template.getForObject("https://api.themoviedb.org/3/movie/" + id + "/credits?api_key="+API_KEY+"&language=it-IT", MovieCredit.class);
    }

    @Bean(name="searchMovie")
    @Scope("prototype")
    public MovieType getSearchingMovie(String name){
       MovieType type=template.getForObject(
                "https://api.themoviedb.org/3/search/movie?api_key="+API_KEY+"&query="+name+"&language=it-IT",MovieType.class
        );
        return type;
    }
}
