package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 30/9/2016.
 */
public class AdapterLoi extends ArrayAdapter<ItemLoiPhat> {
    Activity context;
    public AdapterLoi(Activity context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_listview,null);
        }
        TextView tenLoi = (TextView)convertView.findViewById(R.id.Ten_loi);
        TextView noiDung= (TextView)convertView.findViewById(R.id.noi_dung);
        TextView mucPhat = (TextView) convertView.findViewById(R.id.muc_phat);
        ImageView imageView =(ImageView)convertView.findViewById(R.id.imageView);
        // Lấy ra đối tượng cần hiển thị ở vị trí thứ position
        ItemLoiPhat loiPhat= getItem(position);
        // Set dữ liệu vào item của list view
        switch (loiPhat.getGroupValue()) {
            case 1:
                imageView.setImageResource(R.drawable.bao_hieu);
                break;
            case 2:
                if(loiPhat.getLoaiXe()==2) {
                    imageView.setImageResource(R.drawable.man);
                } else  if(loiPhat.getLoaiXe()==1||loiPhat.getLoaiXe()==22||loiPhat.getLoaiXe()==33) {
                    imageView.setImageResource(R.drawable.car_driver);
                }
                break;
            case 4:
                imageView.setImageResource(R.drawable.luu_thong);
                break;
            case 8:
                imageView.setImageResource(R.drawable.giay_phep);
                break;
            case 16:
                if(loiPhat.getLoaiXe()==2) {
                    imageView.setImageResource(R.drawable.phuong_tien);
                } else  if(loiPhat.getLoaiXe()==1) {
                    imageView.setImageResource(R.drawable.car);
                }
                break;
            case 3316:
                imageView.setImageResource(R.drawable.phuong_tien_truck);
                break;
            case 2216:
                imageView.setImageResource(R.drawable.phuong_tien_bus);
                break;
            case 32:
                imageView.setImageResource(R.drawable.toc_do);
                break;
            case 64:
                imageView.setImageResource(R.drawable.bao_hiem);
                break;
            case 128:
                if(loiPhat.getLoaiXe()==2) {
                    imageView.setImageResource(R.drawable.motorbike);
                } else  if(loiPhat.getLoaiXe()==1) {
                    imageView.setImageResource(R.drawable.car);
                }
                break;
        }
        noiDung.setText(loiPhat.getNoiDung());
        tenLoi.setText(loiPhat.getTenLoi());
        mucPhat.setText(loiPhat.getMucPhat());
        return convertView;
    }
}
