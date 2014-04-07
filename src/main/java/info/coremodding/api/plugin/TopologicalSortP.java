package info.coremodding.api.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class TopologicalSortP
{
    public static class DirectedGraph<T> implements Iterable<T>
    {
        private final Map<T, Set<T>> graph = new HashMap<T, Set<T>>();

        public boolean addNode(T node)
        {
            // Ignore nodes already added
            if (graph.containsKey(node))
            {
                return false;
            }

            graph.put(node, new HashSet<T>());
            return true;
        }

        public void addEdge(T from, T to)
        {
            if (!(graph.containsKey(from) && graph.containsKey(to)))
            {
                throw new NoSuchElementException("Missing nodes from graph");
            }

            graph.get(from).add(to);
        }

        public void removeEdge(T from, T to)
        {
            if (!(graph.containsKey(from) && graph.containsKey(to)))
            {
                throw new NoSuchElementException("Missing nodes from graph");
            }

            graph.get(from).remove(to);
        }

        public boolean edgeExists(T from, T to)
        {
            if (!(graph.containsKey(from) && graph.containsKey(to)))
            {
                throw new NoSuchElementException("Missing nodes from graph");
            }

            return graph.get(from).contains(to);
        }

        public Set<T> edgesFrom(T from)
        {
            if (!graph.containsKey(from))
            {
                throw new NoSuchElementException("Missing node from graph");
            }

            return Collections.unmodifiableSet(graph.get(from));
        }
        @Override
        public Iterator<T> iterator()
        {
            return graph.keySet().iterator();
        }

        public int size()
        {
            return graph.size();
        }

        public boolean isEmpty()
        {
            return graph.isEmpty();
        }

        @Override
        public String toString()
        {
            return graph.toString();
        }
    }

    /**
     * Sort the input graph into a topologically sorted list
     *
     * Uses the reverse depth first search as outlined in ...
     * @param graph
     * @return
     */
    public static <T> List<T> topologicalSort(DirectedGraph<T> graph)
    {
        DirectedGraph<T> rGraph = reverse(graph);
        List<T> sortedResult = new ArrayList<T>();
        Set<T> visitedNodes = new HashSet<T>();
        // A list of "fully explored" nodes. Leftovers in here indicate cycles in the graph
        Set<T> expandedNodes = new HashSet<T>();

        for (T node : rGraph)
        {
            explore(node, rGraph, sortedResult, visitedNodes, expandedNodes);
        }

        return sortedResult;
    }

    public static <T> DirectedGraph<T> reverse(DirectedGraph<T> graph)
    {
        DirectedGraph<T> result = new DirectedGraph<T>();

        for (T node : graph)
        {
            result.addNode(node);
        }

        for (T from : graph)
        {
            for (T to : graph.edgesFrom(from))
            {
                result.addEdge(to, from);
            }
        }

        return result;
    }

    public static <T> void explore(T node, DirectedGraph<T> graph, List<T> sortedResult, Set<T> visitedNodes, Set<T> expandedNodes)
    {
        // Have we been here before?
        if (visitedNodes.contains(node))
        {
            // And have completed this node before
            if (expandedNodes.contains(node))
            {
                // Then we're fine
                return;
            }

            System.out.printf("%s: %s\n%s\n%s\n", node, sortedResult, visitedNodes, expandedNodes);
            throw new IllegalArgumentException("There was a cycle detected in the input graph, sorting is not possible");
        }

        // Visit this node
        visitedNodes.add(node);

        // Recursively explore inbound edges
        for (T inbound : graph.edgesFrom(node))
        {
            explore(inbound, graph, sortedResult, visitedNodes, expandedNodes);
        }

        // Add ourselves now
        sortedResult.add(node);
        // And mark ourselves as explored
        expandedNodes.add(node);
    }
}