package client.controller;


import client.dto.Actor;
import client.service.ActorLookupService;
import client.service.ActorsShortestPathFinder;
import client.dto.ActorMoviePair;
import client.service.ActorsGraphBuilder;
import client.utils.GraphPathConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class JsonRestController {

    private final ActorsShortestPathFinder shortestPathService;

    private final ActorLookupService actorService;

    private final ActorsGraphBuilder actorsGraphBuilder;

    @Autowired
    public JsonRestController(ActorsShortestPathFinder shortestPathService,
                              ActorLookupService actorService,
                              ActorsGraphBuilder actorsGraphBuilder) {
        this.shortestPathService = shortestPathService;
        this.actorService = actorService;
        this.actorsGraphBuilder = actorsGraphBuilder;
    }

    @RequestMapping(value = "actors/shortest_path", method = RequestMethod.GET)
    public ActorMoviePair[] getActorsShortestPath(@RequestParam(value = "actorA") String actorA,
                                                @RequestParam(value = "actorB") String actorB) {

        GraphPathConverter conv = new GraphPathConverter();
        Actor a = actorService.getActorByID(actorA);
        Actor b = actorService.getActorByID(actorB);
        return conv.toArray(shortestPathService.findPath(actorsGraphBuilder.buildConnectionGraph(a, b), a, b));

    }

    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }


}
