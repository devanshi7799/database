package com.example.meet.database_demo_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gary on 25-05-2017.
 */

public class adapter extends BaseAdapter {
    Context context;
    ArrayList<pojo> list;
    public adapter(Context context, ArrayList<pojo> list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
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

        LayoutInflater inflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.look,null);

        TextView id = (TextView)v.findViewById(R.id.txtid);
        TextView name = (TextView)v.findViewById(R.id.txtname);
        TextView sirname = (TextView)v.findViewById(R.id.txtsirname);
        TextView score = (TextView)v.findViewById(R.id.txtscore);

        pojo p = (pojo)list.get(position);

        id.setText(p.getId());
        name.setText(p.getName());
        sirname.setText(p.getSirname());
        score.setText(p.getScore());

        return v;
    }
}
