package com.example.aviones;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListAdapterSearch extends BaseAdapter {
    Context context;
    private final String[] Destino;
    private final String[] Origen;
    private final String[] Salida;
    private final String[] Vuelta;
    private final int[] images;

    public ListAdapterSearch(Context context,String[] Origen, String[] Destino, String[] Salida, String[]  Vuelta, int[] images) {
        this.context = context;
        this.Destino = Destino;
        this.Origen = Origen;
        this.Salida = Salida;
        this.Vuelta = Vuelta;
        this.images = images;

    }

    @Override
    public int getCount() {
        return Destino.length;
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
        ListAdapterSearch.ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.seachfila, parent, false);

            viewHolder.destinossearch = (TextView)
                    convertView.findViewById(R.id.destinossearch);

            viewHolder.fechasearch = (TextView)
                    convertView.findViewById(R.id.fechasearch);

            viewHolder.iconsearch = (ImageView)
                    convertView.findViewById(R.id.iconsearch);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ListAdapterSearch.ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.destinossearch.setText(Origen[position]+"-"+Destino[position]);
        if(Vuelta[position] != null){
            viewHolder.fechasearch.setText(Salida[position]+" <-||-> "+Vuelta[position]);
        }
        else viewHolder.fechasearch.setText(Salida[position]);

        viewHolder.iconsearch.setImageResource(images[position]);
        return convertView;
    }

    private static class ViewHolder {
        TextView destinossearch;
        TextView fechasearch;
        ImageView iconsearch;
    }
}