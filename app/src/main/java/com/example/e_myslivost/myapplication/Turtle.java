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

    public int getNewXBackward(int delka, Turtle turtle)
    {
        int newX = 0;
        if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {
            newX = (int) (turtle.getX() - delka * Math.cos(Math.toRadians(turtle.getAngle())));

        }
        if (turtle.getAngle() == 90) {
            newX = turtle.getX();
        }
        if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
            newX = (int) (turtle.getX() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 90)));

        }
        if (turtle.getAngle() == 180) {
            newX = turtle.getX() + delka;
        }
        if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
            newX = (int) (turtle.getX() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 180)));

        }
        if (turtle.getAngle() == 270) {
            newX = turtle.getX();
        }
        if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
            newX = (int) (turtle.getX() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 270)));
        }
        if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
            newX = turtle.getX() - delka;
        }

        return newX;
    }
    public int getNewYBackward(int delka, Turtle turtle)
    {

        int newY = 0;
        if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {
            newY = (int) (turtle.getY() - delka * Math.sin(Math.toRadians(turtle.getAngle())));

        }
        if (turtle.getAngle() == 90) {
            newY = turtle.getY() - delka;
        }
        if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
            newY = (int) (turtle.getY() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 90)));

        }
        if (turtle.getAngle() == 180) {
            newY = turtle.getY();
        }
        if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
            newY = (int) (turtle.getY() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 180)));
        }
        if (turtle.getAngle() == 270) {
            newY = turtle.getY() + delka;
        }
        if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
            newY = (int) (turtle.getY() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 270)));
        }
        if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
            newY = turtle.getY();
        }

        return newY;
    }
    public int getNewXForward(int delka, Turtle turtle)
    {
        int newX = 0;
        if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {
            newX = (int) (turtle.getX() + delka * Math.cos(Math.toRadians(turtle.getAngle())));
        }
        if (turtle.getAngle() == 90) {
            newX = turtle.getX();
        }
        if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
            newX = (int) (turtle.getX() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 90)));

        }
        if (turtle.getAngle() == 180) {
            newX = turtle.getX() - delka;
        }
        if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
            newX = (int) (turtle.getX() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 180)));
        }
        if (turtle.getAngle() == 270) {
            newX = turtle.getX();
        }
        if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
            newX = (int) (turtle.getX() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 270)));
        }
        if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
            newX = turtle.getX() + delka;
        }

        return newX;
    }
    public int getNewYForward(int delka, Turtle turtle)
    {

        int newY = 0;
        if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {

            newY = (int) (turtle.getY() + delka * Math.sin(Math.toRadians(turtle.getAngle())));
        }
        if (turtle.getAngle() == 90) {

            newY = turtle.getY() + delka;
        }
        if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
            newY = (int) (turtle.getY() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 90)));

        }
        if (turtle.getAngle() == 180) {
            newY = turtle.getY();
        }
        if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
            newY = (int) (turtle.getY() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 180)));
        }
        if (turtle.getAngle() == 270) {
            newY = turtle.getY() - delka;
        }
        if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
            newY = (int) (turtle.getY() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 270)));
        }
        if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
            newY = turtle.getY();
        }

        return newY;
    }
    public void turn(int angle)
    {

    }
}
