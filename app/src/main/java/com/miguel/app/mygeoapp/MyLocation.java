package com.miguel.app.mygeoapp;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

public class MyLocation implements LocationListener {

    Context context;
    LayoutInflater inflater;
    Activity activity;

    public MyLocation(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
        activity = (Activity)(this.context);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        TextView txtPosition = (TextView)activity.findViewById(R.id.txtPosition);
        txtPosition.setText("Lat: " + location.getLatitude() + " | Long: " + location.getLongitude());

        Log.i("MITO_DEBUG", "Lat: " + location.getLatitude() + " | Long: " + location.getLongitude());

        DBLocationHelper dbHelper = new DBLocationHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.rawQuery("INSERT INTO positions ( lat, lng ) VALUES (?,?)", new String[] {String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())}).moveToNext();


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
