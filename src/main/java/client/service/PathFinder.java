package client.service;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

public interface PathFinder<T,E> {

    GraphPath<T,E> findPath(Graph<T, E> graph, T a, T b);
}
