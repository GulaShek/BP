package com.example.e_myslivost.myapplication;

import java.util.ArrayList;

/**
 * Created by e-myslivost on 26.2.2016.
 */
public class Procedure
{
    String procedureName;
    ArrayList<Commands> commands;

    public Procedure(String procedureName,ArrayList<Commands> commands)
    {
        this.commands = commands;
        this.procedureName = procedureName;
    }

    public ArrayList<Commands> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Commands> commands) {
        this.commands = commands;
    }

    public String getProcedureName() {

        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }
}
