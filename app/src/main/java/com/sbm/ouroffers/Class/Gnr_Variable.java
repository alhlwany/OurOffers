package com.sbm.ouroffers.Class;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;

public class Gnr_Variable  extends Application {

    private String pub_vr_comp,vr_delivery_val,Uphone="PHONE",Upass="PASSWORD";
    private String lang="ar",Gnr_vr_userType="مندوب",Gnr_vr_userName="";

    public String getUserType() {
        return Gnr_vr_userType;
    }
    public void setUserType(String USER) {
        this.Gnr_vr_userType = USER;
    }

    public String getUserName() {
        return Gnr_vr_userName;
    }
    public void setUserName(String USER) {
        this.Gnr_vr_userName = USER;
    }

    public String getCompany() {
        return pub_vr_comp;
    }
    public void setCompany(String COMPANY) {
        this.pub_vr_comp = COMPANY;
    }

    public String getDeliveryVal() {
        return vr_delivery_val;
    }
    public void setDeliveryVal(String VALUE) {
        this.vr_delivery_val = VALUE;
    }

    public String getlang() {
        return lang;
    }
    public void setlang(String Language) {
        this.lang = Language;
    }

    public String getUphone() {
        return Uphone;
    }
    public void setUphone(String PHONE) {
        this.Uphone = PHONE;
    }

    public String getUpass() {
        return Upass;
    }
    public void setUpass(String PASSWORD) {
        this.Upass = PASSWORD;
    }
}
