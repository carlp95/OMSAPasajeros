package com.software.nac.omsapasajero;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

import static com.software.nac.omsapasajero.MainActivity.objects;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    public static Ruta objectsRuta;
    APIParadas apiParadas = new APIParadas();


    @Override
    protected void onStart() {
        super.onStart();
        new MapsActivity.HttpRequestTask().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Ruta> {
        @Override
        protected Ruta doInBackground(Void... params) {
            //==================================Para la Ruta==========================

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/ruta/1");

                HttpEntity<?> entity = new HttpEntity<>(headers);

                objectsRuta = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        Ruta.class).getBody();

                System.out.println("Rutasss = " + objectsRuta);


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
        protected void onPostExecute(Ruta info) {
            //  System.out.printf(info.getId());

            //infoIdText.setText(info.getId());
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        System.out.printf("=====Create===");
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    private ArrayList<LatLng> latlngs = new ArrayList<>();

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//=============================Para central el mapa con coordenadas en el monumento==========================
        LatLng lt = new LatLng(19.4507303, -70.69428563);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lt));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
//====================================================================================================






        /*latlngs.add(new LatLng(12.334343, 33.43434));

        LatLng marker = new LatLng(19.43469, -70.6912);
*/
        // System.out.printf("1==============Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa===================================");

        /*googleMap.addMarker(new MarkerOptions()
                .title("Parada OMSA").position(marker)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.paradas)));*/


        // Add a marker in Sydney and move the camera
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

        buildGoogleApiClient();
        mMap.setMyLocationEnabled(true);
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
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
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

        System.out.printf("====================Siiiiiiiii=============================");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        Double lon = Double.parseDouble(objects[0].getCoordenada().getLongitud());
        Double lat = Double.parseDouble(objects[0].getCoordenada().getLatitude());
        LatLng latLng1 = new LatLng(lat, lon);

        ArrayList<LatLng> latLng = new ArrayList<>();

        int n = objectsRuta.getCoordenadas().length;
        for (int i = 0; i < n; i++) {
            Double lati = Double.parseDouble(objectsRuta.getCoordenadas()[i].getLatitude());
            Double lono = Double.parseDouble(objectsRuta.getCoordenadas()[i].getLongitud());
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
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .addAll(latLng).width(10).color(Color.rgb(076, 145, 065)));
        //System.out.printf(lat + lon +"datos");


        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

    }


    // private MarkerOptions options = new MarkerOptions();
    // private ArrayList<LatLng> latlngs = new ArrayList<>();


/*
    @Override
    public void onMapReady(GoogleMap googleMap) {

        latlngs.add(new LatLng(12.334343, 33.43434));

        LatLng marker = new LatLng(19.43469, -70.6912);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 17));
        googleMap.addMarker(new MarkerOptions()
                .title("Parada OMSA").position(marker)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.paradas)));

     *//*   googleMap.addPolyline((new PolylineOptions())
                .add(MELBOURNE, ADELAIDE, PERTH));

        mClickablePolyline = map.addPolyline((new PolylineOptions())
                .add(LHR, AKL, LAX, JFK, LHR)
                .width(5)
                .color(Color.BLUE)
                .geodesic(true)
                .clickable(mClickabilityCheckbox.isChecked()));

        // Rectangle centered at Sydney.  This polyline will be mutable.
        int radius = 5;
        PolylineOptions options = new PolylineOptions()
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius));

        // Move the map so that it is centered on the mutable polyline.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));*//*
// Add a thin red line from London to New York.
        Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(19.48827848, -70.71671963),
                        new LatLng(19.48763117, -70.7144022),
                        new LatLng(19.48263467, -70.70815265),
                        new LatLng(19.47703113, -70.69886684),
                        new LatLng(19.47478561, -70.69486499),
                        new LatLng(19.47148808, -70.69198966),
                        new LatLng(19.47100254, -70.69130301),
                        new LatLng(19.46970779, -70.68789124),
                        new LatLng(19.46914133, -70.68726897),
                        new LatLng(19.46072512, -70.68602443),
                        new LatLng(19.45645615, -70.68546653),
                        new LatLng(19.45366407, -70.68495154),
                        new LatLng(19.45218707, -70.68516612),
                        new LatLng(19.44777625, -70.68817019),
                        new LatLng(19.44684551, -70.68844914),
                        new LatLng(19.44387115, -70.68722606),
                        new LatLng(19.4414633, -70.68714023),
                        new LatLng(19.44077534, -70.68731189),
                        new LatLng(19.41610787, -70.70278287),
                        new LatLng(19.4155817, -70.70394158),
                        new LatLng(19.41448887, -70.71085095),
                        new LatLng(19.41351747, -70.72767377),
                        new LatLng(19.41398294, -70.72891831),
                        new LatLng(19.42074216, -70.73591352),
                        new LatLng(19.42810818, -70.72795272),
                        new LatLng(19.43053647, -70.72602153),
                        new LatLng(19.43268142, -70.72499156),
                        new LatLng(19.44101815, -70.72207332),
                        new LatLng(19.4422929, -70.72226644),
                        new LatLng(19.44488285, -70.72406888),
                        new LatLng(19.4456922, -70.72421908),
                        new LatLng(19.44830232, -70.72372556),
                        new LatLng(19.45068984, -70.7241118),
                        new LatLng(19.45164079, -70.72434783),
                        new LatLng(19.45475663, -70.72449803),
                        new LatLng(19.45524221, -70.72144568),
                        new LatLng(19.45518151, -70.72078049),
                        new LatLng(19.45423564, -70.71545631),
                        new LatLng(19.45407378, -70.71541876),
                        new LatLng(19.45329482, -70.71546704),
                        new LatLng(19.45321389, -70.71533829),
                        new LatLng(19.45601104, -70.71514517),
                        new LatLng(19.45688609, -70.71499497),
                        new LatLng(19.45841868, -70.71481794),
                        new LatLng(19.45918749, -70.714598),
                        new LatLng(19.4630821, -70.71291894),
                        new LatLng(19.46372445, -70.71277946),
                        new LatLng(19.46434657, -70.71285993),
                        new LatLng(19.46518617, -70.71311206),
                        new LatLng(19.46782632, -70.71404278),
                        new LatLng(19.4685951, -70.71483672),
                        new LatLng(19.46914133, -70.71574867),
                        new LatLng(19.47049678, -70.71673572),
                        new LatLng(19.47144762, -70.71789443),
                        new LatLng(19.47196349, -70.71895659),
                        new LatLng(19.47292443, -70.72001874),
                        new LatLng(19.47432031, -70.72096288),
                        new LatLng(19.47577688, -70.7229048),
                        new LatLng(19.47839663, -70.7252866),
                        new LatLng(19.47928673, -70.72593033),
                        new LatLng(19.48617472, -70.73182046),
                        new LatLng(19.48748957, -70.7186991),
                        new LatLng(19.48945172, -70.71881711),
                        new LatLng(19.48910784, -70.7170254),
                        new LatLng(19.48827848, -70.71671963)
                ).width(10)
                .color(Color.rgb(076,145,065)));

       *//* int radius = 5;
        PolylineOptions options = new PolylineOptions()
                .add(new LatLng(19.48827848 + radius, -70.71671963 + radius))
                .add(new LatLng(19.48763117 + radius, -70.7144022 - radius))
                .add(new LatLng(19.48237169- radius, -70.70822239 - radius))
                .add(new LatLng(19.47703113 - radius, -70.69886684 + radius))
                .add(new LatLng(19.47468446 + radius, -70.69500446 + radius));*//*

    }
    */

}
