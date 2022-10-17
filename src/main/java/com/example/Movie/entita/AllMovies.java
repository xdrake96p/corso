package com.example.Movie.entita;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ToString
public class AllMovies {
    private MovieType movieType;
    private Genres genres[];
}
