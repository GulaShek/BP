package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by e-myslivost on 26.2.2016.
 */
public class ChooseProcedure extends Dialog
{

    private Button btnProcedureChooseClose;
    private Button btnProcedureChoose;
    public ListView listViewChooseProcedure;
    public String chooseItem;
    public Procedure chooseProcedure;

    Activity c;
    ArrayList<Procedure> listOfProcedure = new ArrayList<Procedure>();

    private ArrayAdapter<String> listAdapter ;
    String[] commands = new String[] {};
    ArrayList<String> commandList = new ArrayList<String>();

    public ChooseProcedure(Activity a) {
        super(a);
        this.c = a;

    }


    public ChooseProcedure(Activity a, ArrayList<Procedure> list) {
        super(a);
        this.c = a;
        this.listOfProcedure = list;

    }


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_procedure_layout);
        initComopnents();

        for (int i = 0;i < listOfProcedure.size();i++)
        {
            listAdapter.add(listOfProcedure.get(i).procedureName);
            listViewChooseProcedure.setAdapter(listAdapter);

        }

        listViewChooseProcedure.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // When clicked, show a toast with the TextView text or do whatever you need.
                Toast.makeText(getContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
                chooseItem = (String)((TextView) view).getText();
                chooseProcedure = listOfProcedure.get(position);
                listViewChooseProcedure.setItemChecked(position,true);
            }
        });



    }








    public void initComopnents()
    {
        btnProcedureChooseClose = (Button) findViewById(R.id.btnProcedureChooseClose);
        btnProcedureChoose = (Button) findViewById(R.id.btnProcedureChoose);
        listViewChooseProcedure = (ListView) findViewById(R.id.listViewChooseProcedure);

        listAdapter = new ArrayAdapter<String>(getContext(), R.layout.simplerow, commandList);
        commandList.addAll(Arrays.asList(commands));
        listViewChooseProcedure.setAdapter( listAdapter );
        listViewChooseProcedure.setClickable(true);
    }


}


