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
    private EditText editText4;
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
    private SurfaceView srf;
    private SurfaceHolder sth;
    private SwipeNumberPicker swipeNumberPicker;
    private Switch switchColor;
    private Turtle turtle;
    Canvas canvas;
    ArrayList<Procedure> listOfProcedure = new ArrayList<Procedure>();


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

    Bitmap bg = Bitmap.createBitmap(960, 1600, Bitmap.Config.ARGB_8888);
    Canvas canvas2 = new Canvas(bg);
    LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();//skryje vrchní lištu
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //zabrání změnám orientace
        initComopnents();


        btnDeleteTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                turtle = null;
            }
        });

        btnForward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(turtle != null){

                        int distance = seekBar.getProgress();
                        int x = 0;
                        int y = 0;

                        x = turtle.getNewXForward(distance, turtle);
                        y = turtle.getNewYForward(distance, turtle);
                        if(switchColor.isChecked())
                        {
                            //... actual drawing on canvas
                            Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                            paint.setStyle(Paint.Style.FILL);
                            paint.setStrokeWidth(5);


                            paint.setColor(turtle.getColor());
                            canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                            ll.setBackgroundDrawable(new BitmapDrawable(bg));
                            editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());
                        }

                        turtle.setX(x);
                        turtle.setY(y);
                        listAdapter.add("Vpřed " + distance);
                        listView.setAdapter(listAdapter);



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

                    int x = 0;
                    int y = 0;
                    int distance = seekBar2.getProgress();
                    x = turtle.getNewXBackward(distance, turtle);
                    y = turtle.getNewYBackward(distance, turtle);

                    if (switchColor.isChecked()) {

                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        paint.setStyle(Paint.Style.FILL);
                        paint.setStrokeWidth(5);

                        paint.setColor(turtle.getColor());

                        canvas2.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                        ll.setBackgroundDrawable(new BitmapDrawable(bg));
                        editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());

                    }
                    turtle.setX(x);
                    turtle.setY(y);


                    listAdapter.add("Vzad " + distance);
                    listView.setAdapter(listAdapter);


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
                    paint.setColor(Color.rgb(255,252,190));
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
                            turtle.setColor(selectedColorRGB);
                            listAdapter.add("Nová barva ");
                            listView.setAdapter(listAdapter);
                            btnPickColour.setBackgroundColor(selectedColorRGB);

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
                        for (int i = 0;i < listOfProcedure.size();i++) {
                            if (listOfProcedure.get(i).procedureName.equals(nameOfProcedure)) {
                                isOk = false;
                            }
                        }
                        if(isOk) {
                            Procedure p1 = new Procedure(nameOfProcedure, tp.commands);
                            listOfProcedure.add(p1);
                            tp.dismiss();
                        }
                        else
                        {
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


                        //načtení procedury
                        /*
                        int item = chp.listViewChooseProcedure.getSelectedItemPosition();
                        String s = chp.listViewChooseProcedure.getAdapter().getItem(item+2).toString();
                        Toast.makeText(getApplicationContext(), "Vybral jsi " + s, Toast.LENGTH_LONG).show();
                        */
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
                commandList.add(0,"Opakovat " + swipeNumberPicker.getValue());
                commandList.add(listAdapter.getCount(), "Konec opakování");
                commandList.addAll(Arrays.asList(commands));
                listView.setAdapter(listAdapter);
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
                    turtle.setAngle(seekArc.getProgress());
                    listAdapter.add("Otočit " + seekArc.getProgress());
                    listView.setAdapter(listAdapter);
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
                    }

                } else {
                    if (turtle != null) {
                        listAdapter.add("Kreslení vypnuto");
                        listView.setAdapter(listAdapter);
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
        editText4 = (EditText) findViewById(R.id.editText4);
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

        //Ostatní
        ll = (LinearLayout) findViewById(R.id.rect);
        swipeNumberPicker = (SwipeNumberPicker) findViewById(R.id.number_picker);
        switchColor =  (Switch) findViewById(R.id.switch1);
        srf = (SurfaceView) findViewById(R.id.surfaceView);
        sth = srf.getHolder();

        //Listview
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, commandList);
        commandList.addAll(Arrays.asList(commands));
        listView.setAdapter( listAdapter );

        //listOfProceduer
        Procedure čtverec = new Procedure("Čtverec", new String[]{"Vpřed 50", "Otoč 90", "Vpřed 50"});
        Procedure trojuhlenik = new Procedure("trojuhelnik", new String[]{"Vpřed 50", "Otoč 90", "Vpřed 50"});
        listOfProcedure.add(čtverec);
        listOfProcedure.add(trojuhlenik);


    }

}
