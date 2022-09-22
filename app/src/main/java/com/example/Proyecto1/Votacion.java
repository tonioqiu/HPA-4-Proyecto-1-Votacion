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

        //Creacion un arraglo con los datos existentes y un nuevo arreglo para datos nuevos
        oldVotos = readFileToArray(filename,filepath);
        newVotos = oldVotos;


        //Radio button para cambiar la imagen y voto

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.omar_aizpurua);
                op=1;
                Toast.makeText(getApplicationContext(),"Se ha Seleccionado Omar Aizpurua", Toast.LENGTH_SHORT).show();
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.martin_candanedo);
                op=2;
                Toast.makeText(getApplicationContext(),"Se ha Seleccionado Martin Candenedo", Toast.LENGTH_SHORT).show();
            }
        });

        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.vivianvalenzuela);
                op=3;
                Toast.makeText(getApplicationContext(),"Se ha Seleccionado Vivian Valenzuela8-941-1215", Toast.LENGTH_SHORT).show();

            }
        });

            //Click del boton Votar
        btnVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Resultados.class);
                int j = Integer.parseInt(i.get("arrayNum").toString());

                switch (op){
                    //voto 1
                    case 1:
                        newVotos[j] = "1";
                        try {
                            writeArrayToFile(newVotos, filename,filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        startActivity(intent);
                        break;

                     //voto 2
                    case 2:
                        newVotos[j] = "2";
                        try {
                            writeArrayToFile(newVotos, filename, filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        break;
                    //voto 3
                    case 3:
                        newVotos[j] = "3";
                        try {
                            writeArrayToFile(newVotos, filename, filepath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);
                        break;
                    //Si no ha seleccionado ningun candidato
                    default:
                        Toast.makeText(getApplicationContext(),"Seleccione un candidato por favor.", Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

    }

    //File Reader del SDCard
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


    //File Writer del SDCard
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
    //Verificador de permiso para guardar en la aplicacion
    private boolean isExternalStorageAviableforRW(){
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }
}
