package org.psjava.algo.graph.dfs;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.psjava.DFSStatusMap;
import org.psjava.ds.graph.Graph;
import org.psjava.ds.graph.DirectedEdge;
import org.psjava.ds.graph.TestGraphFactory;
import org.psjava.util.VisitorStopper;

public class DFSCoreTest {

    String res;

    @Test
    public void testSimpleTraverseScenario() {
        Graph<String, DirectedEdge<String>> g = TestGraphFactory.createDirectedNew(new String[][]{{"1", "2"}, {"2", "3"}, {"3", "1"}});
        res = "";
        DFSCore.traverse(g::getEdges, DirectedEdge::to, new DFSStatusMap<>(), "1", new DFSVisitor<String, DirectedEdge<String>>() {
            @Override
            public void onDiscovered(String vertex, int depth, VisitorStopper stopper) {
                res += "N" + vertex;
            }

            @Override
            public void onBackEdgeFound(DirectedEdge<String> e) {
                res += "B" + e.from() + e.to();
            }

            @Override
            public void onFinish(String vertex) {
                res += "F" + vertex;
            }

            @Override
            public void onWalkDown(DirectedEdge<String> edge) {
                res += "D" + edge.from() + edge.to();
            }

            @Override
            public void onWalkUp(DirectedEdge<String> edge) {
                res += "U" + edge.to() + edge.from();
            }
        });
        assertEquals("N1D12N2D23N3B31F3U32F2U21F1", res);
    }

    @Test
    public void testStopper() {
        Graph<String, DirectedEdge<String>> g = TestGraphFactory.createDirectedNew(new String[][]{{"A", "B"}, {"B", "C"}, {"C", "D"}});
        res = "";
        DFSCore.traverse(g::getEdges, DirectedEdge::to, new DFSStatusMap<>(), "A", new DFSVisitorBase<String, DirectedEdge<String>>() {
            @Override
            public void onDiscovered(String vertex, int depth, VisitorStopper stopper) {
                res += vertex;
                if (vertex.equals("C"))
                    stopper.stop();
            }

            ;
        });
        Assert.assertEquals("ABC", res);
    }

}
