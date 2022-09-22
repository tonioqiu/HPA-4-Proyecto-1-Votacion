package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Votacion extends AppCompatActivity {
    RadioButton rb1, rb2,rb3;
    ImageView image;
    Button btnVotar;
    MainActivity util = new MainActivity();
    String filename = "";
    String filepath = "";
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
        Bundle i = intent.getExtras();
        filename = "votos.txt";
        filepath = "votos";
        if (!isExternalStorageAviableforRW()){
            btnVotar.setEnabled(false);
        }
        oldVotos = readFileToArray(filename,filepath);
        newVotos = oldVotos;


        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                op=1;
                Toast.makeText(getApplicationContext(),Integer.toString(op), Toast.LENGTH_SHORT).show();
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
                int j = Integer.parseInt(i.get("arrayNum").toString());

                switch (op){
                    case 1:
                        newVotos[j] = "1";
                        try {
                            writeArrayToFile(newVotos, filename,filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        startActivity(intent);
                        break;

                    case 2:
                        newVotos[j] = "2";
                        try {
                            writeArrayToFile(newVotos, filename, filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        break;
                    case 3:
                        newVotos[j] = "3";
                        try {
                            writeArrayToFile(newVotos, filename, filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(),"Seleccione un candidato por favor.", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

    }

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
    public void writeArrayToFile(String arrayName[], String filename, String filepath) throws IOException {
        if (arrayName.length != 0){
            File file = new File(getExternalFilesDir(filepath),filename);
            FileWriter fw = new FileWriter(file);
            PrintWriter writer = new PrintWriter(fw);


            for(int j = 0; j < arrayName.length; j++) {
                writer.println(arrayName[j]);
                writer.flush();
            }

            writer.close();
        }

    };

    private boolean isExternalStorageAviableforRW(){
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }
}

