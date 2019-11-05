package za.co.discovery.assignment.ganesan.service;

import za.co.discovery.assignment.ganesan.dto.PlanetRouteDto;

import java.util.*;
import java.util.function.Function;

public class ShortestPath {

    private Function<PlanetRouteDto, Double> weightFunction;
    private final List<PlanetRouteDto> edges;
    private Set<String> settledNodes;
    private Set<String> unSettledNodes;
    private Map<String, String> predecessors;
    private Map<String, Double> distance;

    public ShortestPath(List<PlanetRouteDto> nodes, Function<PlanetRouteDto, Double> weightFunction) {
        this.edges = new ArrayList<>(nodes);
        this.weightFunction = weightFunction;
    }

    public void execute(String source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0d);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            String node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(String node) {
        List<String> adjacentNodes = getNeighbors(node);
        for (String target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private Double getDistance(String node, String target) {
        return edges.stream()
                .filter(edge -> edge.getOrigin().equals(node) && edge.getDestination().equals(target))
                .findFirst()
                .map(weightFunction)
                .orElseThrow(() -> new RuntimeException("Distance not found"));
    }

    private List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        for (PlanetRouteDto edge : edges) {
            if (edge.getOrigin().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private String getMinimum(Set<String> vertexes) {
        return vertexes.stream().min(Comparator.comparing(this::getShortestDistance)).orElse(null);
    }

    private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
    }

    private Double getShortestDistance(String destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<String> getPath(String target) {
        LinkedList<String> path = new LinkedList<>();
        String step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}
