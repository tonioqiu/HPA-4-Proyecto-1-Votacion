package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Votacion extends AppCompatActivity {
    RadioButton rb1, rb2,rb3;
    ImageView image;
    Button btnVotar;
    MainActivity util = new MainActivity();
    int op = 0;
    String[] oldVotos = new String[40];
    String[] newVotos = new String[40];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        btnVotar = findViewById(R.id.btnVotar);
        image = findViewById(R.id.imageVoto);
        Intent intent = getIntent();
        int i = intent.getIntExtra("arrayNum",40);
        oldVotos = readFileToArray("Votos");
        newVotos = oldVotos;

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                op=1;
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op=2;
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                op=3;
            }
        });

        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                switch (op){
                    case 1:
                        newVotos[i] = "1";
                        writeArrayToFile(newVotos, "votos.txt");
                        startActivity(intent);
                        break;

                    case 2:
                        newVotos[i] = "2";
                        writeArrayToFile(newVotos, "votos.txt");
                        startActivity(intent);
                        break;
                    case 3:
                        newVotos[i] = "3";
                        writeArrayToFile(newVotos, "votos.txt");
                        startActivity(intent);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"Seleccione un candidato por favor.", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

    }

    public String[] readFileToArray(String filename){
        String array[] = new String[40];
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
            ArrayList<String> userlist = new ArrayList<String>();
            String line = bReader.readLine();
            while (line != null) {
                userlist.add(line);
                line = bReader.readLine();
            }
            bReader.close();
            array = userlist.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    };
    public void writeArrayToFile(String arrayName[], String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Votos.txt", false));
            for(int i = 0; i < arrayName.length; i++) {
                writer.write(arrayName[i].toString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    };


}