package com.example.metroappv2;

import java.util.*;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class RouteSearcherLeastStations {

    private Database db;

    public static class RouteResult {
        public int time;
        public List<Integer> path;

        public RouteResult(int time, List<Integer> path) {
            this.time = time;
            this.path = path;
        }

        public List<Integer> getPath() {
            return path;
        }

        public int getTime() {
            return time;
        }
    }

    public static RouteResult CalculateTime(Map<Integer, List<int[]>> graph, int start, int end, int n) {
        // Массив для хранения расстояний до каждой вершины
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;

        // Массив для хранения предыдущих вершин
        int[] previous = new int[n];
        Arrays.fill(previous, -1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentVertex = current[0];
            int currentDistance = current[1];

            if (currentVertex == end) {
                // Восстанавливаем путь
                List<Integer> path = new ArrayList<>();
                for (int at = end; at != -1; at = previous[at]) {
                    path.add(at);
                }
                Collections.reverse(path);
                return new RouteResult(currentDistance, path);
            }

            if (currentDistance > distances[currentVertex]) {
                continue;
            }

            for (int[] neighbor : graph.get(currentVertex)) {
                int nextVertex = neighbor[0];
                int weight = neighbor[1];
                int distance = currentDistance + weight;

                if (distance < distances[nextVertex]) {
                    distances[nextVertex] = distance;
                    previous[nextVertex] = currentVertex;
                    pq.offer(new int[]{nextVertex, distance});
                }
            }
        }

        return new RouteResult(Integer.MAX_VALUE, Collections.emptyList());
    }

    public RouteSearcherLeastStations(Database db) {
        this.db = db;
    }

    public RouteResult ShortestTime(int start, int end) {
        List<Link> links = db.getLinks();
        int l = db.getNumberOfLinks();
        int[][] edges = new int[l][3];
        int n = db.getNumberOfStations();

        for (int i = 0; i < l; i++) {
            int u = links.get(i).getStation1_id();
            int v = links.get(i).getStation2_id();
            int w = 1;
            edges[i] = new int[]{u, v, w};
        }

        // Строим граф в виде списка смежности
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.get(u).add(new int[]{v, w});
            // Если граф неориентированный, добавляем обратное ребро
            graph.get(v).add(new int[]{u, w});
        }

        return CalculateTime(graph, start, end, n);
    }
    public String printPath (int start, int end, Database db, Pane MainPane)
    {
        List<Station> stations = db.getStations();
        RouteResult routeResult = ShortestTime(start, end);
        Text t = new Text();
        String s = "Путь: ";
        for (int i = 0; i < routeResult.getPath().size(); i++)
        {
            if (i != routeResult.getPath().size() - 1)
            {
                for (Station stationData : stations)
                {
                    if (routeResult.getPath().get(i) == stationData.getId())
                    {
                        s += stationData.getName() + " - ";
                    }
                }
            }
            else {
                for (Station stationData : stations)
                {
                    if (routeResult.getPath().get(i) == stationData.getId())
                    {
                        s += stationData.getName();
                    }
                }
            }
        }
        s += "; Количество пройденных станций - " + routeResult.getTime();
        if (start == end)
        {
            s = "Вы выбрали одну и ту же станцию";
        }
        return s;
    }
    public void highlightPath (int start, int end, Database db, Pane MainPane)
    {
        RouteResult routeResult = ShortestTime(start, end);
        List<Integer> path = routeResult.getPath();
        for (int i = 0; i < path.size() - 1; i++)
        {
            Station station1 = db.findStation(path.get(i)).get(0);
            Station station2 = db.findStation(path.get(i+1)).get(0);
            int x1 = station1.getX();
            int x2 = station2.getX();
            int y1 = station1.getY();
            int y2 = station2.getY();
            Line line = new Line(x1, y1, x2, y2);
            line.setStrokeWidth(3.0);
            line.setStyle("000000");
            Circle st1 = new Circle(x1, y1, 12, Color.BLACK);
            Circle st2 = new Circle(x2, y2, 12, Color.BLACK);
            MainPane.getChildren().add(st1);
            MainPane.getChildren().add(st2);
            MainPane.getChildren().add(line);
        }
    }
    public void deletePath (Pane MainPane)
    {
        List <Node> children = new ArrayList<>(MainPane.getChildren());
        for (Node node : children)
        {
            if (node instanceof Circle)
            {
                Circle circle = (Circle) node;
                if (circle.getRadius() == 12)
                {
                    MainPane.getChildren().remove(circle);
                }
            }
            else if (node instanceof Line)
            {
                Line line = (Line) node;
                if(line.getStroke() == Color.BLACK)
                {
                    MainPane.getChildren().remove(line);
                }
            }
        }
    }
}
