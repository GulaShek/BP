package com.example.e_myslivost.myapplication;

/**
 * Created by e-myslivost on 27.2.2016.
 */
public class Commands {

    public int numberOfCommand;
    public String textOfCommand;
    public int commandDistance;

    public Commands() {

    }

    public Commands(int numberOfCommand, String textOfCommand) {

        this.numberOfCommand = numberOfCommand;
        this.textOfCommand = textOfCommand;
    }

    public Commands(int numberOfCommand, String textOfCommand, int commandDistance) {

        this.numberOfCommand = numberOfCommand;
        this.textOfCommand = textOfCommand;
        this.commandDistance = commandDistance;
    }

    public int getCommandDistance() {
        return commandDistance;
    }

    public void setCommandDistance(int commandDistance) {
        this.commandDistance = commandDistance;
    }

    public String getTextOfCommand() {

        return textOfCommand;
    }

    public void setTextOfCommand(String textOfCommand) {
        this.textOfCommand = textOfCommand;
    }

    public int getNumberOfCommand() {

        return numberOfCommand;
    }

    public void setNumberOfCommand(int numberOfCommand) {
        this.numberOfCommand = numberOfCommand;
    }
}
