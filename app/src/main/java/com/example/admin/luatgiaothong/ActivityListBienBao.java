package com.example.admin.luatgiaothong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.util.ArrayList;

public class ActivityListBienBao extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ItemBienBao> contacts;
    public AdapterBienBao adapterBienBao;
    public DatabaseHelper mDBHelper;
    private EditText inputSearch;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bien_bao);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_back_bien_bao);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        mDBHelper = new DatabaseHelper(this);
        listView =(ListView)findViewById(R.id.listView);
        contacts = mDBHelper.getListLoaiBien( intent.getExtras().getInt("loai_bien"));
        adapterBienBao = new AdapterBienBao(this,R.layout.item_bien_bao,contacts);
        listView.setAdapter(adapterBienBao);
        inputSearch =(EditText)findViewById(R.id.input_search);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contacts = mDBHelper.getListBienSearch(s.toString(),intent.getExtras().getInt("loai_bien"));
                reViewListView(contacts);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void  reViewListView(  ArrayList<ItemBienBao> arr)
    {
        adapterBienBao= new AdapterBienBao(this,R.layout.item_bien_bao,arr);
        listView.setAdapter(adapterBienBao);
    }

}
