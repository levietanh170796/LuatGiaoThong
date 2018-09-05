package com.example.admin.luatgiaothong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Admin on 3/12/2016.
 */

public class FragmentListBienBao extends Fragment {


    public DatabaseHelper mDBHelper;
    private ListView listView;
    private AdapterBienBao adapterBienBao;
    private ArrayList<ItemBienBao> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_list_bien_bao,container,false);
        listView =(ListView)view.findViewById(R.id.listView);
        contacts = mDBHelper.getListLoaiBien(1);
        adapterBienBao = new AdapterBienBao(getActivity(),R.layout.item_bien_bao,contacts);
        listView.setAdapter(adapterBienBao);
        return view;
    }
}
