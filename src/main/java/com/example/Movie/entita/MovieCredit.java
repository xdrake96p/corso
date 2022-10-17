package com.example.Movie.entita;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@ToString
public class MovieCredit {
    private Cast[] cast;
    private Crew[]crew;

	
}
