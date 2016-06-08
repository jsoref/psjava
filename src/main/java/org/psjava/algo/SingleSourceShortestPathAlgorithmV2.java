package org.psjava.algo;

import org.psjava.algo.graph.shortestpath.SingleSourceShortestPathResult;
import org.psjava.ds.graph.DirectedEdge;
import org.psjava.ds.graph.Graph;
import org.psjava.ds.math.Function;
import org.psjava.ds.numbersystrem.AddableNumberSystem;

public interface SingleSourceShortestPathAlgorithmV2 {
    <V, W, E extends DirectedEdge<V>> SingleSourceShortestPathResult<V, W, E> calc(Graph<V, E> graph, Function<E, W> weight, V start, AddableNumberSystem<W> ns);
}
