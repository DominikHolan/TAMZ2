package com.example.hol0344.BrainTest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hol0344 on 12.12.17.
 */
public class BrainTest extends View{

    Bitmap[] bmp;
    MainActivity mainActivity;
    int lx = 10;
    int ly = 10;
    private boolean win = false;


    private final int nula = 0;
    private final int jedna = 1;
    private final int dva = 2;
    private final int tri = 3;
    private final int ctyri = 4;
    private final int pet = 5;
    private final int sest = 6;
    private final int sedm = 7;
    private final int osm = 8;
    private final int devet = 9;
    private final int back = 10;
    private final int back1 = 11;


    private int currentLevel = 0;
    int width;
    int height;
    Random r = new Random();
    int pomoc=3;
    int pocet = 3;
    int[] pole;
    int[] pozice;
    int[] pole2;
    public int[] level = {
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
    };
    public int[] level2 = {
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
    };
    public int[] level0 = {
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
            10,10,10,10,10,10,10,10,10,10,
    };
    public BrainTest(Context context) {
        super(context);
        init(context);
    }

    public BrainTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BrainTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        bmp = new Bitmap[12];
        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.zero);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.one);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.two);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.three);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.four);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.five);
        bmp[6] = BitmapFactory.decodeResource(getResources(), R.drawable.six);
        bmp[7] = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        bmp[8] = BitmapFactory.decodeResource(getResources(), R.drawable.eight);
        bmp[9] = BitmapFactory.decodeResource(getResources(), R.drawable.nine);
        bmp[10] = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        bmp[11] = BitmapFactory.decodeResource(getResources(), R.drawable.back1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[getLevel()[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);

            }
        }

        if(pocet!=0) {
            pomoc=pocet;
            generuj();

        }
        pocet=0;
       int u=0;
       boolean pravda = false;
        for(int v=0;v<pomoc;v++) {
            if (pole[v] == pole2[v])
                u++;
            if(u==pomoc){
                Nula();
                pocet=u+1;
                kontrola();
            }
        }
        for(int i=0;i<pomoc;i++)
            if(pole[i]!=pole2[i]&&pole2[i]!=-1)
                pravda=true;
        if(pravda)
            konec();
        invalidate();
    }

    public void Nula()
    {for (int i = 0; i < 100; i++)
        level0[i]=10;
        this.level=this.level0;

    }
    public int[] getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = this.level2;
        currentLevel = level;
    }

    public void resetLevel(){
        //Toast.makeText(getContext(), "Reset level: "+ currentLevel, Toast.LENGTH_SHORT).show();
        setLevel(currentLevel);
    }

    public void generuj()  {
        pole=new int[pocet];
        pole2=new int[pocet];
        pozice=new int[pocet];
        for(int k=0;k<pocet;k++)
        {   int i1 = r.nextInt(9 ) ;
            int i2 = r.nextInt(100 ) ;
            for(int g=0;g<pocet;g++)
            for(int j=0;j<pocet;j++)
                if (i2 == pozice[j] && g != j)
                    i2 = r.nextInt(100);
            level[i2]=i1;
            level2[i2]=11;
            pole[k]=i1;
            pozice[k]=i2;
            pole2[k]=-1;
        }

    }
    public void kontrola() {
        for(int k=0;k<pomoc;k++) {
           level2[pozice[k]]=10;

    }
    }
    public void pis(String x) {
        Toast.makeText(getContext(), x, Toast.LENGTH_SHORT).show();
    }
    public void gen(int j,int l)  {
     level[j]=l;
     level2[j]=l;
    }

    public void uloz(int k,int pozice)
    {
       for(int i=0;i<pomoc;i++)
        if(pozice==this.pozice[i])
            pole2[i]=k;

    }

    public void konec() {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(getContext());
        a_builder.setMessage("Konec").setCancelable(false)
                .setPositiveButton("Zavřít aplikaci", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.setTitle("Špatně zadané čisla");
        alert.show();

    }

}
