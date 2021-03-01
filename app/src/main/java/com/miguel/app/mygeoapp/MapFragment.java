package com.miguel.app.mygeoapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.miguel.app.mygeoapp.model.DBLocationHelper;
import com.miguel.app.mygeoapp.model.MyAddress;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    GoogleMap mMap;
    Button btnUpdateFragment;
    DBLocationHelper dbHelper;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng firenze = new LatLng(43.77655888113951, 11.255727961077028);  // Coordinate di Firenze / Florence
        mMap.addMarker(new MarkerOptions().position(firenze).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firenze, 10)); // Posiziono la mappa su firenze

        double extraLatitude = Double.valueOf(getActivity().getIntent().getStringExtra("latitude"));
        double extraLongitude = Double.valueOf(getActivity().getIntent().getStringExtra("longitude"));

        Log.i("MITO_DEBUG", "lat: " + extraLatitude);
        Log.i("MITO_DEBUG", "long: " + extraLongitude);

        LatLng myPosition = new LatLng(extraLatitude, extraLongitude);
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(myPosition).title("Marker della mia posizione"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 16));


        btnUpdateFragment = getActivity().findViewById(R.id.btnUpdateFragment);


//        btnUpdateFragment.setOnClickListener(v -> {
//
//            PolylineOptions optionsx = new PolylineOptions();
//            optionsx.color(Color.GREEN);
//            optionsx.width(2f);
//
//            Cursor cursor = getData();
//
//            while (cursor.moveToNext()) {
//                double tmpLatitute = cursor.getDouble(cursor.getColumnIndex("lat"));
//                double tmpLongitude = cursor.getDouble(cursor.getColumnIndex("lng"));
////            String tmpAddress = cursor.getString(cursor.getColumnIndex("address"))
//
//                LatLng myPositionx = new LatLng(tmpLatitute, tmpLongitude);
//                optionsx.add(myPositionx);
//            }
//            cursor.close();
//
//            Polyline myPath = mMap.addPolyline(optionsx);
//
//            Log.i("MITO_TAG", "show me ");


//            LatLng firenze = new LatLng(43.77655888113951, 11.255727961077028);  // Coordinate di Firenze / Florence
//            mMap.addMarker(new MarkerOptions().position(firenze).title("Marker in Sydney"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firenze, 10)); // Posiziono la mappa su firenze

//            PolygonOptions options = new PolygonOptions();
//            options.fillColor(Color.RED);
//            options.add(firenze);
//        });


    }

    private Cursor getData() {
        dbHelper = new DBLocationHelper((Context) getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase(); // READ
        String customQuery = "SELECT * FROM positions";

        Cursor cursor = db.rawQuery(customQuery, null);

        return cursor;
    }

}