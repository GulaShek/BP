package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;
import com.vi.swipenumberpicker.OnValueChangeListener;
import com.vi.swipenumberpicker.SwipeNumberPicker;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by e-myslivost on 26.2.2016.
 */
public class CreateProcedure extends Dialog
{
    private SeekBar sbProcedureForward;
    private SeekBar sbProcedureBackward;
    private SeekArc seekArcProcedure;
    private EditText tbProcedureForward;
    private EditText tbProcedureBackward;
    public EditText tbProcedureName;
    private TextView seekArcProgressProcedure;
    private Button btnProcedureForward;
    private Button btnProcedureBackward;
    private Button btnProcedureIteration;
    private Button btnProcedureClose;
    private Button btnProcedurePickColour;
    private Button btnProcedureSave;
    private Switch switchProcedure;
    private SwipeNumberPicker numbPickProcedure;

    //listView
    private ListView listViewProcedure;
    ArrayAdapter<String> listAdapter2 ;
    String[] commands = new String[] {};
    ArrayList<String> commandList = new ArrayList<String>();

    //proměnné pro colorPicker
    int defaultColorR =0;
    int defaultColorG = 0;
    int defaultColorB = 0;
    int selectedColorR;
    int selectedColorG;
    int selectedColorB;
    int selectedColorRGB;

    Activity c;
    ArrayList<Commands> listOfCommands = new ArrayList<Commands>();


