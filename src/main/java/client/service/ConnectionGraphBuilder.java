package client.service;

import org.jgrapht.Graph;

public interface ConnectionGraphBuilder<T,E> {

    Graph<T,E> buildConnectionGraph(T a, T b);

}
