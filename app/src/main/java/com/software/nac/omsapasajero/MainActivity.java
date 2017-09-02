package com.software.nac.omsapasajero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import static com.software.nac.omsapasajero.Omsa.getBackground;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static APIParadas[] objects;
    public static Ruta[] objectsRuta;
    private ViewFlipper viewFlipper;
    private int idCorredorToOpen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((ImageView) findViewById(R.id.logoImage)).setImageBitmap(getBackground(this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewSwitcher);

        findViewById(R.id.menuCerrado).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAnimator.animate(v)
                        .translationY(400)
                        .duration(1000)
                        .onStop(new AnimationListener.Stop() {
                            @Override
                            public void onStop() {
                                viewFlipper.showNext();

                                ViewAnimator.animate(findViewById(R.id.menuAbierto))
                                        .translationY(1800, 0)
                                        .duration(2000)
                                        .start();
                            }
                        })
                        .start();
            }
        });

        ListView listView = (ListView) findViewById(R.id.listViewCorredores);
        Adaptador adaptador = new Adaptador(this, Arrays.asList(new Ruta(1, "Santiago", "Corredor Guevo", "true"), new Ruta(2, "Santiago", "Corredor Semilla", "true"), new Ruta(3, "Santo Domingo", "Corredor Maldito Ciego", "true")));

        listView.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


    }


    @Override
    protected void onStart() {
        super.onStart();
        new HttpRequestTask().execute();
    }

    public void setIdCorredor(int position) {
        idCorredorToOpen = position;

        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                .duration(2000)
                .translationY(0, 1800)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showNext();
                        ViewAnimator.animate(findViewById(R.id.menuRutas))
                                .duration(1000)
                                .translationY(400, 0)
                                .start();
                    }
                })
                .start();
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, APIParadas> {
        @Override
        protected APIParadas doInBackground(Void... params) {
            /*try {
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
*/
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/paradas/ruta/1");

                HttpEntity<?> entity = new HttpEntity<>(headers);

                objects = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        APIParadas[].class).getBody();

                System.out.println("objects = " + objects[0]);


            } catch (Exception e) {
                e.printStackTrace();
            }


            //==================================Para la Ruta==========================

         /*   try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/ruta/1");

                HttpEntity<?> entity = new HttpEntity<>(headers);

                objectsRuta = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        Ruta[].class).getBody();

                System.out.println("objects = " + objectsRuta[0]);


            } catch (Exception e) {
                e.printStackTrace();
            }
*/


            return null;
        }


        public RestTemplate getRestTemplate() {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

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
            switch (viewFlipper.getCurrentView().getId()) {
                case R.id.menuRutas:
                    showOpenMenu();
                    break;
                case R.id.menuAbierto:
                    showCloseMenu();
                    break;
                default:
                    super.onBackPressed();

            }

        }
    }

    private void showCloseMenu() {
        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                .duration(2000)
                .translationY(0, 1800)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showPrevious();
                        ViewAnimator.animate(findViewById(R.id.menuCerrado))
                                .duration(1000)
                                .translationY(400, 0)
                                .start();
                    }
                })
                .start();
    }

    private void showOpenMenu() {
        ViewAnimator.animate(findViewById(R.id.menuRutas))
                .duration(1000)
                .translationY(0, 400)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showPrevious();
                        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                                .duration(2000)
                                .translationY(1800, 0)
                                .start();
                    }
                })
                .start();

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
            //  Log.i("Inf", "hola");
            Intent i = new Intent(MainActivity.this, DialogActivity.class);
            startActivity(i);

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
}
