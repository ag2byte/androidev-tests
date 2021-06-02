package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    String[] names;
    int[] songIds;

    public ItemAdapter(Context c, String[] names,int[] songIds)
    {
        this.names=names;
        this.songIds=songIds;
        mInflator=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return songIds.length;
    }

    @Override
    public Object getItem(int position) {
        return songIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=mInflator.inflate(R.layout.song_lists,null);
        TextView nameTV=(TextView) v.findViewById(R.id.SongNameTV);
        nameTV.setText(names[position]);
        return v;
    }
}
