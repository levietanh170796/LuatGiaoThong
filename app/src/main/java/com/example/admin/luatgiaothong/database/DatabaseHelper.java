package com.example.admin.luatgiaothong.database;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.luatgiaothong.ItemBienBao;
import com.example.admin.luatgiaothong.ItemDuongDay;
import com.example.admin.luatgiaothong.ItemLoiPhat;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Admin on 30/9/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "AppGiaoThong.sqlite";
    public static final String DBLOCATION="/data/data/com.example.admin.luatgiaothong/databases/";
    private static final String KEY_ID = "id";
    private static final String KEY_TEN_lOI = "ten_loi";
    private static final String KEY_LOAI_XE = "loai_xe";
    private static final String KEY_NOI_DUNG = "noi_dung";
    private static final String KEY_MUC_PHAT = "muc_phat";
    private static final String KEY_PHAT_BO_SUNG = "phat_bo_sung";
    private static final String KEY_DIEU_KHOAN = "dieu_khoan";
    private static final String KEY_DOI_TUONG = "doi_tuong";
    private static final String KEY_NAME_EN = "name_en";



    private Context context;
    private SQLiteDatabase database;
    public DatabaseHelper(Context context) {
        super(context,DBNAME,null,1);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDatabase(){
        String dbPath = context.getDatabasePath(DBNAME).getPath();
        if (database != null && database.isOpen()){
            return;
        }
        database = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    private void closeDatabse(){
        if( database !=null){
            database.close();
        }
    }
    public  boolean copyDatabase(Activity context){
        try {
            InputStream inputStream =context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff= new  byte[1024];
            int lenght = 0;
            while ((lenght= inputStream.read(buff))>0){
                outputStream.write(buff,0,lenght);

            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.v("MainActivity","BD copied");
            Toast.makeText(context.getApplicationContext(),"copy succes1",Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<ItemLoiPhat> getListLoi(){
        ArrayList<ItemLoiPhat> contactList = new ArrayList<ItemLoiPhat>();
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM XuPhat",null);
        if (cursor.moveToFirst()) {
            do {

                ItemLoiPhat contact = new ItemLoiPhat();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenLoi(cursor.getString(1));
                contact.setLoaiXe(Integer.parseInt(cursor.getString(2)));
                contact.setNoiDung(cursor.getString(3));
                contact.setMucPhat(cursor.getString(4));
                contact.setPhatBoSung(cursor.getString(5));
                contact.setDieuKhoan(cursor.getString(6));
                contact.setGroupValue(Integer.parseInt(cursor.getString(7)));
                contact.setDoiTuong(cursor.getString(8));
                contact.setNameEn(cursor.getString(9));
                // Adding contact to list
                if(contact.getLoaiXe()==1||contact.getLoaiXe()==2||contact.getLoaiXe()==22
                ||contact.getLoaiXe()==33)contactList.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        closeDatabse();
        return contactList;
    }
    // get list duong day nong
    public ArrayList<ItemDuongDay> getListDuongDay(){
        ArrayList<ItemDuongDay> contactList = new ArrayList<ItemDuongDay>();
        openDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM duong_day_nong",null);
        if (cursor.moveToFirst()) {
            do {

                ItemDuongDay contact = new ItemDuongDay();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenDiaChi(cursor.getString(1));
                contact.setSoDienThoai(cursor.getString(2));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        closeDatabse();
        return contactList;
    }
    // getSearch
    public ArrayList<ItemLoiPhat> getListLoiSearch(String word, int id){
        String SQL;
         ArrayList<ItemLoiPhat> contactList = new ArrayList<ItemLoiPhat>();
        openDatabase();
        // xử lý chuỗi nhập vào
        word= word.trim();
        if(word.equals(""))
            return contactList = getListLoiLoaiXe(id);

        ArrayList<String> keyString = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(word);
        int i=0;

        while (st.hasMoreTokens())
        {
                keyString.add(i,st.nextToken());
                i++;
        }
        String[] args =new String[1];
        args[0] = "%"+ word+"%";
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" like ?",args);
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" " +
         //       "like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"+word+"%'",null);
        // i=1 thì có 1 chữ
        if (id==2) {
             SQL ="SELECT * FROM XuPhat WHERE "+KEY_LOAI_XE+" ='"+id+"' and ( "+KEY_TEN_lOI+" like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"
                    +word+"%' OR ( ";
        } else {
            SQL ="SELECT * FROM XuPhat WHERE "+KEY_LOAI_XE+" !=2 and ( "+KEY_TEN_lOI+" like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"
                    +word+"%' OR ( ";
        }

        // thêm  vào sau câu lệnh sql

        for (int j=0; j<i ;j++){
            if(j==0) SQL= SQL+ KEY_NOI_DUNG+ " like '%"+keyString.get(j)+"%'";
            else SQL = SQL + " and "+KEY_NOI_DUNG+ " like '%"+keyString.get(j)+"%'";
        }

        if (id==2) {
            SQL = SQL+ "))";
        } else {
            SQL = SQL+ "))";
        }


        if (word.length()>0) {
            Cursor cursor = database.rawQuery(SQL, null);

            if (cursor.moveToFirst()) {
                do {

                    ItemLoiPhat contact = new ItemLoiPhat();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setTenLoi(cursor.getString(1));
                    contact.setLoaiXe(Integer.parseInt(cursor.getString(2)));
                    contact.setNoiDung(cursor.getString(3));
                    contact.setMucPhat(cursor.getString(4));
                    contact.setPhatBoSung(cursor.getString(5));
                    contact.setDieuKhoan(cursor.getString(6));
                    contact.setGroupValue(Integer.parseInt(cursor.getString(7)));
                    contact.setDoiTuong(cursor.getString(8));
                    contact.setNameEn(cursor.getString(9));
                    // Adding contact to list
                    if(contact.getLoaiXe()==1||contact.getLoaiXe()==2||contact.getLoaiXe()==22
                            ||contact.getLoaiXe()==33) contactList.add(contact);

                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        closeDatabse();
        return contactList;
    }

    // search biển báo
    public ArrayList<ItemBienBao> getListBienSearch(String word, int id){
        ArrayList<ItemBienBao> contactList = new ArrayList<ItemBienBao>();
        openDatabase();
        ArrayList<String> keyString = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(word);
        int i=0;

        while (st.hasMoreTokens())
        {
            keyString.add(i,st.nextToken());
            i++;
        }
        String[] args =new String[1];
        args[0] = "%"+ word+"%";
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" like ?",args);
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" " +
        //       "like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"+word+"%'",null);
        // i=1 thì có 1 chữ
        /*String SQL ="SELECT * FROM bien_bao WHERE "+KEY_LOAI_XE+" ='"+id+"' and "+KEY_TEN_lOI+" like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"
                +word+"%' OR ( ";*/
        String SQL ="SELECT * FROM bien_bao WHERE "+KEY_LOAI_XE+" ='"+id+"' and ( "+KEY_TEN_lOI+" like '%"+word+"%' OR ( ";

        // thêm  vào sau câu lệnh sql

        for (int j=0; j<i ;j++){
            if(j==0) SQL= SQL+ KEY_TEN_lOI+ " like '%"+keyString.get(j)+"%'";
            else SQL = SQL + " and "+KEY_TEN_lOI+ " like '%"+keyString.get(j)+"%'";
        }

        SQL = SQL+ "))";

        if (word.length()>0) {
            Cursor cursor = database.rawQuery(SQL, null);

            if (cursor.moveToFirst()) {
                do {

                    ItemBienBao contact = new ItemBienBao();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setTenBien(cursor.getString(1));
                    contact.setLoaiBien((Integer.parseInt(cursor.getString(2))));

                    contact.setAnhBien(cursor.getString(3));

                    // Adding contact to list
                    contactList.add(contact);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        closeDatabse();
        return contactList;
    }

    // get list search
    public ArrayList<ItemDuongDay> getListDuongDay(String word){
        ArrayList<ItemDuongDay> contactList = new ArrayList<ItemDuongDay>();
        openDatabase();
        ArrayList<String> keyString = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(word);
        int i=0;

        while (st.hasMoreTokens())
        {
            keyString.add(i,st.nextToken());
            i++;
        }
        String[] args =new String[1];
        args[0] = "%"+ word+"%";
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" like ?",args);
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" " +
        //       "like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"+word+"%'",null);
        // i=1 thì có 1 chữ
        /*String SQL ="SELECT * FROM bien_bao WHERE "+KEY_LOAI_XE+" ='"+id+"' and "+KEY_TEN_lOI+" like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"
                +word+"%' OR ( ";*/
        String SQL ="SELECT * FROM duong_day_nong WHERE tentinh like '%"+word+"%' OR ( ";

        // thêm  vào sau câu lệnh sql

        for (int j=0; j<i ;j++){
            if(j==0) SQL= SQL+ "tentinh like '%"+keyString.get(j)+"%'";
            else SQL = SQL + " and tentinh like '%"+keyString.get(j)+"%'";
        }

        SQL = SQL+ ")";

        if (word.length()>0) {
            Cursor cursor = database.rawQuery(SQL, null);

            if (cursor.moveToFirst()) {
                do {

                    ItemDuongDay contact = new ItemDuongDay();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setTenDiaChi(cursor.getString(1));
                    contact.setSoDienThoai(cursor.getString(2));

                    // Adding contact to list
                    contactList.add(contact);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        closeDatabse();
        return contactList;
    }
    // get list loi theo phuong tien

    public ArrayList<ItemLoiPhat> getListLoiLoaiXe(int loaiXe){
        ArrayList<ItemLoiPhat> contactList = new ArrayList<ItemLoiPhat>();
        openDatabase();
        String[] args =new String[1];
        args[0] =  Integer.toString(loaiXe);
        Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_LOAI_XE+" = ?",args);
        if (cursor.moveToFirst()) {
            do {

                ItemLoiPhat contact = new ItemLoiPhat();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenLoi(cursor.getString(1));
                contact.setLoaiXe(Integer.parseInt(cursor.getString(2)));
                contact.setNoiDung(cursor.getString(3));
                contact.setMucPhat(cursor.getString(4));
                contact.setPhatBoSung(cursor.getString(5));
                contact.setDieuKhoan(cursor.getString(6));
                contact.setGroupValue(Integer.parseInt(cursor.getString(7)));
                contact.setDoiTuong(cursor.getString(8));
                contact.setNameEn(cursor.getString(9));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        closeDatabse();
        return contactList;
    }

    // get list bien bao
    public ArrayList<ItemBienBao> getListLoaiBien(int loaiBien){
        ArrayList<ItemBienBao> contactList = new ArrayList<ItemBienBao>();
        openDatabase();
        String[] args =new String[1];
        args[0] =  Integer.toString(loaiBien);
        Cursor cursor = database.rawQuery("SELECT * FROM bien_bao WHERE "+KEY_LOAI_XE+" = ?",args);
        if (cursor.moveToFirst()) {
            do {

                ItemBienBao contact = new ItemBienBao();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTenBien(cursor.getString(1));
                contact.setLoaiBien((Integer.parseInt(cursor.getString(2))));

                contact.setAnhBien(cursor.getString(3));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
            cursor.close();
        }
        closeDatabse();
        return contactList;
    }

    // get goi ý in click activyty
    public ArrayList<ItemLoiPhat> getListLoiGoiY(String word, int id){
        ArrayList<ItemLoiPhat> contactList = new ArrayList<ItemLoiPhat>();
        openDatabase();
        ArrayList<String> keyString = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(word);
        int i=0;

        while (st.hasMoreTokens())
        {
            keyString.add(i,st.nextToken());
            i++;
        }
        String[] args =new String[1];
        args[0] = "%"+ word+"%";
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" like ?",args);
        //Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_TEN_lOI+" " +
        //       "like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"+word+"%'",null);
        // i=1 thì có 1 chữ
        String SQL ="SELECT * FROM XuPhat WHERE "+KEY_LOAI_XE+" ='"+id+"' and "+KEY_TEN_lOI+" like '%"+word+"%' OR "+KEY_NOI_DUNG+" like '%"
                +word+"%' OR ( ";

        // thêm  vào sau câu lệnh sql

        for (int j=0; j<i ;j++){
            if(j==0) SQL= SQL+ KEY_NOI_DUNG+ " like '%"+keyString.get(j)+"%'";
            else SQL = SQL + " and "+KEY_NOI_DUNG+ " like '%"+keyString.get(j)+"%'";
        }

        SQL = SQL+ ")";

        if (word.length()>0) {
            Cursor cursor = database.rawQuery(SQL, null);

            if (cursor.moveToFirst()) {
                do {

                    ItemLoiPhat contact = new ItemLoiPhat();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setTenLoi(cursor.getString(1));
                    contact.setLoaiXe(Integer.parseInt(cursor.getString(2)));
                    contact.setNoiDung(cursor.getString(3));
                    contact.setMucPhat(cursor.getString(4));
                    contact.setPhatBoSung(cursor.getString(5));
                    contact.setDieuKhoan(cursor.getString(6));
                    contact.setGroupValue(Integer.parseInt(cursor.getString(7)));
                    contact.setDoiTuong(cursor.getString(8));
                    contact.setNameEn(cursor.getString(9));
                    // Adding contact to list
                    contactList.add(contact);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        closeDatabse();
        return contactList;
    }
   public  ItemLoiPhat getID(int id){
        openDatabase();
       ItemLoiPhat itemLoiPhat = new ItemLoiPhat();
       String[] args =new String[1];
       args[0] =  Integer.toString(id);
       Cursor cursor = database.rawQuery("SELECT * FROM XuPhat WHERE "+KEY_ID+" = ?",args);
        if (cursor.moveToFirst()) {


            itemLoiPhat.setId(Integer.parseInt(cursor.getString(0)));
            itemLoiPhat.setTenLoi(cursor.getString(1));
            itemLoiPhat.setLoaiXe(Integer.parseInt(cursor.getString(2)));
            itemLoiPhat.setNoiDung(cursor.getString(3));
            itemLoiPhat.setMucPhat(cursor.getString(4));
            itemLoiPhat.setPhatBoSung(cursor.getString(5));
            itemLoiPhat.setDieuKhoan(cursor.getString(6));
            itemLoiPhat.setGroupValue(Integer.parseInt(cursor.getString(7)));
            itemLoiPhat.setDoiTuong(cursor.getString(8));
            itemLoiPhat.setNameEn(cursor.getString(9));

        }
       return itemLoiPhat;
   }

 }
