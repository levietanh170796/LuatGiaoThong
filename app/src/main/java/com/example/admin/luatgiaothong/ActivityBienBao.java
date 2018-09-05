package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.io.File;
import java.util.ArrayList;

public class ActivityBienBao extends AppCompatActivity implements
        FragmentDrawer.FragmentDrawerListener{

    private FragmentDrawer fragmentDrawer;
    private FragmentListBienBao fm;
    private ListView listView;
    public DatabaseHelper mDBHelper;
    private ArrayList<String> contacts;
    private AdapterLoaiBien adapterLoaiBien;
    private EditText inputSearch;
    ImageButton ibHome, ibMenu;
    DrawerLayout drawerLayout;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bien_bao);
        listView = (ListView) findViewById(R.id.listView);
        mDBHelper = new DatabaseHelper(this);
        intent = getIntent();

        fragmentDrawer = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), null);
        fragmentDrawer.setDrawerListener(this);

        contacts = new ArrayList<String>();
        contacts.add(0,"Biển báo cấm");
        contacts.add(1,"Biển báo nguy hiểm");
        contacts.add(2,"Biển báo hiệu lệnh");
        contacts.add(3,"Biển báo chỉ dẫn");
        contacts.add(4,"Biển báo phụ");

        adapterLoaiBien = new AdapterLoaiBien(this,R.layout.item_loai_bien_bao,contacts);
        listView.setAdapter(adapterLoaiBien);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // set fragment
                intent = new Intent(getApplicationContext(),ActivityListBienBao.class);
                intent.putExtra("loai_bien",position+1);
                startActivity(intent);
            }
        });

        // menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ibMenu = (ImageButton) findViewById(R.id.ib_menu);

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // check exist database
        /*File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (database.exists() == false) {
            mDBHelper.getReadableDatabase();
            // copy db
            if (mDBHelper.copyDatabase(this)) {
                Toast.makeText(this, "copy succes", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "copy error", Toast.LENGTH_LONG).show();
                return;
            }
        }*/
    }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public void onDrawerItemSelected(View view, int position) {

        }
}
