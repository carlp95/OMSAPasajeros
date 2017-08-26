package com.software.nac.omsapasajero;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Neury on 8/26/2017.
 */

public class Omsa {



    private static Bitmap background = null;

    public static Bitmap getBackground(Context activity)
    {
        try
        {
            if (background == null)
            {
                AssetManager assetManager = activity.getResources().getAssets();
                InputStream inputStream = assetManager.open("logo/Done.jpg");
                Bitmap original = BitmapFactory.decodeStream(inputStream);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                original.compress(Bitmap.CompressFormat.JPEG, 100, out);

                background = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
            }

            return background;
        } catch (Exception e)
        {
            return null;
        }
    }






}
