package com.example.aviones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> user = new ArrayList<>();
    ArrayList<String> pswd = new ArrayList<>();
    boolean cadenduser = false;
    boolean cadendpswd = false;
    boolean usercheck = false;
    boolean pswdcheck = false;
    int LAUNCH_SECOND_ACTIVITY = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent registro = getIntent();
        if(registro.hasExtra("user") && registro.hasExtra("pswd")) {
            Toast.makeText(MainActivity.this, R.string.regist, Toast.LENGTH_SHORT).show();
        }

    }
    public void onLogin(View view){
        EditText Nome = findViewById(R.id.nom);
        EditText Contraseña = findViewById(R.id.contr);

        user.add("root");
        pswd.add("root");
        Intent registro = getIntent();
        if(registro.hasExtra("user") && registro.hasExtra("pswd")){
            user.add(registro.getStringExtra("user"));
            pswd.add(registro.getStringExtra("pswd"));

        }


        while((usercheck == false) && (cadenduser == false)){
            for(int a = 0;a<user.size();a++) {
                if (user.get(a).equals(Nome.getText().toString())) {
                    usercheck = true;
                }
                if (usercheck == false) cadenduser = true;
            }
        }
        while((pswdcheck == false) && (cadendpswd == false)) {
            for (int a = 0; a < pswd.size(); a++) {
                if (pswd.get(a).equals(Contraseña.getText().toString())) {
                    pswdcheck = true;
                }
                if (pswdcheck == false) cadendpswd = true;
            }
        }

        if((usercheck == true) && (pswdcheck == true)){
            Intent intent = new Intent(this, interfazinternaclass.class);
            startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
        }
        else Toast.makeText(MainActivity.this, R.string.errorlog, Toast.LENGTH_SHORT).show();

    }
    public void onRegister(View view){

        EditText Nome = (EditText) findViewById(R.id.nom);
        EditText Contraseña = (EditText) findViewById(R.id.contr);
        String user = Nome.getText().toString();
        String pswd = Contraseña.getText().toString();

        Intent registro = new Intent(this, MainActivity.class);
        registro.putExtra("user", user);
        registro.putExtra("pswd", pswd);
        startActivity(registro);

        //Toast.makeText(MainActivity.this, R.string.regist, Toast.LENGTH_SHORT).show();
    }

}
