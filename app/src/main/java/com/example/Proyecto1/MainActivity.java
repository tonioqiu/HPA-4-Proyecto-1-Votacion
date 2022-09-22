package com.example.Proyecto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Antes de empezar la aplicacion, se debe copiar el archivo votos.txt al directorio /sdcard/Android/data/com.example.proyecto1/files/votos

public class MainActivity extends AppCompatActivity {
    EditText idEstudiante;
    Button Login;
    TextView test;
    String filename = "";
    String filepath = "";
    int validez = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEstudiante = findViewById(R.id.txtIdentificacion);
        Login = findViewById(R.id.btnLogin);
        test = findViewById(R.id.textView9);
        filename = "votos.txt";
        filepath = "votos";


        //Click del boton Login
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userinput = idEstudiante.getText().toString();
                String[] userArray = new String[40];
                String[] votoArray = new String[40];


                //Leer el archivo de user.txt en assets y leer el archivo de votos del SDCard
                userArray = readFileToArray("users.txt");
                votoArray = readFileVoteToArray(filename, filepath);
                //Validar que el camnpo de texto tenga algo escrito
                if(userinput.equals("")){
                    Toast.makeText(getApplicationContext(),"Ingrese un usuario", Toast.LENGTH_SHORT).show();
                }
                //Busqueda de usuario ingresado en el arreglo leido de usuarios
                for (int i = 0; i < userArray.length; i++) {
                    //Si el usuario es valido y no ha votado
                    if (userinput.equals(userArray[i]) && votoArray[i].equals("0")){
                        //file read test
                        //test.setText("valido");
                        validez = 1;
                        Intent intent = new Intent(getApplicationContext(), Votacion.class);
                        intent.putExtra("arrayNum", i );
                        startActivity(intent);
                    }
                    //si el usuario es valido y ya voto
                    else if (userinput.equals(userArray[i])){
                        //file read test
                        //test.setText("valido");
                        validez = 1;
                        Intent intent = new Intent(getApplicationContext(), Resultados.class);
                        startActivity(intent);
                    }


                }
                //Si el usuario es invalido
                if (validez != 1){
                Toast.makeText(getApplicationContext(),"Usuario invalido", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    //File Reader del folder de assets
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
    }

    //file reader del SDCard
    public String[] readFileVoteToArray(String filename,String filepath){
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
    }
}


