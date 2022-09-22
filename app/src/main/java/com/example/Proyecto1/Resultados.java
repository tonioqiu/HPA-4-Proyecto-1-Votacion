package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Resultados extends AppCompatActivity {

    TextView txtVotosOmar, txtVotosVivian, txtVotosMartin;
    Button buttonR;
    int voto1, voto2, voto3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        String[] votoArray = new String[40];

        //asignar objetos textview
        txtVotosOmar = findViewById(R.id.txtVotosOmar);
        txtVotosMartin = findViewById(R.id.txtVotosMartin);
        txtVotosVivian = findViewById(R.id.txtVotosVivian);

        //asignar boton
        buttonR = findViewById(R.id.btnRegresar);

        //File reader
        votoArray = readFileToArray("votos.txt");

        //Conteo de votos
        for (int i = 0; i < votoArray.length; i++ ){
            if (Integer.parseInt(votoArray[i]) == 1 )
                voto1++;
            else if (Integer.parseInt(votoArray[i]) == 2)
                voto2++;
            else
                voto3++;
        }

        //Camibio del texto en el textView
        txtVotosOmar.setText(voto1);
        txtVotosMartin.setText(voto2);
        txtVotosVivian.setText(voto3);

        //Boton de regreso a la pantalla principal
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //File Reader
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
}