package client.service;

import client.dto.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
public class ActorLookupService {

    private final static HashMap<String, Actor> actorsContainer = new HashMap<>();
    private RestTemplate restTemplate;
    @Autowired
    public ActorLookupService(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    public Actor getActorByID(String id) {
        if(actorsContainer.containsKey(id))
            return actorsContainer.get(id);
        else {
            String path = "https://java.kisim.eu.org/actors/" + id;
            Actor actor = restTemplate.getForObject(path, Actor.class);
            actorsContainer.put(id, actor);
            return actor;
        }
    }
}
