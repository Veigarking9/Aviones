package com.example.aviones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class listaclasssearch extends AppCompatActivity {
    private String[] Origen;
    private String[] Destiny;
    private String[] Dateg;
    private String[] Datec;

    int[] images = {R.drawable.iconoavion};

    ListView lView;
    ListAdapterSearch lAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent Search = getIntent();
        Origen = new String[1];
        Destiny = new String[1];
        Dateg = new String[1];
        Datec = new String[1];

        if(Search.hasExtra("Origen")){
            Origen[0] = Search.getStringExtra("Origen");
        }
        if(Search.hasExtra("Destiny")){
            Destiny[0] = Search.getStringExtra("Destiny");
        }
        if(Search.hasExtra("Salida")){
            Dateg[0] = Search.getStringExtra("Salida");
        }
        if(Search.hasExtra("Vuelta")){
            Datec[0] = Search.getStringExtra("Vuelta");



        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serchflys);
        lView = (ListView) findViewById(R.id.listasearch);
        lAdapter = new ListAdapterSearch(listaclasssearch.this, Origen, Destiny, Dateg, Datec, images);
        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                ReserPikerFragment reserva = new ReserPikerFragment();
                reserva.show(fragmentManager, "AlertDialog");

                            }

        });
    }
}