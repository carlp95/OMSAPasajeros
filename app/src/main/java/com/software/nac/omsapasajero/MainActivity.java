package com.software.nac.omsapasajero;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
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

    public static Ruta[] listRuta;
    public static Ruta[] objectsRuta;
    private ViewFlipper viewFlipper;
    private int idCorredorToOpen = 0;



    @Override
    protected void onStart() {
        super.onStart();

        new HttpRequestTask2().execute();

    }







//================================================================================//

        private class HttpRequestTask2 extends AsyncTask<Void, Void, Ruta> {
        @Override
        protected Ruta doInBackground(Void... params) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://omsa.herokuapp.com/api/rutas/buscar");

                HttpEntity<?> entity = new HttpEntity<>(headers);

                listRuta = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        Ruta[].class).getBody();

                System.out.println("objects ============================================ " + listRuta);





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

            findViewById(R.id.menuCerrado).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListView listView = (ListView) findViewById(R.id.listViewCorredores);

                    //ArrayList<Ruta> list = (ArrayList<Ruta>) Arrays.asList(listRuta);
                    ArrayList<Ruta> list = new ArrayList<Ruta>(Arrays.asList(listRuta));
                    //  Log.i("datos" , String.valueOf(list));
                    Adaptador adaptador = new Adaptador(MainActivity.this,list);

                    listView.setAdapter(adaptador);
                    adaptador.notifyDataSetChanged();

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


        }

    }



    //============fuera====================================================================//


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((ImageView) findViewById(R.id.logoImage)).setImageBitmap(getBackground(this));

     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);*/

        findViewById(R.id.idbajada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, MapsActivity.class)
                        .putExtra("id",idCorredorToOpen+1);
                startActivity(a);

            }
        });


        findViewById(R.id.idSubida).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, MapsActivity.class)
                        .putExtra("id",idCorredorToOpen);
                startActivity(a);

            }
        });

        viewFlipper = (ViewFlipper) findViewById(R.id.viewSwitcher);




       // Log.i("datos" , String.valueOf(list));


//Arrays.asList(new Ruta(1, "Santiago", "Corredor principal 1", "true"), new Ruta(2, "Santiago", "Corredor central", "true"), new Ruta(3, "Santo Domingo", "Corredor C3", "true")



    }





    public void setIdCorredor(int position) {
        idCorredorToOpen = position;

        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                .duration(1000)
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
                .duration(1000)
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
