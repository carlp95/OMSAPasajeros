package com.software.nac.omsapasajero;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.software.nac.omsapasajero.MapsActivity.distancia;

public class Info extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent i = getIntent();
        DistanceAndTime distanceAndTime = (DistanceAndTime)i.getSerializableExtra("distanceAndTime");

        double duracionTrafico, duracion, tiempoAproximado;
        int resultadoTimepoAproximado, resultadoDuracion, resultadoDuracionTrafico,resultadoCantidadPasajeros;
        String resultadoTieneAire = "Si";
        ImageView img= (ImageView) findViewById(R.id.imagenbarra);

        try {
            TextView informacion = (TextView) findViewById(R.id.textView1);
            TextView tiempoAproximadoView = (TextView) findViewById(R.id.textViewTiempoAproximado);
            TextView tiempoSinTrafico = (TextView) findViewById(R.id.textViewDurationSinR);
            TextView tiempoConTrafico = (TextView) findViewById(R.id.textViewDurationConTR);
            TextView conductor = (TextView) findViewById(R.id.textViewConductorR);
            TextView precio = (TextView) findViewById(R.id.textViewPrecioR);
            TextView tieneAire = (TextView) findViewById(R.id.textViewAireacondicionadoR);
           // TextView cantidadPasajeros = (TextView) findViewById(R.id.textViewcantidadDePasajerosActualR);

            if (distanceAndTime.getAutobus() != null)
            {

                informacion.setText("Información  sobre el Autobús ");
                informacion.setTextColor(getResources().getColor(R.color.colorPrimary));
                duracion = Double.parseDouble(distanceAndTime.getDuration());
                duracionTrafico = Double.parseDouble(distanceAndTime.getDuration_Traffic());

                tiempoAproximado = (duracion+duracionTrafico)/2;

                resultadoTimepoAproximado = round(tiempoAproximado);
                resultadoDuracion = round(duracion);
                resultadoDuracionTrafico =round(duracionTrafico);

                resultadoCantidadPasajeros = Integer.parseInt(distanceAndTime.getAutobus().getCantidadDePasajerosActual());


                //String duracionT =distanceAndTime.getDuration_Traffic();
                //System.out.printf("Duracion en t ->"+duracionT);

                tiempoAproximadoView.setText(resultadoTimepoAproximado+" Minutos");
                tiempoSinTrafico.setText(resultadoDuracion +" Minutos");
                tiempoConTrafico.setText(resultadoDuracionTrafico+" Minutos");
                conductor.setText(distanceAndTime.getAutobus().getConductor());
                precio.setText(distanceAndTime.getAutobus().getPrecio()+ " RD$");
                if (distanceAndTime.getAutobus().getTieneAireAcondicionado().equals("true")){
                    tieneAire.setText(resultadoTieneAire);
                }else{
                    resultadoTieneAire ="No";
                    tieneAire.setText(resultadoTieneAire);
                }

                if (resultadoCantidadPasajeros<=9)
                {
                    img.setImageResource(R.drawable.omsa1);
                }else if (resultadoCantidadPasajeros>9 && resultadoCantidadPasajeros <=18){
                    img.setImageResource(R.drawable.omsa2);
                }else if (resultadoCantidadPasajeros>18 && resultadoCantidadPasajeros <=27){
                    img.setImageResource(R.drawable.omsa3);
                }else if (resultadoCantidadPasajeros>27 && resultadoCantidadPasajeros <=36){
                    img.setImageResource(R.drawable.omsa4);
                }else if (resultadoCantidadPasajeros>36 && resultadoCantidadPasajeros <=45){
                    img.setImageResource(R.drawable.omsa5);
                }else if (resultadoCantidadPasajeros>45 && resultadoCantidadPasajeros <=54){
                    img.setImageResource(R.drawable.omsa6);
                }else if (resultadoCantidadPasajeros>54 && resultadoCantidadPasajeros <=63){
                    img.setImageResource(R.drawable.omsa7);
                }else if (resultadoCantidadPasajeros>63 && resultadoCantidadPasajeros <=72){
                    img.setImageResource(R.drawable.omsa8);
                }else if (resultadoCantidadPasajeros>72 && resultadoCantidadPasajeros <=81){
                    img.setImageResource(R.drawable.omsa9);
                }else if (resultadoCantidadPasajeros>81){
                    img.setImageResource(R.drawable.omsa10);
                }
                //  cantidadPasajeros.setText(distanceAndTime.getAutobus().getCantidadDePasajerosActual());


            }else {
                informacion.setText("No hay Autobús Disponible en esta Ruta");
                informacion.setTextColor(getResources().getColor(R.color.alerta));
            }

   /*         tiempoAproximadoView.setText(" ");
            tiempoSinTrafico.setText(" ");
            tiempoConTrafico.setText(" ");
            conductor.setText(" ");
            precio.setText(" ");
            tieneAire.setText(" ");
            img.setImageResource(R.drawable.omsabarra);*/



        }catch (Exception e){
            e.printStackTrace();
        }




    }

    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5){
            return d<0 ? -i : i;
        }else{
            return d<0 ? -(i+1) : i+1;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
