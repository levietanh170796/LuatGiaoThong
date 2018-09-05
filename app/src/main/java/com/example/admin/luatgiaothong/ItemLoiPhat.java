package com.example.admin.luatgiaothong;

/**
 * Created by Admin on 30/9/2016.
 */
public class ItemLoiPhat  {
    public int id;
    public String tenLoi;
    public int loaiXe;
    public String noiDung;
    public String mucPhat;
    public String phatBoSung;
    public String dieuKhoan;
    public int groupValue;

    public int getGroupValue() {
        return groupValue;
    }

    public void setGroupValue(int groupValue) {
        this.groupValue = groupValue;
    }

    public String doiTuong;
    public String nameEn;


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getDieuKhoan() {
        return dieuKhoan;
    }

    public void setDieuKhoan(String dieuKhoan) {
        this.dieuKhoan = dieuKhoan;
    }



    public int getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(int loaiXe) {
        this.loaiXe = loaiXe;
    }

    public ItemLoiPhat(){

    }

    public ItemLoiPhat(int id, String tenLoi, String noiDung, String mucPhat, int loaiXe,String phatBoSung ) {
        this.id = id;
        this.tenLoi = tenLoi;
        this.noiDung = noiDung;
        this.mucPhat = mucPhat;
        this.loaiXe = loaiXe;
        this.phatBoSung = phatBoSung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getMucPhat() {
        return mucPhat;
    }

    public void setMucPhat(String mucPhat) {
        this.mucPhat = mucPhat;
    }
    public String getPhatBoSung() {
        return phatBoSung;
    }

    public void setPhatBoSung(String phatBoSung) {
        this.phatBoSung = phatBoSung;
    }
}
