package com.example.metroappv2;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/metro_app";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Tc20066002";

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT station_id, stations.name, color, x_coordinate, y_coordinate FROM stations INNER JOIN lines ON stations.line_id = lines.line_id";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("station_id");
                    String name = rs.getString("name");
                    String color = rs.getString("color");
                    int x = rs.getInt("x_coordinate");
                    int y = rs.getInt("y_coordinate");
                    stations.add(new Station(id, name, color, x, y));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;
    }
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT link_id, line_change, is_koltsevaya, station1_id, station2_id, time FROM links";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("link_id");
                    boolean line_change= rs.getBoolean("line_change");
                    boolean is_koltsevaya = rs.getBoolean("is_koltsevaya");
                    int station1_id = rs.getInt("station1_id");
                    int station2_id = rs.getInt("station2_id");
                    int time = rs.getInt("time");
                    links.add(new Link(id, line_change, is_koltsevaya, station1_id, station2_id, time));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return links;
    }
    public List<LinkForRouteSearcher> getLinksForRouteSearcher() {
        List<LinkForRouteSearcher> linksForRouteSearcher = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT link_id, line_change, station1_id, station2_id, time FROM links";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String station1_name = "";
                    String station2_name = "";
                    int id = rs.getInt("link_id");
                    boolean line_change = rs.getBoolean("line_change");
                    int station1_id = rs.getInt("station1_id");
                    try (Statement stmtnew = conn.createStatement();
                         ResultSet newrs = stmtnew.executeQuery("SELECT name FROM stations WHERE station_id = " + String.valueOf(station1_id))) {
                        while (newrs.next()) {
                            station1_name = newrs.getString("name");
                        }
                    }
                    int station2_id = rs.getInt("station2_id");
                    try (Statement stmtnewnew = conn.createStatement();
                         ResultSet newnewrs = stmtnewnew.executeQuery("SELECT name FROM stations WHERE station_id = " + String.valueOf(station2_id))) {
                        while (newnewrs.next()) {
                            station2_name = newnewrs.getString("name");
                        }
                    }
                    int time = rs.getInt("time");
                    linksForRouteSearcher.add(new LinkForRouteSearcher(id, line_change, station1_name, station2_name, station1_id, station2_id, time));
                    linksForRouteSearcher.add(new LinkForRouteSearcher(id, line_change, station2_name, station1_name, station2_id, station1_id, time));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return linksForRouteSearcher;
    }

        public int getNumberOfLinks ()
        {
            int n = 0;
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String sql = "SELECT COUNT (*) FROM links";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(sql)) {

                    while (rs.next()) {
                        n = rs.getInt("count");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return n;
        }
    public int getNumberOfStations ()
    {
        int n = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT COUNT (*) FROM stations";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    n = rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    public List<Station> findStation(int Id) {
        List<Station> stations = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM stations WHERE station_id = " + String.valueOf(Id);
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    int id = rs.getInt("station_id");
                    String name = rs.getString("name");
                    String color = "000000";
                    int x = rs.getInt("x_coordinate");
                    int y = rs.getInt("y_coordinate");
                    stations.add(new Station(id, name, color, x, y));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stations;
    }
}



