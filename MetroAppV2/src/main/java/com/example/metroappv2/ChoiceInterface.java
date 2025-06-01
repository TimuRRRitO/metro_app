package com.example.metroappv2;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;



public class ChoiceInterface {
    public static final int startPosX = 0;
    public static final int startPosY = 0;
    public static final int endPosX = 250;
    public static final int endPosY = 0;
    public static final int timePosX = 500;
    public static final int timePosY = 0;
    public static final int stationsPosX = 750;

    public static final int stationsPosY = 0;
    public static final int deletePosX = 1000;
    public static final int deletePosY = 0;
    public static final int confirmPosX = 1250;
    public static final int confirmPosY = 0;
    public static final int height = 30;
    public static final int width = 250;

    public int getStart(List<Station> stations, ComboBox<String> startStationList)
    {
        int start = 0;
        for (Station stationData : stations)
        {
            if (startStationList.getValue().equals(stationData.getName()))
            {
                start = stationData.getId();
            }
        }
        return start;
    }

    public int getEnd(List<Station> stations, ComboBox<String> endStationList)
    {
        int end = 0;
        for (Station stationData : stations)
        {
            if (endStationList.getValue().equals(stationData.getName()))
            {
                end = stationData.getId();
            }
        }
        return end;
    }
    public ChoiceInterface(List<Station> stations, List<Link> links, Pane MainPane, RouteSearcherShortestTime routeSearcherShortestTime, RouteSearcherLeastStations routeSearcherLeastStations) {
        ComboBox<String> startStationList = new ComboBox<>();
        ComboBox<String> endStationList = new ComboBox<>();
        Button time = new Button();
        Button leastStations = new Button();
        for (Station stationData : stations)
        {
            String name = stationData.getName();
            startStationList.getItems().add(name);
            endStationList.getItems().add(name);
        }
        startStationList.setLayoutX(startPosX);
        startStationList.setLayoutY(startPosY);
        endStationList.setLayoutX(endPosX);
        endStationList.setLayoutY(endPosY);
        time.setLayoutX(timePosX);
        time.setLayoutY(timePosY);
        leastStations.setLayoutX(stationsPosX);
        leastStations.setLayoutY(stationsPosY);
        startStationList.setPrefHeight(height);
        startStationList.setPrefWidth(width);
        endStationList.setPrefHeight(height);
        endStationList.setPrefWidth(width);
        time.setPrefHeight(height);
        time.setPrefWidth(width);
        leastStations.setPrefHeight(height);
        leastStations.setPrefWidth(width);
        startStationList.setPromptText("Выберите начальную станцию");
        endStationList.setPromptText("Выберите конечную станцию");
        time.setText("Наименьшее время");
        leastStations.setText("Наименьшее количество станций");
        MainPane.getChildren().add(startStationList);
        MainPane.getChildren().add(endStationList);
        MainPane.getChildren().add(time);
        MainPane.getChildren().add(leastStations);
        Database db = new Database();
            time.setOnAction(event -> {
                MainPane.getChildren().removeIf(node -> node instanceof Text);
                routeSearcherShortestTime.deletePath(MainPane);
                final int Start = getStart(stations, startStationList);
                final int End = getEnd(stations, endStationList);
                String t = routeSearcherShortestTime.printPath(Start, End, db, MainPane);
                routeSearcherShortestTime.highlightPath(Start, End, db, MainPane);
                Text text = new Text();
                text.setText(t);
                text.setLayoutX(100);
                text.setLayoutY(750);
                MainPane.getChildren().add(text);
            });
            leastStations.setOnAction(event -> {
                MainPane.getChildren().removeIf(node -> node instanceof Text);
                routeSearcherLeastStations.deletePath(MainPane);
                final int Start = getStart(stations, startStationList);
                final int End = getEnd(stations, endStationList);
                String t = routeSearcherLeastStations.printPath(Start, End, db, MainPane);
                routeSearcherLeastStations.highlightPath(Start, End, db, MainPane);
                Text text = new Text();
                text.setText(t);
                text.setLayoutX(100);
                text.setLayoutY(750);
                MainPane.getChildren().add(text);
            });
    }
}
