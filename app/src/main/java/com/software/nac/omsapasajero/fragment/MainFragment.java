package com.software.nac.omsapasajero.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.software.nac.omsapasajero.R;

/**
 * Created by Neury on 5/18/2017.
 */

public class MainFragment extends Fragment{
   /* @NonNull
    @Override
    public View OnCreateView (LayoutInflater inflater , ViewGroup conteiner, Bundle saveInstanceState){
        return inflater.inflate(R.id.content_frame);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        return rootView;

    }
}
