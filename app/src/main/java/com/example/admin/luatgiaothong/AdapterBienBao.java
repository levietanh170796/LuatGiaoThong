package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 3/12/2016.
 */

public class AdapterBienBao extends ArrayAdapter<ItemBienBao> {
    Activity context;
    public AdapterBienBao(Activity context, int resource, List<ItemBienBao> objects) {
        super(context, resource, objects);
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_bien_bao,null);
        }
        TextView tenBien = (TextView)convertView.findViewById(R.id.Ten_bien_bao);
        //TextView noiDungBien= (TextView)convertView.findViewById(R.id.noi_dung_bien_bao);
        ImageView imageView =(ImageView)convertView.findViewById(R.id.imageView_bien_bao);
        // Lấy ra đối tượng cần hiển thị ở vị trí thứ position
        ItemBienBao bienBao= getItem(position);
        tenBien.setText(bienBao.getTenBien());
       // noiDungBien.setText(bienBao.getNoiDung());

        int resID = context.getResources().getIdentifier(bienBao.getAnhBien(),"drawable",context.getPackageName());
        imageView.setImageDrawable(context.getResources().getDrawable(resID));
        return convertView;
    }
}
