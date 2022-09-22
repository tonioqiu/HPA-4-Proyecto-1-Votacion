package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Resultados extends AppCompatActivity {

    TextView txtVotosOmar, txtVotosVivian, txtVotosMartin;
    Button buttonR;
    int voto1, voto2, voto3;
    String filename = "";
    String filepath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        String[] votoArray = new String[40];

        filename = "votos.txt";
        filepath = "votos";
        //asignar objetos textview
        txtVotosOmar = findViewById(R.id.txtVotosOmar);
        txtVotosMartin = findViewById(R.id.txtVotosMartin);
        txtVotosVivian = findViewById(R.id.txtVotosVivian);

        //asignar boton
        buttonR = findViewById(R.id.btnRegresar);

        //File reader
        votoArray = readFileToArray(filename,filepath);

        //Conteo de votos
        for (int i = 0; i < votoArray.length; i++ ){
            if (Integer.parseInt(votoArray[i]) == 1 )
                voto1++;
            else if (Integer.parseInt(votoArray[i]) == 2)
                voto2++;
            else if (Integer.parseInt(votoArray[i]) == 3)
                voto3++;
        }

        //Camibio del texto en el textView
        String a=Integer.toString(voto1);
        String b=Integer.toString(voto2);
        String c=Integer.toString(voto3);
        txtVotosOmar.setText(a);
        txtVotosMartin.setText(b);
        txtVotosVivian.setText(c);

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
    public String[] readFileToArray(String filename,String filepath){
        String array[] = new String[40];
        FileReader fr = null;
        File file = new File(getExternalFilesDir(filepath),filename);
        try {
            fr = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fr);
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