    public CreateProcedure(Activity a) {
    super(a);
    this.c = a;

}


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.procedure_layout);
        initComopnents();

        btnProcedureForward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                listAdapter2.add("Vpřed " + sbProcedureForward.getProgress());
                listViewProcedure.setAdapter(listAdapter2);
                Commands f = new Commands(1,"Vpřed",sbProcedureForward.getProgress());
                listOfCommands.add(f);

            }
        });

        sbProcedureForward.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                tbProcedureForward.setText("" + sbProcedureForward.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tbProcedureForward.setText("" + sbProcedureForward.getProgress());
            }

        });

        btnProcedureBackward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                listAdapter2.add("Vzad " + sbProcedureBackward.getProgress());
                listViewProcedure.setAdapter(listAdapter2);
                Commands f = new Commands(2,"Vzad",sbProcedureBackward.getProgress());
                listOfCommands.add(f);

            }
        });

        sbProcedureBackward.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                tbProcedureBackward.setText("" + sbProcedureBackward.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                tbProcedureBackward.setText("" + sbProcedureBackward.getProgress());
            }

        });

        seekArcProcedure.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                seekArcProgressProcedure.setText("" + seekArcProcedure.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                seekArcProgressProcedure.setText("" + seekArc.getProgress());

                listAdapter2.add("Otočit " + seekArc.getProgress());
                listViewProcedure.setAdapter(listAdapter2);
                Commands c = new Commands(3,"Otočit",seekArc.getProgress());
                listOfCommands.add(c);

            }
        }) ;

        btnProcedureIteration.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                commandList.add(0, "Opakovat " + numbPickProcedure.getValue());
                commandList.add(listAdapter2.getCount(), "Konec opakování");
                commandList.addAll(Arrays.asList(commands));
                listViewProcedure.setAdapter(listAdapter2);
                Commands c = new Commands(6,"Opakuj",numbPickProcedure.getValue());
                listOfCommands.add(0, c);
                Commands c2 = new Commands(7,"konec Opakování");
                listOfCommands.add(listOfCommands.size(), c2);

            }

        });
        numbPickProcedure.setOnValueChangeListener(new OnValueChangeListener() {
            @Override
            public boolean onValueChange(SwipeNumberPicker view, int oldValue, int newValue) {
                boolean isValueOk = (newValue & 1) == 0;
                if (newValue < 10 && newValue > 0) {
                    isValueOk = true;
                } else {
                    isValueOk = false;
                    Toast.makeText(getContext(), "Minimální počet opakování je 1 - maximální 10!", Toast.LENGTH_LONG).show();
                }


                return isValueOk;
            }

        });


        btnProcedurePickColour.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ColorPicker cp = new ColorPicker(c, defaultColorR, defaultColorG, defaultColorB);

                cp.show();


                /* On Click listener for the dialog, when the user select the color */
                Button okColor = (Button) cp.findViewById(R.id.okColorButton);

                okColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                 /* You can get single channel (value 0-255) */
                        selectedColorR = cp.getRed();
                        selectedColorG = cp.getGreen();
                        selectedColorB = cp.getBlue();

                        /* Or the android RGB Color (see the android Color class reference) */
                        selectedColorRGB = cp.getColor();

                        cp.dismiss();

                        listAdapter2.add("Nová barva ");
                        listViewProcedure.setAdapter(listAdapter2);
                        btnProcedurePickColour.setBackgroundColor(selectedColorRGB);
                        Commands f = new Commands(4,"Změna barvy",selectedColorRGB);
                        listOfCommands.add(f);

                    }
                });
            }
        });

        switchProcedure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {

                        listAdapter2.add("Kreslení zapnuto");
                        listViewProcedure.setAdapter(listAdapter2);
                        Commands c = new Commands(5,"Kresleni",1);
                        listOfCommands.add(c);


                } else {

                        listAdapter2.add("Kreslení vypnuto");
                        listViewProcedure.setAdapter(listAdapter2);
                        Commands c = new Commands(5,"Kresleni",0);
                        listOfCommands.add(c);

                }

            }
        });






    }





    public void initComopnents()
    {
        //inicializace komponentu - a vychozího stavu

        //SeekBary
        sbProcedureBackward = (SeekBar) findViewById(R.id.sbProcedureBackward);
        sbProcedureForward = (SeekBar) findViewById(R.id.sbProcedureForward);
        seekArcProcedure = (SeekArc) findViewById(R.id.seekArcProcerude);
        seekArcProgressProcedure = (TextView) findViewById(R.id.seekArcProgressProcedure);
        seekArcProgressProcedure.setKeyListener(null);

        //Textové pole
        tbProcedureBackward = (EditText) findViewById(R.id.tbProcedureBackward);
        tbProcedureBackward.setKeyListener(null);
        tbProcedureForward = (EditText) findViewById(R.id.tbProcedureForward);
        tbProcedureForward.setKeyListener(null);
        tbProcedureName = (EditText) findViewById(R.id.tbProcedureName);


        //Buttny
        btnProcedureBackward= (Button) findViewById(R.id.btnProceureBackward);
        btnProcedureBackward.setBackgroundResource(R.drawable.button_selector);
        btnProcedureForward = (Button) findViewById(R.id.btnProcedureForward);
        btnProcedureForward.setBackgroundResource(R.drawable.button_selector);
        btnProcedureClose = (Button) findViewById(R.id.btnProcedureClose);
        btnProcedureClose.setBackgroundResource(R.drawable.button_selector);
        btnProcedureIteration = (Button) findViewById(R.id.btnProcedureIteration);
        btnProcedureIteration.setBackgroundResource(R.drawable.button_selector);
        btnProcedurePickColour = (Button) findViewById(R.id.btnProcedurePickColor);
        btnProcedurePickColour.setBackgroundResource(R.drawable.button_selector);
        btnProcedureSave = (Button) findViewById(R.id.btnProcedureSave);
        btnProcedureSave.setBackgroundResource(R.drawable.button_selector);


        //Ostatní
        numbPickProcedure = (SwipeNumberPicker) findViewById(R.id.numbPickProcedure);
        switchProcedure =  (Switch) findViewById(R.id.switchProcedure);

        //Listview
        listViewProcedure = (ListView) findViewById(R.id.listViewProcedure);
        listAdapter2 = new ArrayAdapter<String>(this.getContext(), R.layout.simplerow, commandList);
        commandList.addAll( Arrays.asList(commands) );
        listViewProcedure.setAdapter( listAdapter2 );

    }





}
