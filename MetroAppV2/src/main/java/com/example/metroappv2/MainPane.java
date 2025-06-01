package com.example.metroappv2;

import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class MainPane extends Pane {
    public MainPane(List<Station> stations, List<Link> links) {
        for (Link LinkData : links) {
            if (LinkData.Is_koltsevaya() == false && LinkData.isLine_change() == false)
            {
                int startX = 0;
                int startY = 0;
                int endX = 0;
                int endY = 0;
                String color = "";
                for (Station StationData : stations) {
                    if (StationData.getId() == LinkData.getStation1_id()) {
                        startX = StationData.getX();
                        startY = StationData.getY();
                        color = StationData.getColor();
                        break;
                    }
                }
                for (Station StationData : stations) {
                    if (StationData.getId() == LinkData.getStation2_id()) {
                        endX = StationData.getX();
                        endY = StationData.getY();
                        break;
                    }
                }
                Line connection = new Line (startX, startY, endX, endY);
                connection.setStroke(Color.web(color));
                this.getChildren().add(connection);
            }
            if (LinkData.Is_koltsevaya() == true && LinkData.isLine_change() == false)
            {
                int startX = 0;
                int startY = 0;
                int endX = 0;
                int endY = 0;
                String color = "";
                for (Station StationData : stations) {
                    if (StationData.getId() == LinkData.getStation1_id()) {
                        startX = StationData.getX();
                        startY = StationData.getY();
                        color = StationData.getColor();
                        break;
                    }
                }
                for (Station StationData : stations) {
                    if (StationData.getId() == LinkData.getStation2_id()) {
                        endX = StationData.getX();
                        endY = StationData.getY();
                        break;
                    }
                }
                Line connection = new Line (startX, startY, endX, endY);
                connection.setStroke(Color.web(color));
                this.getChildren().add(connection);
            }
        }
        for (Station StationData : stations) {
            Circle station = new Circle(
                    StationData.getX(),
                    StationData.getY(),
                    StationData.RADIUS,
                    Color.valueOf(StationData.getColor())
            );
            this.getChildren().add(station);
        }
        Database db = new Database();
        RouteSearcherShortestTime routeSearcherShortestTime = new RouteSearcherShortestTime(db);
        RouteSearcherLeastStations routeSearcherLeastStations = new RouteSearcherLeastStations(db);
        ChoiceInterface choiceInterface = new ChoiceInterface(stations, links, this, routeSearcherShortestTime, routeSearcherLeastStations);
    }
}
