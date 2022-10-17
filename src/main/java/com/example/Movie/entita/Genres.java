package com.example.Movie.entita;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@ToString
@Setter
@Getter
public class Genres {
    private Value[] genres;
}
