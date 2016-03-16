package com.example.e_myslivost.myapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
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
    private TextView twUhel;
    private TextView twX;
    private TextView twY;
    private Button btnNewTurtle;
    private Button btnDeleteTurtle;
    private Button btnForward;
    private Button btnBackward;
    private Button btnDeleteCommands;
    private Button btnPickColour;
    private Button btnNewProcedure;
    private Button btnChooseProcedure;
    private Button btnIteration;
    private Button btnPlayCommands;
    private SwipeNumberPicker swipeNumberPicker;
    private Switch switchColor;
    private Turtle turtle;
    private boolean isTurtle = false;
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

    //Pro vykreslování
    Canvas canvas;
    Canvas canvas2;
    public ImageView drawingImageView;
    public ImageView viewForDrawingTurtle;
    public Bitmap turtleIcon;
    public Bitmap bmpRotate;
    Matrix mat;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR); //zabrání změnám orientace
        initComopnents();
        final Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth()+400, (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        final Bitmap bitmap2 = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth()+400, (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        //final Bitmap bitmap = Bitmap.createBitmap(2000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);
        canvas2 = new Canvas(bitmap2);
        viewForDrawingTurtle.setImageBitmap(bitmap2);


        btnNewTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!isTurtle) {
                    turtle = new Turtle();
                    turtle.setX(getWindowManager().getDefaultDisplay().getWidth() / 2);
                    turtle.setY(getWindowManager().getDefaultDisplay().getHeight() / 2);
                    Toast.makeText(getApplicationContext(), "Vytvořili jste novou želvu!", Toast.LENGTH_LONG).show();
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setStyle(Paint.Style.FILL);
                    paint.setStrokeWidth(3);
                    paint.setColor(Color.rgb(255, 252, 190));

                    twX.setText(turtle.getX() + "");
                    twY.setText(turtle.getY() + "");

                    drawingImageView.setBackgroundColor(Color.rgb(255, 251, 181));
                    drawingImageView.invalidate();


                    canvas2.drawBitmap(turtleIcon, turtle.getX() - 30, turtle.getY() - 30, paint);
                    viewForDrawingTurtle.invalidate();
                    isTurtle = !isTurtle;
                }else
                {
                    Toast.makeText(getApplicationContext(), "Nedříve smažte obrazovku, poté můžete vytvořit novou želvu!", Toast.LENGTH_LONG).show();
                }


            }
        });


        btnDeleteTurtle.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(3);
                paint.setColor(Color.rgb(255, 252, 190));
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                drawingImageView.invalidate();

                canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                viewForDrawingTurtle.invalidate();

                turtle = null;
                isTurtle = !isTurtle;
                setComoponentToZero();
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



        btnPickColour.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ColorPicker cp = new ColorPicker(MainActivity.this, defaultColorR, defaultColorG, defaultColorB);
                cp.show();
                /* On Click listener for the dialog, when the user select the color */
                Button okColor = (Button) cp.findViewById(R.id.okColorButton);
                okColor.setBackgroundResource(R.drawable.button_selector);
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
                            Commands f = new Commands(4, "Změna barvy", selectedColorRGB);
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
                            if(tp.commandList.size() > 0) {
                                Procedure p1 = new Procedure(nameOfProcedure, tp.listOfCommands);
                                listOfProcedure.add(p1);
                            }
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
                listOfCommands.add(listOfCommands.size(), c2);


            }

        });
        btnDeleteCommands.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                commandList.clear();
                commandList.addAll(Arrays.asList(commands));
                listView.setAdapter(listAdapter);
                listOfCommands.clear();
            }

        });
        btnPlayCommands.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
                viewForDrawingTurtle.invalidate();
                int counterOfCommands = listOfCommands.size();
                int x = 0;
                int y = 0;
                if(turtle != null) {

                    if(counterOfCommands != 0) {
                        for (int i = 0; i < counterOfCommands; i++) {
                            int command = listOfCommands.get(i).getNumberOfCommand();

                            switch (command) {

                                case 1:


                                    x = turtle.getNewXForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                    y = turtle.getNewYForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                    if (turtle.isDraw()) {
                                        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                                        paint.setStyle(Paint.Style.FILL);
                                        paint.setStrokeWidth(5);
                                        paint.setColor(turtle.getColor());
                                        canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                        drawingImageView.invalidate();
                                    }

                                    turtle.setX(x);
                                    turtle.setY(y);
                                    twX.setText(x + "");
                                    twY.setText(y +"");
                                    break;
                                case 2:


                                    x = turtle.getNewXBackward(listOfCommands.get(i).getCommandDistance(), turtle);
                                    y = turtle.getNewYBackward(listOfCommands.get(i).getCommandDistance(), turtle);

                                    if (turtle.isDraw()) {

                                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                                        paint.setStyle(Paint.Style.FILL);
                                        paint.setStrokeWidth(5);
                                        paint.setColor(turtle.getColor());
                                        canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                        drawingImageView.invalidate();
                                    }
                                    turtle.setX(x);
                                    turtle.setY(y);
                                    twX.setText(x + "");
                                    twY.setText(y + "");

                                    break;
                                case 3:
                                    turtle.setAngle(listOfCommands.get(i).getCommandDistance());
                                    twUhel.setText(turtle.getAngle() + "°");

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
                                case 6:
                                    int storage = i;
                                    int iterator = listOfCommands.get(i).getCommandDistance();


                                    for (int g = 0; g <= iterator; ) {
                                        i++;
                                        if (listOfCommands.get(i).getNumberOfCommand() == 7) {

                                            g++;
                                            i = storage;

                                        }

                                        command = listOfCommands.get(i).getNumberOfCommand();
                                        switch (command) {

                                            case 1:


                                                x = turtle.getNewXForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                                y = turtle.getNewYForward(listOfCommands.get(i).getCommandDistance(), turtle);
                                                if (turtle.isDraw()) {
                                                    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                                                    paint.setStyle(Paint.Style.FILL);
                                                    paint.setStrokeWidth(5);
                                                    paint.setColor(turtle.getColor());
                                                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                                    drawingImageView.invalidate();
                                                }

                                                turtle.setX(x);
                                                turtle.setY(y);
                                                twX.setText(x + "");
                                                twY.setText(y + "");

                                                break;
                                            case 2:


                                                x = turtle.getNewXBackward(listOfCommands.get(i).getCommandDistance(), turtle);
                                                y = turtle.getNewYBackward(listOfCommands.get(i).getCommandDistance(), turtle);

                                                if (turtle.isDraw()) {

                                                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                                                    paint.setStyle(Paint.Style.FILL);
                                                    paint.setStrokeWidth(5);
                                                    paint.setColor(turtle.getColor());
                                                    canvas.drawLine(turtle.getX(), turtle.getY(), x, y, paint);
                                                    drawingImageView.invalidate();

                                                }
                                                turtle.setX(x);
                                                turtle.setY(y);
                                                twX.setText(x + "");
                                                twY.setText(y + "");

                                                break;
                                            case 3:
                                                turtle.setAngle(listOfCommands.get(i).getCommandDistance());
                                                twUhel.setText(turtle.getAngle() + "°");

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
                                case 8:

                                    if(turtle.getAngle() + listOfCommands.get(i).getCommandDistance() < 360)
                                    {
                                        turtle.setAngle(turtle.getAngle() + listOfCommands.get(i).getCommandDistance());
                                    }else
                                    {
                                        int a = turtle.getAngle() + listOfCommands.get(i).getCommandDistance();
                                        turtle.setAngle(a%360);
                                    }

                                    turtle.setAngle(turtle.getAngle());

                                    break;

                                default:

                                    break;
                            }


                        }
                        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

                        mat.setRotate(turtle.getAngle());
                        bmpRotate = Bitmap.createBitmap(turtleIcon, 0, 0, turtleIcon.getWidth(), turtleIcon.getHeight(), mat, true);
                        canvas2.drawBitmap(bmpRotate, turtle.getX()-(turtleIcon.getWidth()/2), turtle.getY()-(turtleIcon.getHeight()/2), paint);

                        viewForDrawingTurtle.invalidate();
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Nemáte vytvořený žádný příkaz!", Toast.LENGTH_LONG).show();
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), "Nemáte vytvořenou želvu!", Toast.LENGTH_LONG).show();
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
                    Commands c = new Commands(3, "Otočit", seekArc.getProgress());
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
                        Commands c = new Commands(5, "Kresleni", 1);
                        listOfCommands.add(c);
                    }

                } else {
                    if (turtle != null) {
                        listAdapter.add("Kreslení vypnuto");
                        listView.setAdapter(listAdapter);
                        Commands c = new Commands(5, "Kresleni", 0);
                        listOfCommands.add(c);
                    }
                }

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {
                //nove
                listAdapter = (ArrayAdapter) arg0.getAdapter();
                listAdapter.remove(listAdapter.getItem(position));
                listAdapter.notifyDataSetChanged();
                listOfCommands.remove(position);
                //konec
                return false;

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
        twUhel = (TextView) findViewById(R.id.twUhel);
        twX = (TextView) findViewById(R.id.twX);
        twY = (TextView) findViewById(R.id.twY);

        //Buttny
        btnNewTurtle = (Button) findViewById(R.id.button);
        btnNewTurtle.setBackgroundResource(R.drawable.button_selector);
        btnForward = (Button) findViewById(R.id.button5);
        btnForward.setBackgroundResource(R.drawable.button_selector);
        btnDeleteTurtle = (Button) findViewById(R.id.button2);
        btnDeleteTurtle.setBackgroundResource(R.drawable.button_selector);
        btnDeleteCommands = (Button) findViewById(R.id.button8);
        btnDeleteCommands.setBackgroundResource(R.drawable.button_selector);
        btnBackward = (Button) findViewById(R.id.button4);
        btnBackward.setBackgroundResource(R.drawable.button_selector);
        btnIteration = (Button) findViewById(R.id.button7);
        btnIteration.setBackgroundResource(R.drawable.button_selector);
        btnPickColour = (Button) findViewById(R.id.button9);
        btnPickColour.setBackgroundResource(R.drawable.button_selector);
        btnNewProcedure = (Button) findViewById(R.id.button3);
        btnNewProcedure.setBackgroundResource(R.drawable.button_selector);
        btnChooseProcedure = (Button) findViewById(R.id.btnChooseProcedure);
        btnChooseProcedure.setBackgroundResource(R.drawable.button_selector);
        btnPlayCommands = (Button) findViewById(R.id.btnPlayCommands);
        btnPlayCommands.setBackgroundResource(R.drawable.button_play_selector);

        //Ostatní
        swipeNumberPicker = (SwipeNumberPicker) findViewById(R.id.number_picker);
        swipeNumberPicker.setValue(1,true);
        switchColor =  (Switch) findViewById(R.id.switch1);
        drawingImageView = (ImageView) this.findViewById(R.id.rect2);
        viewForDrawingTurtle = (ImageView) this.findViewById(R.id.viewForTurtle);
        turtleIcon = BitmapFactory.decodeResource(getResources(), R.drawable.turtleicon);
        mat = new Matrix();

        //Listview - pro zobrazení příkazu uživteli
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, commandList);
        commandList.addAll(Arrays.asList(commands));
        listView.setAdapter( listAdapter );

        //listOfProceduer
        ArrayList<Commands> listCtverec = new ArrayList<Commands>();
        listCtverec.add(new Commands(3,"Otoč",0));
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",270));
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",180));
        listCtverec.add(new Commands(1,"Vpřed",50));
        listCtverec.add(new Commands(3,"Otoč",90));
        listCtverec.add(new Commands(1,"Vpřed",50));
        Procedure ctverec = new Procedure("Čtverec", listCtverec);
        ArrayList<Commands> listTrojuhlenik = new ArrayList<Commands>();
        listTrojuhlenik.add(new Commands(3,"Otoč",0));
        listTrojuhlenik.add(new Commands(1,"Vpřed",50));
        listTrojuhlenik.add(new Commands(3,"Otoč",225));
        listTrojuhlenik.add(new Commands(1,"Vpřed",50));
        listTrojuhlenik.add(new Commands(3,"Otoč",135));
        listTrojuhlenik.add(new Commands(1,"Vpřed",50));
        listTrojuhlenik.add(new Commands(3,"Otoč",0));
        listTrojuhlenik.add(new Commands(1,"Vpřed",50));
        Procedure trojuhleni = new Procedure("Trojuhlenik", listTrojuhlenik);
        ArrayList<Commands> listKolecko = new ArrayList<Commands>();
        for(int i = 0; i < 180; i++) {
            listKolecko.add(new Commands(1, "Vpřed", 2));
            listKolecko.add(new Commands(8, "Otoč", 2));
        }
        Procedure kolecko = new Procedure("Kolecko", listKolecko);
        listOfProcedure.add(ctverec);
        listOfProcedure.add(trojuhleni);
        listOfProcedure.add(kolecko);


    }

    public void setComoponentToZero()
    {
        seekBar.setProgress(0);
        seekBar2.setProgress(0);
        seekBarArc.setProgress(0);
        seekArcProgress.setText("0");
        switchColor.setChecked(true);
        swipeNumberPicker.setValue(1, true);
        twX.setText("0");
        twY.setText("0");
        twUhel.setText("0°");
        btnPickColour.setBackgroundResource(R.drawable.button_selector);
    }

}
