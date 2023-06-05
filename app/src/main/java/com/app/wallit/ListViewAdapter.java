package com.app.wallit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ArrayList<list> l_;
    Context context_;
    LayoutInflater LIF;

    public ListViewAdapter(Context context, ArrayList<list> l){
            this.context_=context;
            this.l_=l;
            this.LIF=LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return l_.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LIF.inflate(R.layout.list_item, null);
        TextView amt = (TextView) convertView.findViewById(R.id.amt);
        TextView cat = (TextView) convertView.findViewById(R.id.cat);
        TextView when = (TextView) convertView.findViewById(R.id.when);
        cat.setText(l_.get(position).getCategory());
        amt.setText(l_.get(position).getAmountString());
        when.setText(l_.get(position).getDate());

        return convertView;
    }
}
