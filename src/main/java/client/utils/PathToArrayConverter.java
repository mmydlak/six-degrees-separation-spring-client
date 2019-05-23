package client.utils;

import org.jgrapht.GraphPath;


public interface PathToArrayConverter<T,E,V> {

    V[] toArray(GraphPath<T, E> gp);

}
