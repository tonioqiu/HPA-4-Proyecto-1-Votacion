package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Resultados extends AppCompatActivity {

    TextView txtVotosOmar, txtVotosVivian, txtVotosMartin, txtvotosnulos, txtpercent1, txtpercent2,txtpercent3,txtpercent4;
    Button buttonR;
    int voto1, voto2, voto3, voto4, votoTotal=0;
    double voto1percent, voto2percent, voto3percent,voto4percent;
    String filename = "";
    String filepath = "";
    ProgressBar bar1, bar2, bar3, bar4;
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
        txtvotosnulos = findViewById(R.id.txtvotosnulos);
        txtpercent1 = findViewById(R.id.txtpercent1);
        txtpercent2 = findViewById(R.id.txtpercent2);
        txtpercent3 = findViewById(R.id.txtpercent3);
        txtpercent4 = findViewById(R.id.textpercent4);
        bar1 = findViewById(R.id.barVoto1);
        bar2 = findViewById(R.id.barVoto2);
        bar3 = findViewById(R.id.barVoto3);
        bar4 = findViewById(R.id.barvoto4);

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
            else if (Integer.parseInt(votoArray[i]) == 4)
                voto4++;
        }
        //Calculo de total de votos
        votoTotal= voto1+voto2+voto3+voto4;
        //Calculo del porcentaje de votos de cada candidato
        voto1percent = voto1*100.0/votoTotal;
        voto2percent = voto2*100.0/votoTotal;
        voto3percent = voto3*100.0/votoTotal;
        voto4percent = voto4*100.0/votoTotal;


        //impresion de porcentaje en texto
        NumberFormat formatter = new DecimalFormat("#0.00");
        String p1 = formatter.format(voto1percent) + "%";
        String p2 = formatter.format(voto2percent) + "%";
        String p3 = formatter.format(voto3percent) + "%";
        String p4 = formatter.format(voto4percent) + "%";

        txtpercent1.setText(p1);
        txtpercent2.setText(p2);
        txtpercent3.setText(p3);
        txtpercent4.setText(p4);

        //impresion de porcentaje en progressbar
        bar1.setProgress((int)voto1percent);
        bar2.setProgress((int)voto2percent);
        bar3.setProgress((int)voto3percent);
        bar4.setProgress((int)voto4percent);

        //Camibio del texto en el textView
        String a=Integer.toString(voto1);
        String b=Integer.toString(voto2);
        String c=Integer.toString(voto3);
        String d=Integer.toString(voto4);

        txtVotosOmar.setText(a);
        txtVotosMartin.setText(b);
        txtVotosVivian.setText(c);
        txtvotosnulos.setText(d);

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