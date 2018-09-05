package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Admin on 3/12/2016.
 */

public class AdapterDuongDay extends ArrayAdapter<ItemDuongDay> {
    Activity context;
    TextView soDienThoai;
    public AdapterDuongDay(Activity context, int resource, List<ItemDuongDay> objects) {
        super(context, resource, objects);
        this.context= context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_duong_day,null);
        }
        TextView tenDiaChi = (TextView)convertView.findViewById(R.id.Ten_dia_chi);
        soDienThoai= (TextView)convertView.findViewById(R.id.so_dien_thoai);
        ImageView imageViewcCall = (ImageView)convertView.findViewById(R.id.call_duong_day);

        imageViewcCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMakeCall();
            }
        });

        // Lấy ra đối tượng cần hiển thị ở vị trí thứ position
        ItemDuongDay item = getItem(position);
        tenDiaChi.setText(item.getTenDiaChi());
        soDienThoai.setText(item.getSoDienThoai());
        return convertView;
    }
     public void doMakeCall()
    {
        Uri uri= Uri.parse("tel:"+soDienThoai.getText().toString());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        try{
            context.startActivity(intent);
            context.finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context,"khong goi duoc",Toast.LENGTH_LONG).show();
        }

    }
}
