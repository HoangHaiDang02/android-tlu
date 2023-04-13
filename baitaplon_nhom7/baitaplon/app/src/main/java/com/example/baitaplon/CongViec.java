package com.example.baitaplon;

public class CongViec {
    public CongViec(int idCV, String tenCV) {
        IdCV = idCV;
        TenCV = tenCV;
    }

    private int IdCV;
    private String TenCV;

    public int getIdCV() {
        return IdCV;
    }

    public void setIdCV(int idCV) {
        IdCV = idCV;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }


}
