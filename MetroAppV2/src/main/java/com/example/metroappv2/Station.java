package com.example.metroappv2;

public class Station {
    private int id;
    private String name;
    private String color;
    private int x;
    private int y;

    public static final int RADIUS = 10;

    public Station(int id, String name, String color, int x, int y) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
