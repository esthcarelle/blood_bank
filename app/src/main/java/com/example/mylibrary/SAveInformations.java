package com.example.mylibrary;

public class SAveInformations {
    private String mName;
    private String mBlood;
    private String mLocation;

    public SAveInformations(String iname,String ilocation,String iblood){
        this.mName=iname;
        this.mLocation=ilocation;
        this.mBlood=iblood;
    }
    public String getmBlood() {
        return mBlood;
    }

    public String getmName() {
        return mName;
    }

    public String getmLocation() {
        return mLocation;
    }
}
