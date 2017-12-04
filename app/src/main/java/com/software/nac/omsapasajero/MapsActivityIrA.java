package com.software.nac.omsapasajero;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.software.nac.omsapasajero.R.id.map;

public class MapsActivityIrA extends FragmentActivity  implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    LatLng destino;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    public ParadaCercana paradaCercana;
    Boolean paradaOrigen = false;
    Location mylocation;
    double destinoLa, destinoLo ,origenLaString, origenLoString;
    Button ir;
    private List<Polyline> polylines;
    ProgressDialog pDialog;
    private static final int[] COLORS = new int[]{Color.RED};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ir);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        polylines = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        buildGoogleApiClient();
        mapFragment.getMapAsync(this);


        ir=(Button)findViewById(R.id.btir);

        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (destino!=null)
                {
                   // Log.i("enviar informacion", String.valueOf(mLastLocation.getLatitude()));
                   // Log.i("enviar informacion", String.valueOf(mLastLocation.getLongitude()));
                  //  System.out.printf("informacion sobre mi ubicacion"+ mLastLocation.getLatitude());
                   // Log.i("enviar informacion", String.valueOf(destino.latitude));
                  //  Log.i("enviar informacion", String.valueOf(destino.longitude));
                   // Toast.makeText(getApplicationContext(),"Buscando Paradas...", Toast.LENGTH_SHORT).show();

                    pDialog = new ProgressDialog(MapsActivityIrA.this);
                    pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pDialog.setMessage("Buscando Paradas Cercanas...");
                    pDialog.setCancelable(true);
                    pDialog.setMax(100);
                    new ParadasMasCerca().execute(mLastLocation.getLatitude(),mLastLocation.getLongitude(),destino.latitude,destino.longitude);

                }else {

                    Toast.makeText(getApplicationContext(),"Selecciones el Destino", Toast.LENGTH_SHORT).show();
                }
                //new GetParadasMasCerca().execute(destino);
            }
        });
    }



    private class ParadasMasCerca extends AsyncTask<Double, Void, ParadaCercana> implements RoutingListener{

        @Override
        protected ParadaCercana doInBackground(Double... params) {

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                Double latOrigen = params[0];
                Double lngOrigen = params[1];
                Double latDestino = params[2];
                Double lngDestino = params[3];
                String cadena0 = Double.toString(latOrigen);
                String cadena1 = Double.toString(lngOrigen);
                String cadena2 = Double.toString(latDestino);
                String cadena3 = Double.toString(lngDestino);
            //    Log.i("cadenaa", cadena0+cadena1+cadena2+cadena3);
                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/paradas/mas/cerca/"+cadena0+"/"+cadena1+"/"+cadena2+"/"+cadena3+"/");


                HttpEntity<?> entity = new HttpEntity<>(headers);

                paradaCercana = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        ParadaCercana.class).getBody();
               // Log.i("datooooo",paradaCercana.getParadasLlegada().getNombre().toString());
                // System.out.println("estoy en autobus)"+autobus.toString());
                // Log.i("Autoooooooo1", distanceAndTime.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;


        }

    /*    @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }*/

      /*  @Override
        protected void onProgressUpdate(Void... values) {
            //super.onProgressUpdate(values);
            int progreso = 2;

            pDialog.setProgress(progreso);
        }*/

      /*  @Override
        protected void onPreExecute() {
            //super.onPreExecute();

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    MapsActivity.AutobusConDatos.this.cancel(true);
                }


            });

            pDialog.setProgress(0);
            pDialog.show();
        }*/


        @Override
        protected void onProgressUpdate(Void... values) {
            //super.onProgressUpdate(values);
            int progreso = 2;

            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            //super.onPreExecute();

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    MapsActivityIrA.ParadasMasCerca.this.cancel(true);
                }


            });

            pDialog.setProgress(0);
            pDialog.show();
        }



        public RestTemplate getRestTemplate() {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            return restTemplate;
        }

        @Override
        protected void onPostExecute(ParadaCercana inf) {
          /*  paradaCercana.getParadaSaida().getNombre();
            paradaCercana.getParadasLlegada().getNombre();
*/
            pDialog.dismiss();
          paradaOrigen=true;
            LatLng salidaO;
            LatLng latLng1 = null;
            LatLng latLng2 = null;
            try {

                Double latO = Double.valueOf(paradaCercana.getParadaSalida().getCoordenada().getLatitude());
                Double lonO = Double.valueOf(paradaCercana.getParadaSalida().getCoordenada().getLongitud());

            //Log.i("paradaaaaaaaaaaa", String.valueOf(latO));
            //Log.i("paradaaaaaaaaaaa", String.valueOf(lonO));
            latLng1 = new LatLng(latO, lonO);
            Marker salidam = mMap.addMarker(new MarkerOptions()
                    .title("Parada de Salida")
                    .snippet(paradaCercana.getParadaSalida().getNombre())
                    .position(latLng1)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.paradas)));

             mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {


                 @Override
                 public boolean onMarkerClick(Marker marker) {
                     if(marker.getTitle().equals("Parada de Salida")){
                      //  Toast.makeText(getApplicationContext(),"Parada de Salida",Toast.LENGTH_SHORT).show();

                     }
                     return false;
                 }
             });


            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

                Double latD = Double.parseDouble(paradaCercana.getParadaLLegada().getCoordenada().getLatitude());
                Double lonD = Double.parseDouble(paradaCercana.getParadaLLegada().getCoordenada().getLongitud());
               // Log.i("paradaaaaaaaaaaa", String.valueOf(latD));
               // Log.i("paradaaaaaaaaaaa", String.valueOf(lonD));
                 latLng2 = new LatLng(latD, lonD);
                mMap.addMarker(new MarkerOptions()
                        .title("Parada de Llegada")
                        .snippet(paradaCercana.getParadaLLegada().getNombre())
                        .position(latLng2)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.paradas)));




            } catch (Exception e) {
                e.printStackTrace();
            }


            ArrayList<LatLng> latLng = new ArrayList<>();
            try {
                int n = paradaCercana.getParadaSalida().getRuta().getCoordenadas().length;
                for (int i = 0; i < n; i++) {
                    Double lati = Double.parseDouble(paradaCercana.getParadaSalida().getRuta().getCoordenadas()[i].getLatitude());
                    Double lono = Double.parseDouble(paradaCercana.getParadaSalida().getRuta().getCoordenadas()[i].getLongitud());
//            Double lati1;
//            Double lono1;
//
//            if (i < n - 1) {
//                lati1 = Double.parseDouble(objectsRuta.getCoordenadas()[i + 1].getLatitude());
//                lono1 = Double.parseDouble(objectsRuta.getCoordenadas()[i + 1].getLongitud());
//            } else {
//                lati1 = Double.parseDouble(objectsRuta.getCoordenadas()[0].getLatitude());
//                lono1 = Double.parseDouble(objectsRuta.getCoordenadas()[0].getLongitud());
//            }

                    latLng.add(new LatLng(lati, lono));
                }

                Polyline line2 = mMap.addPolyline(new PolylineOptions()
                        .addAll(latLng).width(10));
                line2.setColor(Color.BLUE);

                //DA4444

            } catch (Exception e) {
                e.printStackTrace();
            }
            LatLng my =new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            getRuta(my,latLng1,latLng2,destino);
            //getRuta2(latLng2,destino);



            LatLng lt = new LatLng(19.4507303, -70.69428563);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(13));



        /*    Log.i("Resultatos de paradas ",paradaCercana.getParadaSalida().getNombre().toString());
            Log.i("Resultatos de paradas ",paradaCercana.getParadaLLegada().getNombre().toString());*/
        }

        private void getRuta2(LatLng latLng2, LatLng destino) {
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(false)
                    .waypoints(latLng2, destino)
                    .build();
            routing.execute();



        }

        private void getRuta(LatLng my, LatLng latLng1,LatLng latLng2,LatLng destino) {
            Routing routing = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(false)
                    .waypoints(my, latLng1)
                    .build();
            routing.execute();

            /*
            Routing routing2 = new Routing.Builder()
                    .travelMode(AbstractRouting.TravelMode.DRIVING)
                    .withListener(this)
                    .alternativeRoutes(false)
                    .waypoints(latLng2, destino)
                    .build();
            routing2.execute();*/
        }

        @Override
        public void onRoutingFailure(RouteException e) {
            if(e != null) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(), "Something went wrong, Try again", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onRoutingStart() {

        }

        @Override
        public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
            if(polylines.size()>0) {
                for (Polyline poly : polylines) {
                    poly.remove();
                }
            }

            polylines = new ArrayList<>();
            //add route(s) to the map.
            for (int i = 0; i <route.size(); i++) {

                //In case of more than 5 alternative routes
                int colorIndex = i % COLORS.length;

                PolylineOptions polyOptions = new PolylineOptions();
                polyOptions.color(Color.RED);
                polyOptions.width(10 + i * 3);
                polyOptions.addAll(route.get(i).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                polylines.add(polyline);

               // Toast.makeText(getApplicationContext(),"Route "+ (i+1) +": distance - "+ route.get(i).getDistanceValue()+": duration - "+ route.get(i).getDurationValue(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onRoutingCancelled() {

        }

      /*  @Override
        protected void onPostExecute(DistanceAndTime info) {
            super.onPostExecute(paradaCercana);
            //distancia = true;

          *//*  pDialog.dismiss();
            Intent nextScreen = new Intent(MapsActivity.this, Info.class);
            nextScreen.putExtra("distanceAndTime", distanceAndTime);
            startActivity(nextScreen);*//*

//            infText.setText(distanceAndTime.getAutobus().getActivo());

        }*/
/*
        @Override
        protected void onCancelled() {

        }*/

    }

    /*private class GetParadasMasCerca extends AsyncTask<LatLng, Void, APIParadas> {
        @Override
        protected Coordenadas doInBackground(LatLng... params) {

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/autobuses/buscar/ruta/" );

                HttpEntity<?> entity = new HttpEntity<>(headers);

                listAutobus = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        Autobus[].class).getBody();

                System.out.println("estoy en autobus)"+listAutobus[0].toString());
                Log.i("Autoooooooo", listAutobus.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public RestTemplate getRestTemplate() {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            return restTemplate;
        }


        @Override
        protected void onPostExecute(Autobus info) {
            postExecuteForAutobus = true;
            Log.i("Autoooooooo", String.valueOf(postExecuteForAutobus));
            if (mapReady) {

                try {
                    int q = listAutobus.length;
                    for (int j = 0; j < q; j++) {
                        lonAutobus = Double.parseDouble(listAutobus[j].getCoordenada().getLongitud());
                        latAutobus = Double.parseDouble(listAutobus[j].getCoordenada().getLatitude());
                        LatLng latLng1 = new LatLng(latAutobus, lonAutobus);
                        mAutobus = mMap.addMarker(new MarkerOptions()
                                //.title("Autobus")
                                // .snippet("...")
                                .position(latLng1)
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.logobus)));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }
*/
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lt = new LatLng(19.4507303, -70.69428563);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(lt)      // Sets the center of the map to Mountain View
                .zoom(14)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(60)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);




        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                if(point!=null){
                   // change=false;
                    //clear map
                    mMap.clear();
                    destino = new LatLng(point.latitude, point.longitude);
                   // System.out.printf("aaaaaaaaaaaa"+destino);
                    //System.out.printf("aaaaaaaaaaaa"+destino.latitude);
                    //System.out.printf("aaaaaaaaaaaa");

                    MarkerOptions marker = new MarkerOptions()
                        .position(destino)
                        .draggable(true)
                        .title("Destino");
                    mMap.addMarker(marker);

                    //Log.i("qqqqqqqqqq", String.valueOf(destino.latitude));
                    //System.out.printf("aaaaaaaaaaaa");
                  //  destinoLaString = String.valueOf(point.latitude);
                    //destinoLoString = String.valueOf(point.longitude);



                // System.out.println(destinoLa + "-aaaaa-" + destinoLo);

                }
            }
        });




    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(2000);
        mLocationRequest.setFastestInterval(2000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        if (getApplicationContext()!=null){

            mLastLocation=location;
        }


    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    private void borrarPolyline(){
        for (Polyline lines:polylines){
            lines.remove();
        }
        polylines.clear();
    }
}
