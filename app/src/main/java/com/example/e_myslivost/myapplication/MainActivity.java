package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;

import org.w3c.dom.Text;


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
    private SurfaceView srf;
    private SurfaceHolder sth;
    private NumberPicker numberPicker;
    private Turtle turtle;
    Canvas canvas;

    //proměnné pro colorPicker
    int defaultColorR =0;
    int defaultColorG = 0;
    int defaultColorB = 0;
    int selectedColorR;
    int selectedColorG;
    int selectedColorB;
    int selectedColorRGB;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
                    int x = 0;
                    int y = 0;

                    canvas = sth.lockCanvas();

                    //textView.setText("Vpřed:");
                    //... actual drawing on canvas
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(5);
                    int distance = seekBar.getProgress();
                    paint.setColor(turtle.getColor());

                    x = turtle.getNewXForeward(distance,turtle);
                    y = turtle.getNewYForeward(distance,turtle);

                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);

                    sth.unlockCanvasAndPost(canvas);

                    canvas = sth.lockCanvas();
                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                    sth.unlockCanvasAndPost(canvas);

                    canvas = sth.lockCanvas();
                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                    turtle.setX(x);
                    turtle.setY(y);
                    editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());
                    sth.unlockCanvasAndPost(canvas);
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
                    canvas = sth.lockCanvas();
                    //... actual drawing on canvas
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(5);
                    int distance = seekBar2.getProgress();
                    paint.setColor(turtle.getColor());
                    x = turtle.getNewXBackward(distance, turtle);
                    y = turtle.getNewYBackward(distance, turtle);
                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                    sth.unlockCanvasAndPost(canvas);

                    canvas = sth.lockCanvas();
                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                    sth.unlockCanvasAndPost(canvas);

                    canvas = sth.lockCanvas();
                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                    turtle.setX(x);
                    turtle.setY(y);
                    editText4.setText("X:" + x + "Y:" + y + "tX:" + turtle.getX() + "tY:" + turtle.getY());
                    sth.unlockCanvasAndPost(canvas);
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
                        } else {
                            Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
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
                }
            }
        }) ;


        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal)
            {
                numberPicker.setValue(newVal);
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
        btnPickColour = (Button) findViewById(R.id.button9);


        //Ostatní
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        srf = (SurfaceView) findViewById(R.id.surfaceView);
        sth = srf.getHolder();




    }

}
