package com.example.metroappv2;

public class Link {
    private int id;
    private boolean line_change;
    private boolean is_koltsevaya;
    private int station1_id;
    private int station2_id;
    private int time;

    public Link(int id, boolean line_change, boolean is_koltsevaya, int station1_id, int station2_id, int time) {
        this.id = id;
        this.line_change = line_change;
        this.is_koltsevaya = is_koltsevaya;
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

    public boolean Is_koltsevaya() {
        return is_koltsevaya;
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
