package client.dto;

import lombok.Data;

@Data
public class LightMovie {
    private String id;
    private String title;

    public LightMovie(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
    }
}
