package com.example.hol0344.BrainTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Dominik on 15.12.2017.
 */

public class cislo extends Activity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    BrainTest brainTest;
    String text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cislo);
        b1 = (Button) findViewById(R.id.button4);
        b2 = (Button) findViewById(R.id.button5);
        b3 = (Button) findViewById(R.id.button6);
        b4 = (Button) findViewById(R.id.button7);
        b5 = (Button) findViewById(R.id.button8);
        b6 = (Button) findViewById(R.id.button9);
        b7 = (Button) findViewById(R.id.button10);
        b8 = (Button) findViewById(R.id.button11);
        b9 = (Button) findViewById(R.id.button12);
        b0 = (Button) findViewById(R.id.button3);
        set(b1);
        set(b2);
        set(b3);
        set(b4);
        set(b5);
        set(b6);
        set(b7);
        set(b8);
        set(b9);
        set(b0);

        }

    public void set(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(cislo.this, MainActivity.class);
                Intent i1 = new Intent();
                i1.putExtra("cislo",""+b.getText());
                setResult(1, i1);
                finish();
            }
        });

    }
}
