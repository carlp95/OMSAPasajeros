package com.software.nac.omsapasajero;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RatingBar extends AppCompatActivity {

   // RatingBar ratingbar1;
    final boolean solo = false;
    ProgressDialog pDialog;

    private APIService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        final android.widget.RatingBar ratingbar1= (android.widget.RatingBar) findViewById(R.id.ratingBar);
        final EditText comentario= (EditText) findViewById(R.id.edtComentario);
        //comentario.setSelection(0);
        final Button enviar = (Button) findViewById(R.id.btnRating);

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String todayString = formatter.format(currentTime);


        final long realDate = currentTime.getTime();
        long fechaFin = realDate/1000;
        final long fechafin2= (long) Math.floor(fechaFin);
        Log.i("calvo", todayString);
        Log.i("calvo", String.valueOf(realDate));
        Log.i("calvo", String.valueOf(fechaFin));
        Log.i("calvo", String.valueOf(fechaFin));

        //final Integer numeroDePuntuacion = Integer.valueOf((int) ratingbar1.getRating());
       // Log.i("calvo", String.valueOf(ratingbar1.getNumStars()));


        // ratingbar1=(RatingBar)findViewById(R.id.ratingBar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Log.i("dataaaa",post.toString());

                if (ratingbar1.getRating()==0 || comentario.getText().toString().isEmpty()){
                    Toast.makeText(RatingBar.this, "Complete la evaluación.", Toast.LENGTH_SHORT).show();
                    return;
                }else {


                    Post post = new Post();
                    post.setComentario(comentario.getText().toString().trim());
                    post.setFechaPublicada(fechafin2);
                    post.setNumeroDePuntuacion((int) ratingbar1.getRating());
                    Log.d("calvo",post.toString());
                    pDialog = new ProgressDialog(RatingBar.this);
                    pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pDialog.setMessage("Enviando evaluación...");
                    pDialog.setCancelable(true);
                    pDialog.setMax(100);
                    new PostComentario().execute(post);

                }


                //Log.i("calvo", String.valueOf(ratingbar1.getRating()));


                ;
                //registrarToken(post);



            }
        });

     //   mAPIService = ApiUtils.getAPIService();

        //RatingBar a = (RatingBar) findViewById(R.id.ratingBar);

      //  addListenerOnButtonClick();

    }



/*

    private void addListenerOnButtonClick() {

        //Performing action on Button Click
        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //String title = ratingbar1.getText().toString().trim();


                String date = todayString.trim();
                String rating=String.valueOf(ratingbar1.getRating()).trim();
                String body = comentario.getText().toString().trim();

                Log.i("fechaaa1", date);
                Log.i("fechaaa1", rating);
                Log.i("fechaaa1", body);


                if(realDate != null && !TextUtils.isEmpty(rating)&& !TextUtils.isEmpty(body)) {

                    sendPost(realDate,rating,body);
                }


                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }
        });


    }
*/



    private class PostComentario extends AsyncTask<Post, Void, Post> {
        @Override
        protected Post doInBackground(Post... params) {

            try {
                Log.i("siiiiiiiii",params[0].toString());
                registrarToken(params[0]);



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
                    RatingBar.PostComentario.this.cancel(true);
                }


            });

            pDialog.setProgress(0);
            pDialog.show();
        }


        @Override
        protected void onPostExecute(Post post) {
            super.onPostExecute(post);



            pDialog.dismiss();
            Toast.makeText(RatingBar.this, "Evaluación enviada.", Toast.LENGTH_SHORT).show();
            Intent a = new Intent(RatingBar.this, MainActivity.class);
            startActivity(a);

        }

        @Override
        protected void onCancelled() {

        }

        public  Post registrarToken(Post credencial){
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            return restTemplate.postForObject("http://gps-qjm.herokuapp.com/api/recibir/comentario/",credencial,Post.class);
        }





    }

    private void sendPost(Post post) {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://gps-qjm.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();


        /*StringBuilder JSONString = new StringBuilder();
        Gson gson1 = new Gson();*/

        APIService client = retrofit.create(APIService.class);

    //    Post post1 = gson.fromJson(JSONString.toString().trim(), Post.class);

        Call<Post> call = client.savePost(post);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d("mensaje", response.message());
                Toast.makeText(RatingBar.this, "yeah"+response.body().getId(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(RatingBar.this, "noo",Toast.LENGTH_SHORT).show();
                Log.i("mensaje",t.getMessage());
                Log.d("hesus","dsf");
            }
        });


    }

}
