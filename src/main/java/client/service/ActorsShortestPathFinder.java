package client.service;

import client.dto.Actor;
import client.dto.Movie;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.springframework.stereotype.Service;

@Service
public class ActorsShortestPathFinder implements PathFinder<Actor, Movie> {


    @Override
    public GraphPath<Actor,Movie> findPath(Graph<Actor, Movie> graph, Actor a, Actor b){

        BellmanFordShortestPath<Actor, Movie> bfsp = new BellmanFordShortestPath<>(graph);
        GraphPath<Actor, Movie> shortestPath = bfsp.getPath(a,b);

        return shortestPath;
    }




}
