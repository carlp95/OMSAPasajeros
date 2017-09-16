package com.software.nac.omsapasajero;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neury on 8/29/2017.
 */

public class Adaptador extends BaseAdapter {


    private Activity activity;
    private ArrayList<Ruta> data;
    private LayoutInflater inflater;


    public Adaptador(Activity activity, List<Ruta> list) {
        this.activity = activity;
        this.data = new ArrayList<>(list);

        for (int i = 0; i < data.size(); i++) {
            if (!data.get(i).getEsDireccionSubida().equals("true")) {
                data.remove(i);
                i--;
            }
        }

        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null)
            view = inflater.inflate(R.layout.list_corredores, null); // no esta en el layout

        int backgroundColor, textColor;
        int backgroundColorID = position % 2 != 0 ? R.color.colorBackground1 : R.color.colorBackground2;
        int textColorID = position % 2 != 0 ? R.color.colorText1 : R.color.colorText2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backgroundColor = activity.getResources().getColor(backgroundColorID, null);
            textColor = activity.getResources().getColor(textColorID, null);
        } else {
            backgroundColor = activity.getResources().getColor(backgroundColorID);
            textColor = activity.getResources().getColor(textColorID);
        }

        TextView nombreCorredor = (TextView) view.findViewById(R.id.idCorredor);
        TextView ciudadCorredor = (TextView) view.findViewById(R.id.idCiudad);

        nombreCorredor.setText(data.get(position).getNombreCorredor());
        nombreCorredor.setTextColor(textColor);

        ciudadCorredor.setText(data.get(position).getCiudad());
        ciudadCorredor.setTextColor(textColor);



        view.findViewById(R.id.backgroundLayout).setBackgroundColor(backgroundColor);
        view.findViewById(R.id.backgroundLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).setIdCorredor(data.get(position).getId());
                Toast.makeText(activity, String.format("#%s %s, %s", data.get(position).getId(), data.get(position).getNombreCorredor(), data.get(position).getCiudad()), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
