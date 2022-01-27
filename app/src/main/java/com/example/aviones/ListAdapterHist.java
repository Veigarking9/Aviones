package com.example.aviones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterHist extends BaseAdapter {
    Context context;
    private ArrayList<String> Destino;
    private ArrayList<String> Origen;
    private ArrayList<String> Salida;
    private ArrayList<String> Vuelta;
    private int[] images;

    public ListAdapterHist(Context context,ArrayList<String> Origen, ArrayList<String> Destino, ArrayList<String> Salida, ArrayList<String> Vuelta, int[] images) {
        this.context = context;
        this.Destino = Destino;
        this.Origen = Origen;
        this.Salida = Salida;
        this.Vuelta = Vuelta;
        this.images = images;

    }

    @Override
    public int getCount() {
        return Destino.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.histfila, parent, false);

            viewHolder.destinohist = (TextView)
                    convertView.findViewById(R.id.destinoshist);

            viewHolder.fechahist = (TextView)
                    convertView.findViewById(R.id.fechashist);

            viewHolder.iconhist = (ImageView)
                    convertView.findViewById(R.id.iconhist);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.destinohist.setText(Origen.get(position)+" - "+Destino.get(position));
        if(Vuelta.size() != 0  && Salida.size() != 0 ){

            viewHolder.fechahist.setText(Salida.get(position)+" <-||-> "+Vuelta.get(position));
        }
        if(Vuelta.size() != 0 && Salida.size() == 0){
            viewHolder.fechahist.setText(Vuelta.get(position));
        }
        if(Salida.size() != 0 && Vuelta.size() == 0){
            viewHolder.fechahist.setText(Salida.get(position));
        }
        if(Vuelta.size() == 0  && Salida.size() == 0 ){
            viewHolder.fechahist.setText("");
        }


        viewHolder.iconhist.setImageResource(R.drawable.iconoavion);
        return convertView;
    }

    private static class ViewHolder {
        TextView destinohist;
        TextView fechahist;
        ImageView iconhist;
    }
}