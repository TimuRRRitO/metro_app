package com.example.metroappv2;

public class LinkForRouteSearcher {
    private int id;
    private boolean line_change;
    private String station1_name;
    private String station2_name;
    private int station1_id;
    private int station2_id;
    private int time;

    public LinkForRouteSearcher(int id, boolean line_change, String station1_name, String station2_name, int station1_id, int station2_id, int time) {
        this.id = id;
        this.line_change = line_change;
        this.station1_name = station1_name;
        this.station2_name = station2_name;
        this.station1_id = station1_id;
        this.station2_id = station2_id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public boolean isLine_change() {
        return line_change;
    }

    public String getStation2_name() {
        return station2_name;
    }

    public String getStation1_name() {
        return station1_name;
    }

    public int getStation1_id() {
        return station1_id;
    }

    public int getStation2_id() {
        return station2_id;
    }

    public int getTime() {
        return time;
    }
}

