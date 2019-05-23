package client.utils;

import client.dto.Actor;
import client.dto.LightMovie;
import client.dto.Movie;
import client.dto.ActorMoviePair;
import org.jgrapht.GraphPath;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphPathConverter implements PathToArrayConverter<Actor,Movie,ActorMoviePair> {


    @Override
    public ActorMoviePair[] toArray(GraphPath<Actor, Movie> gp) {

        List<Movie> edges = gp.getEdgeList();
        List<Actor> vertex = gp.getVertexList();
        ActorMoviePair[] result = new ActorMoviePair[vertex.size()];

        for(int i = 0; i < vertex.size(); ++i){
            if(i == vertex.size()-1)
                result[i] = new ActorMoviePair(vertex.get(i),null);
            else
                result[i] = new ActorMoviePair(vertex.get(i), new LightMovie(edges.get(i)));
        }
        return result;
    }


}
