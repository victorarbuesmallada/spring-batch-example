package org.cascomio.springbatchexample.models;

public class Coordinates {
    private float x;
    private float y;

    public Coordinates(String x, String y) {
        this.x = Float.valueOf(x);
        this.y = Float.valueOf(y);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
