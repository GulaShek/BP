package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;
import com.vi.swipenumberpicker.OnValueChangeListener;
import com.vi.swipenumberpicker.SwipeNumberPicker;

import org.w3c.dom.Text;

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
    private Button btnIteration;
    private SurfaceView srf;
    private SurfaceHolder sth;
    private SwipeNumberPicker swipeNumberPicker;
    private Switch switchColor;
    private Turtle turtle;
    Canvas canvas;

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
                    if (sth.getSurface().isValid()) {
                        int distance = seekBar.getProgress();
                        int x = 0;
                        int y = 0;

                        x = turtle.getNewXForeward(distance, turtle);
                        y = turtle.getNewYForeward(distance, turtle);
                        if(switchColor.isChecked())
                        {
                            canvas = sth.lockCanvas();
                            //... actual drawing on canvas
                            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                            paint.setStyle(Paint.Style.FILL);
                                paint.setStrokeWidth(5);

                            paint.setColor(turtle.getColor());
                            canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);

                            sth.unlockCanvasAndPost(canvas);

                            canvas = sth.lockCanvas();
                                canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                            sth.unlockCanvasAndPost(canvas);

                            canvas = sth.lockCanvas();
                                canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);

                                editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());
                            sth.unlockCanvasAndPost(canvas);
                        }
                        turtle.setX(x);
                        turtle.setY(y);
                        listAdapter.add("Vpřed " + distance);
                        listView.setAdapter(listAdapter);
                        }
            }else
            {
                Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
            }
            }
        });

        btnBackward.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(turtle != null){
                if (sth.getSurface().isValid()) {
                    int x = 0;
                    int y = 0;
                    int distance = seekBar2.getProgress();
                    x = turtle.getNewXBackward(distance, turtle);
                    y = turtle.getNewYBackward(distance, turtle);

                    if(switchColor.isChecked()) {

                        canvas = sth.lockCanvas();
                        //... actual drawing on canvas
                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                        paint.setStyle(Paint.Style.FILL);
                        paint.setStrokeWidth(5);

                        paint.setColor(turtle.getColor());

                        canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                        sth.unlockCanvasAndPost(canvas);

                        canvas = sth.lockCanvas();
                        canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                        sth.unlockCanvasAndPost(canvas);

                        canvas = sth.lockCanvas();
                        canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                        editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());
                        sth.unlockCanvasAndPost(canvas);
                    }
                    turtle.setX(x);
                    turtle.setY(y);


                    listAdapter.add("Vzad " + distance);
                    listView.setAdapter(listAdapter);

                }
                }else
                {
                    Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnNewTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                turtle = new Turtle();
                turtle.setX(srf.getWidth() / 2);
                turtle.setY(srf.getHeight() / 2);

                Toast.makeText(getApplicationContext(), "Vytvořili jste novou želvu!", Toast.LENGTH_LONG).show();
                if (sth.getSurface().isValid()) {
                    canvas = sth.lockCanvas();
                    srf.setBackgroundColor(0xd7d5d5);
                    //... actual drawing on canvas
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(5);
                    paint.setColor(turtle.getColor());
                    canvas.drawCircle(turtle.getX(), turtle.getY(), 25, paint);


                    sth.unlockCanvasAndPost(canvas);
                }

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
                        btnPickColour.setBackgroundColor(selectedColorRGB);
                        if (turtle != null) {
                            turtle.setColor(selectedColorRGB);
                            listAdapter.add("Nová barva ");
                            listView.setAdapter(listAdapter);

                        } else {
                            Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });
        btnIteration.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                commandList.add(0,"Opakovat " + swipeNumberPicker.getValue());
                commandList.add(listAdapter.getCount(),"Konec opakování");
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
        }) ;


        swipeNumberPicker.setOnValueChangeListener(new OnValueChangeListener() {
            @Override
            public boolean onValueChange(SwipeNumberPicker view, int oldValue, int newValue) {
                boolean isValueOk = (newValue & 1) == 0;
                if (newValue < 10 &&  newValue  > 0)
                {
                    isValueOk = true;
                }else
                {
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
                    if(turtle != null)
                    {
                        listAdapter.add("Kreslení zapnuto");
                        listView.setAdapter(listAdapter);
                    }

                } else {
                    if(turtle != null)
                    {
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


        //Ostatní
        swipeNumberPicker = (SwipeNumberPicker) findViewById(R.id.number_picker);
        switchColor =  (Switch) findViewById(R.id.switch1);
        srf = (SurfaceView) findViewById(R.id.surfaceView);
        sth = srf.getHolder();

        //Listview
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, commandList);
        commandList.addAll( Arrays.asList(commands) );
        listView.setAdapter( listAdapter );

    }

}
