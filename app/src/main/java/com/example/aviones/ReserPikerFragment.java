package com.example.aviones;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ReserPikerFragment extends DialogFragment {
    Intent Search;

    public ReserPikerFragment() {
        Search = new Intent();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.reserv);


        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                getActivity().setResult(interfazinternaclass.RESULT_OK,Search);
                getActivity().finish();


            }

        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                getActivity().setResult(interfazinternaclass.RESULT_CANCELED,Search);
                getActivity().finish();







            }

        });


        return builder.create();

    }


}
