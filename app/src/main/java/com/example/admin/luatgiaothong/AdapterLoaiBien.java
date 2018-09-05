package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 3/12/2016.
 */

public class AdapterLoaiBien extends ArrayAdapter<String> {

    Activity context;
    public AdapterLoaiBien(Activity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context= context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_loai_bien_bao,null);
        }
        TextView tenLoaiBien = (TextView)convertView.findViewById(R.id.Ten_loai_bien);
        ImageView imageView =  (ImageView)convertView.findViewById(R.id.imageView_loai_bien);
        tenLoaiBien.setText(getItem(position));
        if (position==0){
            imageView.setImageResource(R.drawable.d101);
        } else if(position == 1){
            imageView.setImageResource(R.drawable.d201);
        }
        else if(position == 2){
            imageView.setImageResource(R.drawable.d301a);
        }
        else if(position == 3){
            imageView.setImageResource(R.drawable.d401);
        }
        else if(position == 4){
            imageView.setImageResource(R.drawable.d501);
        }
        return convertView;
    }
}
