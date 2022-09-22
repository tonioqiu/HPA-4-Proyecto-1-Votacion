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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText idEstudiante;
    Button Login;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idEstudiante = findViewById(R.id.txtIdentificacion);
        Login = findViewById(R.id.btnLogin);
        test = findViewById(R.id.textView9);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userinput = idEstudiante.getText().toString();
                String[] userArray = new String[40];
                String[] votoArray = new String[40];

                //File reader
                userArray = readFileToArray("users.txt");
                votoArray = readFileToArray("votos.txt");

                for (int i = 0; i < userArray.length; i++) {
                    if (userinput.equals(userArray[i]) && votoArray[i].equals("0")){
                        //file read test
                        test.setText("valido");
                        Intent intent = new Intent(getApplicationContext(), Votacion.class);
                        intent.putExtra("arrayNum", i );
                        startActivity(intent);

                    }
                    else if (userinput.equals(userArray[i])){
                        //file read test
                        test.setText("valido");
                        Intent intent = new Intent(getApplicationContext(), Resultados.class);
                        startActivity(intent);

                    }
                    else if (i==39){
                        Toast.makeText(getApplicationContext(),"Usuario invalido", Toast.LENGTH_SHORT).show();
                    }



                }

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