package com.miguel.app.mygeoapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    ListView myList;
    CustomAdapter adapter;
    DBLocationHelper dbHelper;

    List<MyAddress> addresses;

    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        addresses = new ArrayList<>();
        myList = findViewById(R.id.myList);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        MyLocation myLocation = new MyLocation(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, myLocation);

            Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Devi abilitare GPS", Toast.LENGTH_SHORT).show();
        }

        dbHelper = new DBLocationHelper(this);
        getData();
        adapter = new CustomAdapter(context, addresses); // addresses è la lista con i dati degli indirizzi
        myList.setAdapter(adapter); // MyList è la mia listview

        btnUpdate.setOnClickListener(btnUpdateEvent);
        btnDelete.setOnClickListener(btnDeleteEvent);

    }

    private View.OnClickListener btnUpdateEvent = v -> {
        addresses.clear(); // Cancello la lista per ripopolarla di nuovo
        getData(); // Faccio la query per ottenere i dati e ripopolare "addresses"
        adapter.loadData(addresses);
        myList.invalidateViews();
        myList.refreshDrawableState();
    };

    private View.OnClickListener btnDeleteEvent = v -> {
        SQLiteDatabase db = dbHelper.getWritableDatabase(); // WRITE
        dbHelper.cleanDB(); // Cancella il database e lo ricrea ... da non fare...

        addresses.clear(); // Cancello la lista per ripopolarla di nuovo
        getData(); // Faccio la query per ottenere i dati e ripopolare "addresses"
        adapter.loadData(addresses);
        myList.invalidateViews();
        myList.refreshDrawableState();
    };


    private void getData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String customQuery = "SELECT * FROM positions";

        Cursor cursor = db.rawQuery(customQuery, null);

        while (cursor.moveToNext()) {
            String tmpLatitute = cursor.getString(cursor.getColumnIndex("lat"));
            String tmpLongitude = cursor.getString(cursor.getColumnIndex("lng"));
            String tmpAddress = cursor.getString(cursor.getColumnIndex("address"));
            int tmpID = cursor.getInt(cursor.getColumnIndex("id"));

            addresses.add(new MyAddress(tmpLatitute, tmpLongitude, tmpAddress, tmpID));
        }
        cursor.close();
    }

}