package com.software.nac.omsapasajero;

import android.accessibilityservice.GestureDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;

import com.google.gson.JsonArray;
import com.software.nac.omsapasajero.fragment.MainFragment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , OnMapReadyCallback{

    SupportMapFragment supportMapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportMapFragment =SupportMapFragment.newInstance();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fragmentManager = getFragmentManager();
       // fragmentManager.beginTransaction().replace(R.id.content_frame,new MainFragment()).commit();

        android.support.v4.app.FragmentManager sFm = getSupportFragmentManager();
        sFm.beginTransaction().add(R.id.content_frame,supportMapFragment).commit();

        //fragmentManager.beginTransaction().add(R.id.content_frame,supportMapFragment).commit();





        supportMapFragment.getMapAsync(this);

    }



    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, APIParadas> {
        @Override
        protected APIParadas doInBackground(Void... params) {
            try {
                final URL url = new URL("http://omsatracker.herokuapp.com/api/paradas/ruta/1"); // the  url from where to fetch data(json)
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept","application/json");
                conn.setConnectTimeout(10000);

                try{
                    if(conn.getResponseCode()!=200){
                        throw new RuntimeException("Failed: http Error code: "+ conn.getResponseCode());
                    }
                    BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line= buf.readLine())!=null)
                    {
                        builder.append(line).append("\n");
                    }
                    buf.close();
                    System.out.println(builder.toString());
                    System.out.println("==============================================================================");

                    List<APIParadas> apiParadases = new ArrayList<APIParadas>();

                    //JsonArray jsonElements =new JsonArray(builder.toString());

                }finally {
                    conn.disconnect();
                }
                return null;

            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        public  RestTemplate getRestTemplate()
        { RestTemplate restTemplate = new RestTemplate();

            if (restTemplate == null)
            {
                restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            }

            return restTemplate;
        }


        @Override
        protected void onPostExecute(APIParadas info) {
          //  System.out.printf(info.getId());

            //infoIdText.setText(info.getId());
        }

    }











































    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        android.app.FragmentManager fragmentManager = getFragmentManager();



        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Log.i("Inf" , "hola");

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    @Override
    public void onMapReady(GoogleMap googleMap) {

        latlngs.add(new LatLng(12.334343, 33.43434));

        LatLng marker = new LatLng(19.43469 , -70.6912);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker,17));
        googleMap.addMarker(new MarkerOptions()
                .title("Parada OMSA").position(marker)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.paradas)));

     /*   googleMap.addPolyline((new PolylineOptions())
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));*/
// Add a thin red line from London to New York.
        Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(19.48827848 , -70.71671963),
                        new LatLng(19.48763117, -70.7144022),
                        new LatLng(19.48263467,-70.70815265),
                        new LatLng(19.47703113,-70.69886684),
                        new LatLng(19.47478561,-70.69486499),
                        new LatLng(19.47148808,-70.69198966),
                        new LatLng(19.47100254,-70.69130301),
                        new LatLng(19.46970779,-70.68789124),
                        new LatLng(19.46914133,-70.68726897),
                        new LatLng(19.46072512,-70.68602443),
                        new LatLng(19.45645615,-70.68546653),
                        new LatLng(19.45366407,-70.68495154),
                        new LatLng(19.45218707,-70.68516612),
                        new LatLng(19.44777625,-70.68817019),
                        new LatLng(19.44684551,-70.68844914),
                        new LatLng(19.44387115,-70.68722606),
                        new LatLng(19.4414633,-70.68714023),
                        new LatLng(19.44077534,-70.68731189),
                        new LatLng(19.41610787,-70.70278287),
                        new LatLng(19.4155817,-70.70394158),
                        new LatLng(19.41448887,-70.71085095),
                        new LatLng(19.41351747,-70.72767377),
                        new LatLng(19.41398294,-70.72891831),
                        new LatLng(19.42074216,-70.73591352),
                        new LatLng(19.42810818,-70.72795272),
                        new LatLng(19.43053647,-70.72602153),
                        new LatLng(19.43268142,-70.72499156),
                        new LatLng(19.44101815,-70.72207332),
                        new LatLng(19.4422929,-70.72226644),
                        new LatLng(19.44488285,-70.72406888),
                        new LatLng(19.4456922,-70.72421908),
                        new LatLng(19.44830232,-70.72372556),
                        new LatLng(19.45068984,-70.7241118),
                        new LatLng(19.45164079,-70.72434783),
                        new LatLng(19.45475663,-70.72449803),
                        new LatLng(19.45524221,-70.72144568),
                        new LatLng(19.45518151,-70.72078049),
                        new LatLng(19.45423564,-70.71545631) ,
                        new  LatLng(19.45407378,-70.71541876),
                        new LatLng(19.45329482,-70.71546704),
                        new LatLng(19.45321389,-70.71533829),
                        new LatLng(19.45601104,-70.71514517),
                        new LatLng(19.45688609,-70.71499497),
                        new LatLng(19.45841868,-70.71481794),
                        new LatLng(19.45918749,-70.714598),
                        new LatLng(19.4630821,-70.71291894),
                        new LatLng(19.46372445,-70.71277946),
                        new LatLng(19.46434657,-70.71285993),
                        new LatLng(19.46518617,-70.71311206),
                        new LatLng(19.46782632,-70.71404278),
                        new LatLng(19.4685951,-70.71483672),
                        new LatLng(19.46914133,-70.71574867),
                        new LatLng(19.47049678,-70.71673572),
                        new LatLng(19.47144762,-70.71789443),
                        new LatLng(19.47196349,-70.71895659),
                        new LatLng(19.47292443,-70.72001874),
                        new LatLng(19.47432031,-70.72096288),
                        new LatLng(19.47577688,-70.7229048),
                        new LatLng(19.47839663,-70.7252866),
                        new LatLng(19.47928673,-70.72593033),
                        new LatLng(19.48617472,-70.73182046),
                        new LatLng(19.48748957,-70.7186991),
                        new LatLng(19.48945172,-70.71881711),
                        new LatLng(19.48910784,-70.7170254),
                        new LatLng(19.48827848,-70.71671963)
                ).width(10)
                .color(Color.RED));

       /* int radius = 5;
        PolylineOptions options = new PolylineOptions()
                .add(new LatLng(19.48827848 + radius, -70.71671963 + radius))
                .add(new LatLng(19.48763117 + radius, -70.7144022 - radius))
                .add(new LatLng(19.48237169- radius, -70.70822239 - radius))
                .add(new LatLng(19.47703113 - radius, -70.69886684 + radius))
                .add(new LatLng(19.47468446 + radius, -70.69500446 + radius));*/

    }
}
