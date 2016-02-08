package com.example.e_myslivost.myapplication;

import android.graphics.Color;

/**
 * Created by e-myslivost on 17.11.2015.
 */
public class Turtle {



    private int x;
    private int y;
    private int angle;
    private int color;
    public boolean draw;



    public Turtle()
    {
        this.angle = 0;
        this.draw = true;
        this.x = 0;
        this.y = 0;
        this.color = Color.GREEN;
    }

    public Turtle( int x, int y, int angle, boolean draw)
    {
        this.x = x;
        this.y= y;
        this.angle = angle;
        this.draw = draw;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public int getColor() {
        return color;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public int getAngle() {
        return angle;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void forward(int distance)
    {

    }
    public void back(int distance)
    {

    }
    public void turn(int angle)
    {

    }
}
