package com.example.aviones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class listaclasshist extends AppCompatActivity {
    ArrayList <String> Orig;
    ArrayList <String> Destiny;
    ArrayList <String> Dateg;
    ArrayList <String> Datec;
    int[] images = {R.drawable.iconoavion};
    private AlertDialog.Builder builder;

    ListView lView;
    ListAdapterHist lAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Orig = new ArrayList<String>();
        Destiny = new ArrayList<String>();
        Dateg = new ArrayList<String>();
        Datec = new ArrayList<String>();
        Intent Hist = getIntent();

        if(Hist.hasExtra("Origen")){
            Orig = getIntent().getStringArrayListExtra("Origen");;
        }
        if(Hist.hasExtra("Destiny")){
            Destiny = getIntent().getStringArrayListExtra("Destiny");
        }
        if(Hist.hasExtra("Salida")){
            Dateg = getIntent().getStringArrayListExtra("Salida");
        }
        if(Hist.hasExtra("Vuelta") && Hist.getStringArrayListExtra("Vuelta").size() != 0){
            Datec = getIntent().getStringArrayListExtra("Vuelta");
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.histflys);
        lView = (ListView) findViewById(R.id.listahist);

        lAdapter = new ListAdapterHist(listaclasshist.this, Orig, Destiny, Dateg, Datec, images);
        lView.setAdapter(lAdapter);

        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                builder = new AlertDialog.Builder(listaclasshist.this);
                LinearLayout layout = new LinearLayout(listaclasshist.this);
                TextView Origen = new TextView(listaclasshist.this);
                TextView Destino =  new TextView(listaclasshist.this);
                TextView Salida = new TextView(listaclasshist.this);
                TextView Vuelta = new TextView(listaclasshist.this);




                builder.setTitle("VUELO RESERVADO");
                builder.setMessage("VUELO:");

                Origen.setText("ORIGEN:  " + Orig.get(i));
                Origen.setTextSize(20);

                Destino.setText("DESTINO:  " + Destiny.get(i));
                Destino.setTextSize(20);

                Salida.setText("SALIDA:  " + Dateg.get(i));
                Salida.setTextSize(20);

                if(Hist.getStringArrayListExtra("Vuelta") !=null){
                    Vuelta.setText("VUELTA:  " + Datec.get(i));
                    Vuelta.setTextSize(20);
                }
                else Vuelta.setText("");


                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.CENTER);
                layout.setPadding(30,0,30,0);
                layout.addView(Origen);
                layout.addView(Destino);
                layout.addView(Salida);
                layout.addView(Vuelta);
                builder.setView(layout);
                builder.setNegativeButton("Atras", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                
                AlertDialog dialogo = builder.create();
                dialogo.show();


                Intent Search = new Intent();

                setResult(interfazinternaclass.RESULT_OK, Search);


            }
        });
    }
}