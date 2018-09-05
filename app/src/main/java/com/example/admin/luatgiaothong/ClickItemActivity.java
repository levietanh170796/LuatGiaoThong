package com.example.admin.luatgiaothong;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.luatgiaothong.database.DatabaseHelper;

import java.util.ArrayList;

public class ClickItemActivity extends AppCompatActivity{
   private TextView tenLoi;
    private TextView content;
    private TextView mucPhat;
    private TextView loaiXe;
    private TextView phatBoSung;
    private TextView doiTuong;

    private String s1,s2,s3;
    private ListView listView;
    private AdapterLoi adapterLoi;
    DatabaseHelper mDBHelper;
    ArrayList<ItemLoiPhat> contacts;
    Toolbar toolbar;
    ImageView imageBack ;
    private ImageView imageView;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_item);
        // khoi tao toobar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // khoi tạo cac tẽtview
        tenLoi=(TextView)findViewById(R.id.Ten_loi);
        content=(TextView)findViewById(R.id.text_view_content);
        mucPhat=(TextView)findViewById(R.id.text_view_muc_phat);
        phatBoSung=(TextView)findViewById(R.id.text_view_phat_bo_sung);
        doiTuong=(TextView)findViewById(R.id.text_view_doi_tuong);

        //loaiXe=(TextView)findViewById(R.id.text_view_loaixe);
        listView=(ListView)findViewById(R.id.list_goi_y);
        imageView=(ImageView)findViewById(R.id.image_chitiet);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.layout_back);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mDBHelper = new DatabaseHelper(this);
        Intent intent = getIntent();

        //String name_loi=intent.getExtras().getString("TenLoi");

        // set noi dung nhan tu intent
        tenLoi.setText(intent.getExtras().getString("TenLoi"));
        content.setText(intent.getExtras().getString("NoiDung")+
                " ("+intent.getExtras().getString("DieuKhoan")+")");
        doiTuong.setText(intent.getExtras().getString("DoiTuong")+":");
        mucPhat.setText(intent.getExtras().getString("MucPhat"));
        phatBoSung.setText(intent.getExtras().getString("PhatBoSung"));

        switch (intent.getExtras().getInt("GroupValue")) {
            case 1:
                imageView.setImageResource(R.drawable.bao_hieu);
                break;
            case 2:
                if(intent.getExtras().getInt("LoaiXe")==2) {
                    imageView.setImageResource(R.drawable.man);
                } else  if(intent.getExtras().getInt("LoaiXe")==1||intent.getExtras().getInt("LoaiXe")==22
                        ||intent.getExtras().getInt("LoaiXe")==33) {
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
                if(intent.getExtras().getInt("LoaiXe")==2) {
                    imageView.setImageResource(R.drawable.phuong_tien);
                } else  if(intent.getExtras().getInt("LoaiXe")==1) {
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
                if(intent.getExtras().getInt("LoaiXe")==2) {
                    imageView.setImageResource(R.drawable.motorbike);
                } else  if(intent.getExtras().getInt("LoaiXe")==1) {
                    imageView.setImageResource(R.drawable.car);
                }
                break;
        }
        /*if(intent.getExtras().getInt("LoaiXe")==1){
           // loaiXe.setText("Xe ô tô");
            imageLoaiXe.setImageResource(R.drawable.car);
        }
        else   if(intent.getExtras().getInt("LoaiXe")==2){
           // loaiXe.setText("Xe máy");
            imageLoaiXe.setImageResource(R.drawable.motorbike);
        }
        else {
           // loaiXe.setText("Xe ô tô và xe máy");
            imageLoaiXe.setImageResource(R.drawable.car_motorbike);
        }*/
        // Lấy ra 3 từ đầu trong tên lỗi
        /*char[] str = name_loi.toCharArray();
        int dau_xau, length_xau,count;
        count=0;
        dau_xau =0;
        for (int i=0; i<name_loi.length()-1; i++) {
            if (name_loi.charAt(i)==' ' && count ==0){
                 s1= String.copyValueOf(str,dau_xau,i-dau_xau);
                 dau_xau=i+1;
                count++;
            } else {
                if (name_loi.charAt(i)==' ' && count ==1) {
                    s2 = String.copyValueOf(str, dau_xau, i - dau_xau);
                    dau_xau = i + 1;
                    count++;
                }
                else {
                    if (name_loi.charAt(i)==' ' && count ==2){
                        s3= String.copyValueOf(str,dau_xau,i-dau_xau);
                        dau_xau=i+1;
                        count++;
                    }
                }
            }


        }*/
        // lay ra goi y
        contacts= mDBHelper.getListLoiGoiY(tenLoi.getText().toString(),intent.getExtras().getInt("LoaiXe"));
        adapterLoi= new AdapterLoi(this,R.layout.item_listview,contacts);
        listView.setAdapter(adapterLoi);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemLoiPhat item = adapterLoi.getItem(position);
                tenLoi.setText(item.getTenLoi());
                content.setText(item.getDoiTuong()+"\n"+item.getNoiDung()+" "+item.getDieuKhoan());
                mucPhat.setText(item.getMucPhat());
                phatBoSung.setText(item.getPhatBoSung());
                switch (item.getGroupValue()) {
                    case 1:
                        imageView.setImageResource(R.drawable.bao_hieu);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.man);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.luu_thong);
                        break;
                    case 8:
                        imageView.setImageResource(R.drawable.giay_phep);
                        break;
                    case 16:
                        if(item.getLoaiXe()==2) {
                            imageView.setImageResource(R.drawable.phuong_tien);
                        } else  if(item.getLoaiXe()==1) {
                            imageView.setImageResource(R.drawable.phuong_tien_car);
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
                        if(item.getLoaiXe()==2) {
                            imageView.setImageResource(R.drawable.motorbike);
                        } else  if(item.getLoaiXe()==1) {
                            imageView.setImageResource(R.drawable.car);
                        }
                        break;
                }
                contacts= mDBHelper.getListLoiGoiY(item.getTenLoi(),item.getLoaiXe());
                reViewListView(contacts);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
    public void  reViewListView(  ArrayList<ItemLoiPhat> arr)
    {
        adapterLoi= new AdapterLoi(this,R.layout.item_listview,arr);
        listView.setAdapter(adapterLoi);
    }
}
