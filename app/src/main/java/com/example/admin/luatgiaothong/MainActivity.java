package com.example.admin.luatgiaothong;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
      FragmentDrawer.FragmentDrawerListener{

    private FragmentDrawer fragmentDrawer;
    private ListView listView;
    private AdapterLoi adapterLoi;
    public DatabaseHelper mDBHelper;
    private ArrayList<ItemLoiPhat> contacts;
    private EditText inputSearch;
    private TextView textViewTitle;
    ImageButton ibHome, ibMenu;
    DrawerLayout drawerLayout;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        mDBHelper = new DatabaseHelper(this);
        textViewTitle = (TextView)findViewById(R.id.title);


        /*
           Khong dung toolbar
         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creat navigation
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        fragmentDrawer =(FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        fragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), null);
        fragmentDrawer.setDrawerListener(this);

        // menu
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ibMenu = (ImageButton)findViewById(R.id.ib_menu);

        ibMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // check exist database navigationView.setNavigationItemSelectedListener(this);
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists()==false){
            mDBHelper.getReadableDatabase();
            // copy db
            if(mDBHelper.copyDatabase(this)){
                Toast.makeText(this,"copy succes",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"copy error",Toast.LENGTH_LONG).show();
                return;
            }
        }
        contacts = mDBHelper.getListLoiLoaiXe(2);
        adapterLoi= new AdapterLoi(this,R.layout.item_listview,contacts);
        listView.setAdapter(adapterLoi);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent("com.example.admin.luatgiaothong.ClickItemActivity");
               // intent.putExtra("id",id);
                ItemLoiPhat item = adapterLoi.getItem(position);
                intent.putExtra("id",item.getId());
                intent.putExtra("TenLoi",item.getTenLoi());
                intent.putExtra("NoiDung",item.getNoiDung());
                intent.putExtra("MucPhat",item.getMucPhat());
                intent.putExtra("LoaiXe",item.getLoaiXe());
                intent.putExtra("PhatBoSung",item.getPhatBoSung());
                intent.putExtra("DieuKhoan",item.getDieuKhoan());
                intent.putExtra("GroupValue",item.getGroupValue());
                intent.putExtra("DoiTuong",item.getDoiTuong());
                startActivity(intent);
            }
        });

        // get intent from navigation
        int id;
        intent = getIntent();
        try {
            id = intent.getExtras().getInt("id");
            contacts= mDBHelper.getListLoiLoaiXe(id);
            reViewListView(contacts);
        } catch (Exception e){
            drawerLayout.openDrawer(GravityCompat.START);
            id=2;
        }

        switch (id){
            case 2: textViewTitle.setText("Xử Phạt Xe Máy " );
                break;
            case 1: textViewTitle.setText("Xử Phạt Ô Tô ");
                break;
            case 22: textViewTitle.setText("Xử Phạt Xe Khách ");
                break;
            case 33: textViewTitle.setText("Xử Phạt Xe Tải ");
                break;
        }

        // tìm kiếm loi
        inputSearch =(EditText)findViewById(R.id.input_search);
        final int finalId = id;
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contacts = mDBHelper.getListLoiSearch(s.toString(), finalId);
                reViewListView(contacts);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


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
    /*@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    public void  reViewListView(  ArrayList<ItemLoiPhat> arr)
    {
        adapterLoi= new AdapterLoi(this,R.layout.item_listview,arr);
        listView.setAdapter(adapterLoi);
    }
}

