package com.example.aviones;

import static com.example.aviones.R.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class interfazinternaclass extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    int tipo;
    private int mCount = 0;
    private TextView mShowCount;
    boolean history = false;
    int LAUNCH_SECOND_ACTIVITY = 1;

    ArrayList<String> Orig = new ArrayList<String>();
    ArrayList <String> Destiny = new ArrayList<String>();
    ArrayList <String> Dateg = new ArrayList<String>();
    ArrayList <String> Datec = new ArrayList<String>();




    private void escribir(String texto) {
        mShowCount.setText(texto);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(layout.interfazinterna);
        mShowCount = (TextView) findViewById(id.editText3);
        Spinner from = findViewById(id.Spinner);
        Spinner to = findViewById(id.Spinner2);
        TextView pasajeros = findViewById(id.editText3);
        RadioGroup Paradas = (RadioGroup) findViewById(id.radioGroup2);
        TextView vuelta = findViewById(id.editText5);
        TextView retur = findViewById(id.textView4);
        ImageView cale = findViewById(id.imageView2);
        vuelta.setVisibility(View.INVISIBLE);
        retur.setVisibility(View.INVISIBLE);
        cale.setVisibility(View.INVISIBLE);

        final Button sumar = findViewById(id.button5);
        sumar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(mCount < 19){
                    mCount++;
                    escribir(new Integer(mCount).toString());
                }
                else{
                    Toast.makeText(interfazinternaclass.this, string.passmax, Toast.LENGTH_SHORT).show();
                }

            }
        });
        final Button restar = findViewById(id.button4);
        restar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(mCount > 0){
                    mCount--;
                    escribir(new Integer(mCount).toString());
                }
                else{
                    Toast.makeText(interfazinternaclass.this, string.passmin, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //INICIALIZACIÓN DEL SPINNER DE AEROPUERTO DE SALIDA
        Spinner spinner = (Spinner) findViewById(id.Spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, array.salida, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = (Spinner) findViewById(id.Spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, array.destino, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

    }

    //INICIALIZACIÓN DEL SELECTOR DE FECHA PARA HACERLO VISIBLE EN CASO DE IDA Y VUELTA
    public void onRoundTrip(View view){

        TextView vuelta = findViewById(id.editText5);
        TextView retur = findViewById(id.textView4);
        ImageView cale = findViewById(id.imageView2);
        RadioGroup Tipo = (RadioGroup) findViewById(id.radioGroup);



        if(Tipo.getCheckedRadioButtonId() == id.button) {

            vuelta.setVisibility(View.VISIBLE);
            retur.setVisibility(View.VISIBLE);
            cale.setVisibility(View.VISIBLE);

        }
    }
    //INICIALIZACION DEL SELECTOR DE FECHA EN CASO DE QUE SE SELECCIONE SOLO IDA
    public void onOneWay(View view){

    TextView vuelta = findViewById(id.editText5);
    TextView retur = findViewById(id.textView4);
    ImageView cale = findViewById(id.imageView2);
    RadioGroup Tipo = (RadioGroup) findViewById(id.radioGroup);

    if(Tipo.getCheckedRadioButtonId() == id.button3) {


        vuelta.setVisibility(View.INVISIBLE);
        retur.setVisibility(View.INVISIBLE);
        cale.setVisibility(View.INVISIBLE);
    }

    }
    public void onLocateOrigin(View view){
        Spinner from = findViewById(id.Spinner);
        if(from.getSelectedItem().toString().equals("Santiago")){
            Uri loc = Uri.parse("geo:42.896714679154435, -8.416308483682513");
            Intent locate = new Intent(Intent.ACTION_VIEW, loc);
            startActivity(locate);
        }
        if(from.getSelectedItem().toString().equals("Coruña")){
            Uri loc = Uri.parse("geo:43.30269945727474, -8.381403566478392");
            Intent locate = new Intent(Intent.ACTION_VIEW, loc);
            startActivity(locate);
        }
    }
    //INICIALIZACIÓN DEL SELECTOR DE FECHA DE IDA
    public void onFromDate(View view) {
        DialogFragment FromDatePiker = new DatePickerFragment();
        FromDatePiker.show(getSupportFragmentManager(), (String) getText(string.fecha));
        tipo = 0;

    }
    //INICIALIZACIÓN DEL SELECTOR DE FECHA DE VUELTA
    public void onToDate(View view) {
        DialogFragment ToDatePiker = new DatePickerFragment();

        ToDatePiker.show(getSupportFragmentManager(), (String) getText(string.fecha));
        tipo = 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String Date = DateFormat.getDateInstance().format(c.getTime());

            if (tipo == 0){
                TextView desde = (TextView) findViewById(id.editText4);
                desde.setText(Date);
            }
            if (tipo == 1){
                TextView hasta = (TextView) findViewById(id.editText5);
                hasta.setText(Date);

            }

    }




    //INICIALIZACIÓN DEL MENÚ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    //GESTION DE HISTORIAL
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case id.historial:

                RadioGroup Tipo = (RadioGroup) findViewById(id.radioGroup);
                Intent hist = new Intent(this, listaclasshist.class);
                hist.putStringArrayListExtra("Destiny", Destiny);
                hist.putStringArrayListExtra("Origen", Orig);
                hist.putStringArrayListExtra("Salida", Dateg);
                if(Tipo.getCheckedRadioButtonId() == id.button){
                    hist.putStringArrayListExtra("Vuelta", Datec);
                }



                //Toast.makeText(MainActivity2.this, "Not able to do this action", Toast.LENGTH_SHORT).show();
                startActivity(hist);

            case id.cerrases:
                Intent Logout = new Intent();
                setResult(interfazinternaclass.RESULT_OK,Logout);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //METODO ONSEARCHA PARA EL BOTÓN SEARCH
    public void onSearch(View view){

        TextView desde = findViewById(id.editText4);
        TextView hasta = findViewById(id.editText5);
        Spinner from = findViewById(id.Spinner);
        Spinner to = findViewById(id.Spinner2);
        RadioGroup Tipo = (RadioGroup) findViewById(id.radioGroup);




        Intent search = new Intent(this, listaclasssearch.class);
        search.putExtra("Destiny", to.getSelectedItem().toString());
        search.putExtra("Origen", from.getSelectedItem().toString());
        search.putExtra("Salida", desde.getText().toString());
        if(Tipo.getCheckedRadioButtonId() == id.button){
            search.putExtra("Vuelta", hasta.getText().toString());
        }


        //Toast.makeText(MainActivity2.this, "Not able to do this action", Toast.LENGTH_SHORT).show();
        startActivityForResult(search, LAUNCH_SECOND_ACTIVITY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView desde = findViewById(id.editText4);
        TextView hasta = findViewById(id.editText5);
        Spinner from = findViewById(id.Spinner);
        Spinner to = findViewById(id.Spinner2);
        RadioGroup Tipo = (RadioGroup) findViewById(id.radioGroup);

        if(resultCode == Activity.RESULT_OK){
            Destiny.add(to.getSelectedItem().toString());
            Orig.add(from.getSelectedItem().toString());
            Dateg.add(desde.getText().toString());
            if(Tipo.getCheckedRadioButtonId() == id.button){
                Datec.add(hasta.getText().toString());
            }
        }
        if (resultCode == Activity.RESULT_CANCELED) {

        }
        }
}

