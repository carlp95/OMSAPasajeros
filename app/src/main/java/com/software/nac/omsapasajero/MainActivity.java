package com.software.nac.omsapasajero;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
    ProgressDialog pDialog;

    @Override
    protected void onStart() {
        super.onStart();

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialog.setMessage("Buscando lista de Corredores...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);
        new HttpRequestTask2().execute();

    }
//================================================================================//

    private class HttpRequestTask2 extends AsyncTask<Void, Void, Ruta> {
        @Override
        protected Ruta doInBackground(Void... params) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

                UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://gps-qjm.herokuapp.com/api/rutas/buscar");

                HttpEntity<?> entity = new HttpEntity<>(headers);

                listRuta = getRestTemplate().exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        entity,
                        Ruta[].class).getBody();

                //System.out.println("objects ============================================ " + listRuta);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

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
                        MainActivity.HttpRequestTask2.this.cancel(true);
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
        protected void onPostExecute(Ruta info) {

            pDialog.dismiss();
            findViewById(R.id.menuCerrado).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.i("wwwwwwwwwwwwww","aquiiiiiiiiii");
                    ListView listView = (ListView) findViewById(R.id.listViewCorredores);

                    try {
                        //ArrayList<Ruta> list = (ArrayList<Ruta>) Arrays.asList(listRuta);
                        ArrayList<Ruta> list = new ArrayList<Ruta>(Arrays.asList(listRuta));
                        //  Log.i("datos" , String.valueOf(list));
                        Adaptador adaptador = new Adaptador(MainActivity.this,list);

                        listView.setAdapter(adaptador);
                        adaptador.notifyDataSetChanged();
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    ViewAnimator.animate(v)
                            .translationY(400)
                            .duration(500)
                            .onStop(new AnimationListener.Stop() {
                                @Override
                                public void onStop() {
                                    viewFlipper.showNext();

                                    ViewAnimator.animate(findViewById(R.id.menuAbierto))
                                            .translationY(1800, 0)
                                            .duration(500)
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
        setContentView(R.layout.app_bar_main);

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

        findViewById(R.id.iraid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, MapsActivityIrA.class);
                startActivity(a);
            }
        });

      /*  findViewById(R.id.idbajada).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, MapsActivity.class)
                        .putExtra("id",idCorredorToOpen)
                        .putExtra("id2",idCorredorToOpen+1);

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
        });*/

        viewFlipper = (ViewFlipper) findViewById(R.id.viewSwitcher);




       // Log.i("datos" , String.valueOf(list));


//Arrays.asList(new Ruta(1, "Santiago", "Corredor principal 1", "true"), new Ruta(2, "Santiago", "Corredor central", "true"), new Ruta(3, "Santo Domingo", "Corredor C3", "true")



    }





    public void setIdCorredor(int position) {
        idCorredorToOpen = position;

        Intent a = new Intent(MainActivity.this, MapsActivity.class)
                .putExtra("id",idCorredorToOpen)
                .putExtra("id2",idCorredorToOpen+1);

        startActivity(a);

        /*ViewAnimator.animate(findViewById(R.id.menuAbierto))
                .duration(500)
                .translationY(0, 1800)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showNext();
                        ViewAnimator.animate(findViewById(R.id.menuRutas))
                                .duration(300)
                                .translationY(400, 0)
                                .start();
                    }
                })
                .start();
*/

    }


    @Override
    public void onBackPressed() {
        switch (viewFlipper.getCurrentView().getId()) {
            case R.id.menuRutas:
                showOpenMenu();
                break;
            case R.id.menuAbierto:
                // Log.i("qqqqqqqqqqqqqqqq","aquiiiiiiiiii");
                showCloseMenu();
                break;
            default:
                super.onBackPressed();

        }
    }

    private void showCloseMenu() {
        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                .duration(500)
                .translationY(0, 1800)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showPrevious();
                        ViewAnimator.animate(findViewById(R.id.menuCerrado))
                                .duration(300)
                                .translationY(400, 0)
                                .start();
                    }
                })
                .start();
    }

    private void showOpenMenu() {
        ViewAnimator.animate(findViewById(R.id.menuRutas))
                .duration(500)
                .translationY(0, 400)
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        viewFlipper.showPrevious();
                        ViewAnimator.animate(findViewById(R.id.menuAbierto))
                                .duration(300)
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
            Intent comentario = new Intent(MainActivity.this, RatingBar.class);
            startActivity(comentario);
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
