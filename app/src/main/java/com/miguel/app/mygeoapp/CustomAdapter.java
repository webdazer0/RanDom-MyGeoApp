package com.miguel.app.mygeoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater inflater;
    List<MyAddress> addresses;

    public CustomAdapter(Context ctx, List<MyAddress> addresses) {
        this.ctx = ctx;
        this.inflater = LayoutInflater.from(ctx);
        this.addresses = addresses;
    }

    public void loadData(List<MyAddress> addresses) {
        this.addresses = addresses;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.item_list, null);

        TextView txtLat = convertView.findViewById(R.id.txtLat);
        TextView txtLng = convertView.findViewById(R.id.txtLng);
        TextView txtAddress = convertView.findViewById(R.id.txtAddress);


        txtLat.setText(addresses.get(position).getLatitude());
        txtLng.setText(addresses.get(position).getLongitude());
        txtAddress.setText(addresses.get(position).getAddress());

//        btnDelete.setOnClickListener((View.OnClickListener) v -> {
//            Log.i("MITO_TAG", "Hai clicatto il idbtn: " + getItemApiId(position) + " e anche: id: " + getItemId(position));
//            deleteStudentAPI(context, getItemApiId(position));
////                deleteSQL(getItemId(position));
//        });

        return convertView;
    }
}
