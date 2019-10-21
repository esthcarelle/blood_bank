package com.example.mylibrary;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mGivers;
    private String[] mCenter;

    public MyAdapter(Context mContext, int resource, String[] mGivers, String[] mCenter) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mGivers = mGivers;
        this.mCenter = mCenter;
    }
    @Override
    public Object getItem(int position) {
        String giver = mGivers[position];
        String center = mCenter[position];
        return String.format("%s \n at: %s", giver, center);
    }

    @Override
    public int getCount() {
        return mGivers.length;
    }
}
