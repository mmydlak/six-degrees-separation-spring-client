package client.service;

import client.dto.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class MovieLookupService {

    private final static HashMap<String, Movie> moviesContainer = new HashMap<>();
    private RestTemplate restTemplate;

    @Autowired
    public MovieLookupService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public Movie getMovieByID(String id) {
        if(moviesContainer.containsKey(id)){
            return moviesContainer.get(id);
        } else {
            String path = "https://java.kisim.eu.org/movies/" + id;
            Movie movie =  restTemplate.getForObject(path, Movie.class);
            moviesContainer.put(id, movie);
            return movie;
        }
    }

    public Movie[] getMoviesByActorID(String id) {
        String path = "https://java.kisim.eu.org/actors/" + id + "/movies";
        return restTemplate.getForObject(path, Movie[].class);
    }

}
