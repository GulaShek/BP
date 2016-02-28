package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;
import com.vi.swipenumberpicker.OnValueChangeListener;
import com.vi.swipenumberpicker.SwipeNumberPicker;

import java.util.ArrayList;
import java.util.Arrays;



public class MainActivity extends Activity {




    private SeekBar seekBar;
    private SeekBar seekBar2;
    private SeekArc seekBarArc;
    private EditText editText;
    private EditText editText2;
    private TextView seekArcProgress;
    private Button btnNewTurtle;
    private Button btnDeleteTurtle;
    private Button btnForward;
    private Button btnBackward;
    private Button btnClouseApp;
    private Button btnPickColour;
    private Button btnNewProcedure;
    private Button btnChooseProcedure;
    private Button btnIteration;
    private Button btnPlayCommands;
    private SurfaceView srf;
    private SwipeNumberPicker swipeNumberPicker;
    private Switch switchColor;
    private Turtle turtle;
    ArrayList<Procedure> listOfProcedure = new ArrayList<Procedure>();
    ArrayList<Commands> listOfCommands = new ArrayList<Commands>();


    //listView
    private ListView listView;
    private ArrayAdapter<String> listAdapter ;
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

    Bitmap bg = Bitmap.createBitmap(850, 1600, Bitmap.Config.ARGB_8888);
    Canvas canvas2 = new Canvas(bg);
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //zabrání změnám orientace
        initComopnents();


        btnDeleteTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(3);
                paint.setColor(Color.rgb(255,252,190));
                canvas2.drawRect(0, 0, 960, 1600, paint);
                ll.setBackgroundDrawable(new BitmapDrawable(bg));
                turtle = null;
            }
        });

        btnForward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(turtle != null){

                        int distance = seekBar.getProgress();
                        listAdapter.add("Vpřed " + distance);
                        listView.setAdapter(listAdapter);
                        Commands f = new Commands(1,"Vpřed",distance);
                        listOfCommands.add(f);
                }else
                {
                    Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBackward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (turtle != null) {
                    int distance = seekBar2.getProgress();
                    listAdapter.add("Vzad " + distance);
                    listView.setAdapter(listAdapter);
                    Commands f = new Commands(2,"Vzad",distance);
                    listOfCommands.add(f);

                } else {
                    Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                }

            }

        });

        btnNewTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                turtle = new Turtle();
                turtle.setX(480);
                turtle.setY(800);
                Toast.makeText(getApplicationContext(), "Vytvořili jste novou želvu!", Toast.LENGTH_LONG).show();
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(3);
                paint.setColor(Color.rgb(255, 252, 190));
                canvas2.drawRect(0, 0, 960, 1600, paint);
                ll.setBackgroundDrawable(new BitmapDrawable(bg));
            }
        });

        btnPickColour.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ColorPicker cp = new ColorPicker(MainActivity.this, defaultColorR, defaultColorG, defaultColorB);
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
                        if (turtle != null) {
                            listAdapter.add("Nová barva ");
                            listView.setAdapter(listAdapter);
                            btnPickColour.setBackgroundColor(selectedColorRGB);
                            Commands f = new Commands(4,"Změna barvy",selectedColorRGB);
                            listOfCommands.add(f);

                        } else {
                            Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
        btnNewProcedure.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final CreateProcedure tp = new CreateProcedure(MainActivity.this);

                tp.show();

                Button btnProcedureSave = (Button) tp.findViewById(R.id.btnProcedureSave);
                btnProcedureSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameOfProcedure = tp.tbProcedureName.getText().toString();
                        boolean isOk = true;
                        for (int i = 0; i < listOfProcedure.size(); i++) {
                            if (listOfProcedure.get(i).procedureName.equals(nameOfProcedure)) {
                                isOk = false;
                            }
                        }
                        if (isOk) {
                            Procedure p1 = new Procedure(nameOfProcedure, tp.listOfCommands);
                            listOfProcedure.add(p1);
                            tp.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Procedura s tímto názvem již existuje!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                Button btnProcedureClose = (Button) tp.findViewById(R.id.btnProcedureClose);

                btnProcedureClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tp.dismiss();

                    }
                });
            }

        });

        btnChooseProcedure.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ChooseProcedure chp = new ChooseProcedure(MainActivity.this,listOfProcedure);

                chp.show();

                Button btnProcedureChoose = (Button) chp.findViewById(R.id.btnProcedureChoose);
                btnProcedureChoose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        listAdapter.add(chp.chooseItem);
                        listView.setAdapter(listAdapter);
                        for (int i = 0; i < chp.chooseProcedure.commands.size(); i++)
                        {
                            listOfCommands.add(chp.chooseProcedure.commands.get(i));
                        }
                        chp.dismiss();
                    }
                });
                Button btnProcedureChooseClose = (Button) chp.findViewById(R.id.btnProcedureChooseClose);

                btnProcedureChooseClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        chp.dismiss();
                    }
                });
            }

        });
        btnIteration.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                commandList.add(0, "Opakovat " + swipeNumberPicker.getValue());
                commandList.add(listAdapter.getCount(), "Konec opakování");
                commandList.addAll(Arrays.asList(commands));
                listView.setAdapter(listAdapter);
                Commands c = new Commands(6,"Opakuj",swipeNumberPicker.getValue());
                listOfCommands.add(0, c);
                Commands c2 = new Commands(7,"konec Opakování");
                listOfCommands.add(listOfCommands.size(),c2);


            }

        });
        btnClouseApp.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        });
        btnPlayCommands.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                int counterOfCommands = listOfCommands.size();
                int x = 0;
                int y = 0;

                for (int i = 0; i< counterOfCommands;i++)
                {
                    int command = listOfCommands.get(i).getNumberOfCommand();

                    switch (command) {

                        case 1:


                            x = turtle.getNewXForward(listOfCommands.get(i).getCommandDistance(), turtle);
                            y = turtle.getNewYForward(listOfCommands.get(i).getCommandDistance(), turtle);
                            if(turtle.isDraw())
                            {
                                //... actual drawing on canvas
                                Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                                paint.setStyle(Paint.Style.FILL);
                                paint.setStrokeWidth(5);
                                paint.setColor(turtle.getColor());
                                canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                ll.setBackgroundDrawable(new BitmapDrawable(bg));
                            }

                            turtle.setX(x);
                            turtle.setY(y);

                            break;
                        case 2:


                            x = turtle.getNewXBackward(listOfCommands.get(i).getCommandDistance(), turtle);
                            y = turtle.getNewYBackward(listOfCommands.get(i).getCommandDistance(), turtle);

                            if (turtle.isDraw()) {

                                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                                paint.setStyle(Paint.Style.FILL);
                                paint.setStrokeWidth(5);
                                paint.setColor(turtle.getColor());
                                canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                ll.setBackgroundDrawable(new BitmapDrawable(bg));

                            }
                            turtle.setX(x);
                            turtle.setY(y);

                            break;
                        case 3:
                            turtle.setAngle(listOfCommands.get(i).getCommandDistance());

                            break;
                        case 4:

                            turtle.setColor(listOfCommands.get(i).getCommandDistance());

                            break;
                        case 5:

                            if(listOfCommands.get(i).getCommandDistance()==1)
                            {
                                turtle.setDraw(true);
                            }else
                            {
                                turtle.setDraw(false);
                            }

                            break;
                        case 6:
                            int storage = i;
                            int iterator = listOfCommands.get(i).getCommandDistance();


                            for(int g =  0;g <= iterator;)
                            {
                                i++;
                                if (listOfCommands.get(i).getNumberOfCommand() == 7)
                                {

                                    g++;
                                    i=storage;

                                }

                                command = listOfCommands.get(i).getNumberOfCommand();
                                switch (command) {

                                    case 1:


                                        x = turtle.getNewXForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                        y = turtle.getNewYForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                        if (turtle.isDraw()) {
                                            //... actual drawing on canvas
                                            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                                            paint.setStyle(Paint.Style.FILL);
                                            paint.setStrokeWidth(5);
                                            paint.setColor(turtle.getColor());
                                            canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                            ll.setBackgroundDrawable(new BitmapDrawable(bg));
                                        }

                                        turtle.setX(x);
                                        turtle.setY(y);

                                        break;
                                    case 2:


                                        x = turtle.getNewXBackward(listOfCommands.get(i).getCommandDistance(), turtle);
                                        y = turtle.getNewYBackward(listOfCommands.get(i).getCommandDistance(), turtle);

                                        if (turtle.isDraw()) {

                                            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                                            paint.setStyle(Paint.Style.FILL);
                                            paint.setStrokeWidth(5);
                                            paint.setColor(turtle.getColor());
                                            canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                            ll.setBackgroundDrawable(new BitmapDrawable(bg));

                                        }
                                        turtle.setX(x);
                                        turtle.setY(y);

                                        break;
                                    case 3:
                                        turtle.setAngle(listOfCommands.get(i).getCommandDistance());

                                        break;
                                    case 4:

                                        turtle.setColor(listOfCommands.get(i).getCommandDistance());

                                        break;
                                    case 5:

                                        if (listOfCommands.get(i).getCommandDistance() == 1) {
                                            turtle.setDraw(true);
                                        } else {
                                            turtle.setDraw(false);
                                        }

                                        break;

                                }


                            }



                            break;

                        default:

                            break;
                    }




                }


            }

        });




        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                editText.setText("" + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editText.setText("" + seekBar.getProgress());
            }

        });

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                editText2.setText("" + seekBar2.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editText2.setText("" + seekBar2.getProgress());
            }

        });
        seekBarArc.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener() {
            @Override
            public void onProgressChanged(SeekArc seekArc, int i, boolean b) {
                seekArcProgress.setText("" + seekArc.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {

            }

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
                seekArcProgress.setText("" + seekArc.getProgress());
                if (turtle != null) {
                    listAdapter.add("Otočit " + seekArc.getProgress());
                    listView.setAdapter(listAdapter);
                    Commands c = new Commands(3,"Otočit",seekArc.getProgress());
                    listOfCommands.add(c);
                }
            }
        });


        swipeNumberPicker.setOnValueChangeListener(new OnValueChangeListener() {
            @Override
            public boolean onValueChange(SwipeNumberPicker view, int oldValue, int newValue) {
                boolean isValueOk = (newValue & 1) == 0;
                if (newValue < 10 && newValue > 0) {
                    isValueOk = true;
                } else {
                    isValueOk = false;
                    Toast.makeText(getApplicationContext(), "Minimální počet opakování je 1 - maximální 10!", Toast.LENGTH_LONG).show();
                }


                return isValueOk;
            }

        });

        switchColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    if (turtle != null) {
                        listAdapter.add("Kreslení zapnuto");
                        listView.setAdapter(listAdapter);
                        Commands c = new Commands(5,"Kresleni",1);
                        listOfCommands.add(c);
                    }

                } else {
                    if (turtle != null) {
                        listAdapter.add("Kreslení vypnuto");
                        listView.setAdapter(listAdapter);
                        Commands c = new Commands(5,"Kresleni",0);
                        listOfCommands.add(c);
                    }
                }

            }
        });






    }




    public void clouseAPP()
    {
        this.closeOptionsMenu();
    }

    public void initComopnents()
    {
        //inicializace komponentu - a vychozího stavu

        //SeekBary
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBarArc = (SeekArc) findViewById(R.id.seekArc);

        //Textové pole
        editText = (EditText) findViewById(R.id.editText);
        editText.setKeyListener(null);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setKeyListener(null);
        seekArcProgress = (TextView) findViewById(R.id.seekArcProgress);
        seekArcProgress.setKeyListener(null);

        //Buttny
        btnNewTurtle = (Button) findViewById(R.id.button);
        btnForward = (Button) findViewById(R.id.button5);
        btnDeleteTurtle = (Button) findViewById(R.id.button2);
        btnClouseApp = (Button) findViewById(R.id.button8);
        btnBackward = (Button) findViewById(R.id.button4);
        btnIteration = (Button) findViewById(R.id.button7);
        btnPickColour = (Button) findViewById(R.id.button9);
        btnNewProcedure = (Button) findViewById(R.id.button3);
        btnChooseProcedure = (Button) findViewById(R.id.btnChooseProcedure);
        btnPlayCommands = (Button) findViewById(R.id.btnPlayCommands);

        //Ostatní
        ll = (LinearLayout) findViewById(R.id.rect);
        swipeNumberPicker = (SwipeNumberPicker) findViewById(R.id.number_picker);
        switchColor =  (Switch) findViewById(R.id.switch1);
        srf = (SurfaceView) findViewById(R.id.surfaceView);

        //Listview
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, commandList);
        commandList.addAll(Arrays.asList(commands));
        listView.setAdapter( listAdapter );

        //listOfProceduer
        ArrayList<Commands> listCtverec = new ArrayList<Commands>();
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",270));
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",180));
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",90));
        listCtverec.add(new Commands(1,"Vpřed",50));
        Procedure čtverec = new Procedure("Čtverec", listCtverec);
        listOfProcedure.add(čtverec);


    }

}
