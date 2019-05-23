package client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.jgrapht.graph.DefaultEdge;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie extends DefaultEdge {

    private String title;
    private Actor[] actors;
    private String id;

    public Movie(){
    }

    public Movie(Movie m){
        this.title = m.title;
        this.id = m.id;
        this.actors = m.actors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Actor[] getActors() {
        return actors;
    }

    public void setActors(Actor[] actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                //"id=" + id +
                "title=" + title +
                //", actors=" + Arrays.toString(actors) +
                '}';
    }

}
