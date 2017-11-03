package com.software.nac.omsapasajero;

/**
 * Created by Neury on 11/2/2017.
 */

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @POST("/recibir/comentario/")
    @FormUrlEncoded
    Call<Post> savePost(@Field("fechaPublicada") Long fechaPublicada,
                        @Field("numeroDePuntuacion") Integer numeroDePuntuacion,
                        @Field("comentario") String comentario);
}
