package com.example.e_myslivost.myapplication;

/**
 * Created by e-myslivost on 26.2.2016.
 */
public class Procedure
{
    String procedureName;
    String[] procedure;

    public Procedure(String procedureName,String[] procedure)
    {
        this.procedure = procedure;
        this.procedureName = procedureName;
    }

    public String[] getProcedure() {
        return procedure;
    }

    public void setProcedure(String[] procedure) {
        this.procedure = procedure;
    }

    public String getProcedureName() {

        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }
}
