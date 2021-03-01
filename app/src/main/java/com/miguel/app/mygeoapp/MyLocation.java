package com.miguel.app.mygeoapp;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.miguel.app.mygeoapp.model.DBLocationHelper;

import java.util.List;
import java.util.Locale;

public class MyLocation implements LocationListener {

    Context context;
    LayoutInflater inflater;
    Activity activity;

    public MyLocation(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        activity = (Activity) (this.context);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        TextView txtPosition = (TextView) activity.findViewById(R.id.txtPosition);
        txtPosition.setText("Lat: " + location.getLatitude() + " | Long: " + location.getLongitude());

        String myLocality = "";

        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);

            if (addresses.size() > 0) {
                for (Address address : addresses) {
//                    Log.i("MITO_DEBUG", address.getLocality());
                    Log.i("MITO_DEBUG", address.getAddressLine(0));
                }
                myLocality = addresses.get(0).getAddressLine(0);
            }

        } catch (Exception error) {
            Log.e("MITO_DEBUG", "error " + error.getMessage());
        }

        Log.i("MITO_DEBUG", "Lat: " + location.getLatitude() + " | Long: " + location.getLongitude());

        DBLocationHelper dbHelper = new DBLocationHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("INSERT INTO positions ( lat, lng, address ) VALUES (?,?,?)", new String[]{String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()), myLocality}).moveToNext();


    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
