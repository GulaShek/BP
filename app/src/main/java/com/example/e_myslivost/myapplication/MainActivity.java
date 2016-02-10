package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;


import java.util.Random;

import javax.security.auth.callback.Callback;



public class MainActivity extends Activity {




    private SeekBar seekBar;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private Button btnNewTurtle;
    private Button btnDeleteTurtle;
    private Button btnForward;
    private Button btnBackward;
    private Button btnClouseApp;
    private Button btnPickColour;
    private SurfaceView srf;
    private SurfaceHolder sth;
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
                    int delka = seekBar.getProgress();
                    paint.setColor(turtle.getColor());
                    if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {
                        x = (int) (turtle.getX() + delka * Math.cos(Math.toRadians(turtle.getAngle())));
                        y = (int) (turtle.getY() - delka * Math.sin(Math.toRadians(turtle.getAngle())));
                        editText4.setText("blok1 ");
                    }
                    if (turtle.getAngle() == 90) {
                        x = turtle.getX();
                        y = turtle.getY() - delka;
                    }
                    if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
                        x = (int) (turtle.getX() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 90)));
                        y = (int) (turtle.getY() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 90)));
                        editText4.setText("blok2");
                    }
                    if (turtle.getAngle() == 180) {
                        x = turtle.getX() - delka;
                        y = turtle.getY();
                    }
                    if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
                        x = (int) (turtle.getX() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 180)));
                        y = (int) (turtle.getY() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 180)));
                    }
                    if (turtle.getAngle() == 270) {
                        x = turtle.getX();
                        y = turtle.getY() + delka;
                    }
                    if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
                        x = (int) (turtle.getX() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 270)));
                        y = (int) (turtle.getY() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 270)));
                    }
                    if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
                        x = turtle.getX() + delka;
                        y = turtle.getY();
                    }


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

                    srf.setBackgroundColor(0xd7d5d5);
                    srf.setZOrderOnTop(true);
                    srf.getHolder().setFormat(PixelFormat.TRANSPARENT);
                    canvas = sth.lockCanvas();
                    //... actual drawing on canvas
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(5);
                    int delka = seekBar2.getProgress();
                    paint.setColor(turtle.getColor());
                    if (turtle.getAngle() > 0 && turtle.getAngle() < 90) {
                        x = (int) (turtle.getX() - delka * Math.cos(Math.toRadians(turtle.getAngle())));
                        y = (int) (turtle.getY() + delka * Math.sin(Math.toRadians(turtle.getAngle())));

                    }
                    if (turtle.getAngle() == 90) {
                        x = turtle.getX();
                        y = turtle.getY() + delka;
                    }
                    if (turtle.getAngle() > 90 && turtle.getAngle() < 180) {
                        x = (int) (turtle.getX() + delka * Math.sin(Math.toRadians(turtle.getAngle() - 90)));
                        y = (int) (turtle.getY() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 90)));

                    }
                    if (turtle.getAngle() == 180) {
                        x = turtle.getX() + delka;
                        y = turtle.getY();
                    }
                    if (turtle.getAngle() > 180 && turtle.getAngle() < 270) {
                        x = (int) (turtle.getX() + delka * Math.cos(Math.toRadians(turtle.getAngle() - 180)));
                        y = (int) (turtle.getY() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 180)));
                    }
                    if (turtle.getAngle() == 270) {
                        x = turtle.getX();
                        y = turtle.getY() - delka;
                    }
                    if (turtle.getAngle() > 270 && turtle.getAngle() < 360) {
                        x = (int) (turtle.getX() - delka * Math.sin(Math.toRadians(turtle.getAngle() - 270)));
                        y = (int) (turtle.getY() - delka * Math.cos(Math.toRadians(turtle.getAngle() - 270)));
                    }
                    if (turtle.getAngle() == 360 || turtle.getAngle() == 0) {
                        x = turtle.getX() - delka;
                        y = turtle.getY();
                    }
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
                Button okColor = (Button)cp.findViewById(R.id.okColorButton);

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
                        if(turtle != null)
                        {
                            turtle.setColor(selectedColorRGB);
                        }else
                        {
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

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                editText3.setText("" + seekBar3.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                editText3.setText("" + seekBar3.getProgress());
                if (turtle != null) {
                    turtle.setAngle(seekBar3.getProgress());
                    editText3.setText("" + seekBar3.getProgress() + " v:" + turtle.getAngle());
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
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        //Textové pole
        editText = (EditText) findViewById(R.id.editText);
        editText.setKeyListener(null);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setKeyListener(null);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText3.setKeyListener(null);
        editText4 = (EditText) findViewById(R.id.editText4);

        //Buttny
        btnNewTurtle = (Button) findViewById(R.id.button);
        btnForward = (Button) findViewById(R.id.button5);
        btnDeleteTurtle = (Button) findViewById(R.id.button2);
        btnClouseApp = (Button) findViewById(R.id.button8);
        btnBackward = (Button) findViewById(R.id.button4);
        btnPickColour = (Button) findViewById(R.id.button9);


        //Ostatní
        srf = (SurfaceView) findViewById(R.id.surfaceView);
        sth = srf.getHolder();




    }

}
