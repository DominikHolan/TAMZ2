package com.example.hol0344.BrainTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends Activity {
    float x1, y1;
    Button button;
    BrainTest brainTest;
    int f;
    int myNum = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brainTest = (BrainTest) findViewById(R.id.map);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                brainTest.resetLevel();

            }
        });
        new BrainTest(this);
        brainTest.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int q = 0, p = 0;
                    for (int i = 0; i < 100; i++) {
                        q = i / 10;
                        p = i % 10;
                        if (brainTest.level[i] != 10)
                            if ((x1 > p * 65 && x1 < p * 65 + 65) && (y1 > q * 95 && y1 < q * 95 + 95)) {
                                if (brainTest.level[i] > 10) {
                                    f = i;
                                    otevri();
                                }
                            }
                    }
                }


                if(brainTest.number>brainTest.top)
                write();
                return true;
            }
        });
        read();


   }

    public void read() {
        try {
            FileInputStream fileInputStream= openFileInput("myText.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines=bufferedReader.readLine())!=null) {
                stringBuffer.append(lines);
            }
            brainTest.top=Integer.parseInt(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write() {
        String Mytextmessage;
        if(brainTest.number>brainTest.top)
        Mytextmessage  = brainTest.number+"";
        else
             Mytextmessage= brainTest.top+"";
        try {
            FileOutputStream fileOutputStream = openFileOutput("myText.txt",MODE_PRIVATE);
            fileOutputStream.write(Mytextmessage.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void otevri() {
        Intent i = new Intent(MainActivity.this, cislo.class);
        startActivityForResult(i, 1);
    }
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == 1) {
            if (resultCode == 1) {
                String message = data.getStringExtra("cislo");
                try {
                    myNum = Integer.parseInt(message);
                } catch (NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }
                brainTest.gen(f, myNum);
                brainTest.uloz(myNum, f);

            }

        }
    }


    }


