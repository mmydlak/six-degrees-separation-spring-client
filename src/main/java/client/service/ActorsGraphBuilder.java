package client.service;

import client.dto.Actor;
import client.dto.Movie;
import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class ActorsGraphBuilder implements ConnectionGraphBuilder<Actor, Movie> {

    private final
    MovieLookupService movieService;

    @Autowired
    public ActorsGraphBuilder(MovieLookupService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Graph<Actor, Movie> buildConnectionGraph(Actor a, Actor b) {
        Graph<Actor, Movie> graph = new SimpleGraph<>(Movie.class);
        List<Movie> aMovies = new LinkedList<>();
        List<Actor> actorsTmp = new LinkedList<>();
        List<Actor> actorsToHandle = new LinkedList<>();

        graph.addVertex(a);
        graph.addVertex(b);
        actorsToHandle.add(a);

        Movie m;
        Actor ac;
        boolean found = false;
        while(!found) {
            Actor removed = actorsToHandle.remove(0);
            aMovies.addAll(Arrays.asList(movieService.getMoviesByActorID(removed.getId())));

            for (int i = 0; i < aMovies.size(); i++) {
                aMovies.set(i, movieService.getMovieByID(aMovies.get(i).getId()));
                m = aMovies.get(i);

                if (graph.containsEdge(m)) {
                    aMovies.remove(m);
                    i--;
                } else {
                    actorsTmp.addAll(Arrays.asList(m.getActors()));

                    for (int j = 0; j < actorsTmp.size(); j++) {
                        ac = actorsTmp.get(j);
                        if (graph.containsVertex(ac)) {
                            if (ac.equals(b)) {
                                graph.addEdge(removed, ac, (Movie)m.clone());
                                found = true;
                            }
                        } else {
                            graph.addVertex(ac);
                            graph.addEdge(removed, ac, (Movie)m.clone());
                            actorsToHandle.add(ac);
                        }
                    }
                    actorsTmp.clear();
                }
            }
            aMovies.clear();
        }
        return graph;
    }

}
