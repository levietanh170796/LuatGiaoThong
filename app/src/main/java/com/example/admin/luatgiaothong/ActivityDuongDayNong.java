package com.example.admin.luatgiaothong;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.util.ArrayList;

public class ActivityDuongDayNong extends AppCompatActivity {
    private ListView listView;
    private ArrayList<ItemDuongDay> contacts;
    public AdapterDuongDay adapterDuongDay;
    public DatabaseHelper mDBHelper;
    private EditText inputSearch;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duong_day_nong);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_back_duong_day);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = getIntent();
        mDBHelper = new DatabaseHelper(this);
        listView =(ListView)findViewById(R.id.listView);
        contacts = mDBHelper.getListDuongDay();
        adapterDuongDay = new AdapterDuongDay(this,R.layout.item_duong_day,contacts);
        listView.setAdapter(adapterDuongDay);


        /*ListV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg){
                                            TODO Auto-generated method stub
                                            TextView v = (TextView) view.findViewById(R.id.txtLstItem);
                                            Toast.makeText(getApplicationContext(), "selected Item Name is " + v.getText(), Toast.LENGTH_LONG).show();
                                        }
                                    }
        );*/
        inputSearch =(EditText)findViewById(R.id.input_search);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contacts = mDBHelper.getListDuongDay(s.toString());
                reViewListView(contacts);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
   /* public void doMakeCall()
    {
        Uri uri= Uri.parse("tel:"+addRess1.getText().toString());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        try{
            startActivity(intent);
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"khong goi duoc",Toast.LENGTH_LONG).show();
        }

    }*/
   public void  reViewListView(  ArrayList<ItemDuongDay> arr)
   {
       adapterDuongDay= new AdapterDuongDay(this,R.layout.item_duong_day,arr);
       listView.setAdapter(adapterDuongDay);
   }
}
