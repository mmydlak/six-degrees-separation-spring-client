package client.dto;



import lombok.Getter;
import lombok.Setter;

public class ActorMoviePair {
    @Getter
    @Setter
    private Actor actor;
    @Getter
    @Setter
    private LightMovie movie;


    public ActorMoviePair(Actor actor, LightMovie movie) {
        this.actor = actor;
        this.movie = movie;
    }
}

