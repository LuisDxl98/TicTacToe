package com.apps.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.media.Image;
import java.util.Vector;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    ImageButton img1;
    ImageButton img2;
    ImageButton img3;
    ImageButton img4;
    ImageButton img5;
    ImageButton img6;
    ImageButton img7;
    ImageButton img8;
    ImageButton img9;
    RadioButton r1;
    RadioButton r2;
    ToggleButton tb1;
    TextView t1;
    int op;
    Image im;
    boolean toggle;
    Vector<String> jugador1;
    Vector<String> jugador2;
    int stateGame = 0;
    int[][] table = {{3,4,5},{6,7,8},{9,10,11}};
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize(savedInstanceState);
        initializeLogic();
    }
    private void initializeLogic(){
        t1.setVisibility(View.INVISIBLE);
        op = 0;
        toggle=false;

    }
    private void initialize(Bundle savedInstanceState){
        img1 = (ImageButton) findViewById(R.id.imageButton);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        img3 = (ImageButton) findViewById(R.id.imageButton3);
        img4 = (ImageButton) findViewById(R.id.imageButton4);
        img5 = (ImageButton) findViewById(R.id.imageButton5);
        img6 = (ImageButton) findViewById(R.id.imageButton6);
        img7 = (ImageButton) findViewById(R.id.imageButton7);
        img8 = (ImageButton) findViewById(R.id.imageButton8);
        img9 = (ImageButton) findViewById(R.id.imageButton9);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        tb1 = (ToggleButton) findViewById(R.id.toggleButton);
        t1 = (TextView) findViewById(R.id.textView);

        tb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    resetGame();
                    r1.setChecked(true);
                    Toast.makeText(getBaseContext(), "Juego iniciado", Toast.LENGTH_SHORT).show();
                    stateGame = 1;
                }else{
                    stateGame = 0;
                    Toast.makeText(getBaseContext(), "Juego detenido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void resetGame(){
        r1.setChecked(false);
        r2.setChecked(false);
        int[][] table2 = {{3,4,5},{6,7,8},{9,10,11}};
        table = table2;
        img1.setImageResource(R.mipmap.squere);
        img2.setImageResource(R.mipmap.squere);
        img3.setImageResource(R.mipmap.squere);
        img4.setImageResource(R.mipmap.squere);
        img5.setImageResource(R.mipmap.squere);
        img6.setImageResource(R.mipmap.squere);
        img7.setImageResource(R.mipmap.squere);
        img8.setImageResource(R.mipmap.squere);
        img9.setImageResource(R.mipmap.squere);
        op = 0;
        toggle=false;
        contador=0;
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    //Aqui va X
                    op = 1;
                    break;
            case R.id.radioButton2:
                if (checked)
                    //Aqui va O
                    op = 2;
                    break;
        }
    }
    public void toggleButton(View v){
        if(stateGame == 1){
            contador++;
            ImageButton imB = (ImageButton) v;
            Drawable drawable = imB.getDrawable();
            int img=0;
            if(r1.isChecked()){
                img = R.mipmap.cruz;
                setClickOnGamer(imB, 1);
                r2.setChecked(true);
                r1.setChecked(false);
            }else{
                img = R.mipmap.cero;
                setClickOnGamer(imB, 2);
                r1.setChecked(true);
                r2.setChecked(false);
            }
            imB.setImageResource(img);
        }else{
            Toast.makeText(getBaseContext(), "Inicie el juego por favor",Toast.LENGTH_SHORT).show();
        }
    }
    public void setClickOnGamer(ImageButton imB, int pp){
        if(stateGame == 1) {
            if (imB.getId() == img1.getId()) {
                table[0][0] = pp;
            }
            if (imB.getId() == img2.getId()) {
                table[0][1] = pp;
            }
            if (imB.getId() == img3.getId()) {
                table[0][2] = pp;
            }
            if (imB.getId() == img4.getId()) {
                table[1][0] = pp;
            }
            if (imB.getId() == img5.getId()) {
                table[1][1] = pp;
            }
            if (imB.getId() == img6.getId()) {
                table[1][2] = pp;
            }
            if (imB.getId() == img7.getId()) {
                table[2][0] = pp;
            }
            if (imB.getId() == img8.getId()) {
                table[2][1] = pp;
            }
            if (imB.getId() == img9.getId()) {
                table[2][2] = pp;
            }
            if (checkWinner() == 1){
                if (r2.isChecked()){
                    Toast.makeText(getBaseContext(), "El ganador es el jugador 2", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), "El ganador es el jugador 1", Toast.LENGTH_SHORT).show();
                }
                stateGame = 0;
            }else if (checkWinner() == 2){
                Toast.makeText(getBaseContext(), "Hubo un empate", Toast.LENGTH_SHORT).show();
                stateGame = 0;
            }
        }
    }
    public int checkWinner() {
        if (stateGame == 1) {
            if (table[0][0] == table[0][1] && table[0][0] == table[0][2]) {
                return 1;
            } else if (table[1][0] == table[1][1] && table[1][0] == table[1][2]) {
                return 1;
            } else if (table[2][0] == table[2][1] && table[2][0] == table[2][2]) {
                return 1;
            } else if (table[0][0] == table[1][0] && table[0][0] == table[2][0]) {
                return 1;
            } else if (table[0][1] == table[1][1] && table[0][1] == table[2][1]) {
                return 1;
            } else if (table[0][2] == table[1][2] && table[0][2] == table[2][2]) {
                return 1;
            } else if (table[0][0] == table[1][1] && table[0][0] == table[2][2]) {
                return 1;
            } else if (table[0][2] == table[1][1] && table[0][2] == table[2][0]) {
                return 1;
            } else if (contador>8){
                return 2;
            }else{
                return 0;
            }
        }else {
            return 0;
        }
    }
}