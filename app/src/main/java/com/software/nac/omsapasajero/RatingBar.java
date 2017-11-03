package com.software.nac.omsapasajero;

import android.app.Activity;
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

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingBar extends AppCompatActivity {

   // RatingBar ratingbar1;
    final boolean solo = false;
    android.widget.RatingBar ratingbar1;
    EditText comentario;
    Button enviar;
    private APIService mAPIService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);


        mAPIService = ApiUtils.getAPIService();

        //RatingBar a = (RatingBar) findViewById(R.id.ratingBar);


        addListenerOnButtonClick();






    }

    private void addListenerOnButtonClick() {
        ratingbar1 = (android.widget.RatingBar) findViewById(R.id.ratingBar);
       // ratingbar1=(RatingBar)findViewById(R.id.ratingBar);
        comentario = (EditText) findViewById(R.id.edtComentario);
        enviar = (Button) findViewById(R.id.btnRating);
        //Performing action on Button Click
        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //String title = ratingbar1.getText().toString().trim();
                Date currentTime = Calendar.getInstance().getTime();
                String date = currentTime.toString().trim();
                String rating=String.valueOf(ratingbar1.getRating()).trim();
                String body = comentario.getText().toString().trim();

                if(!TextUtils.isEmpty(date) && !TextUtils.isEmpty(rating)&& !TextUtils.isEmpty(body)) {
                    sendPost(date,rating,body);
                }

                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendPost(String date, String rating, String body) {
        Long realDate = Long.valueOf(date);
        Integer realInteger = Integer.valueOf(rating);
        mAPIService.savePost(realDate, realInteger, body).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if(response.isSuccessful()) {
                    //showResponse(response.body().toString());
                    Log.i("Info", "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("Info", "Unable to submit post to API.");
            }
        });


    }


}
