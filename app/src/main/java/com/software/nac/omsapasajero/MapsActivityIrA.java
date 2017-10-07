package com.software.nac.omsapasajero;

import android.app.Activity;
import android.content.pm.PackageManager;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MapsActivityIrA extends FragmentActivity  implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    LatLng destino;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Location mylocation;
    double destinoLa, destinoLo ,origenLaString, origenLoString;
    Button ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_ir);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        buildGoogleApiClient();
        mapFragment.getMapAsync(this);

        ir=(Button)findViewById(R.id.btir);

        ir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (destino!=null)
                {
                    Log.i("enviar informacion", String.valueOf(mLastLocation.getLatitude()));
                    System.out.printf("informacion sobre mi ubicacion"+ mLastLocation.getLatitude());
                    Log.i("enviar informacion", String.valueOf(destino.latitude));
                    Toast.makeText(getApplicationContext(),"Siii", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(getApplicationContext(),"Selecciones el Destino", Toast.LENGTH_SHORT).show();
                }
                //new GetParadasMasCerca().execute(destino);
            }
        });
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
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

                    Log.i("qqqqqqqqqq", String.valueOf(destino.latitude));
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
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
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
}
