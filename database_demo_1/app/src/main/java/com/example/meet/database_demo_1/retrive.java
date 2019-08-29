package com.example.meet.database_demo_1;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gary on 25-05-2017.
 */

public class retrive extends AppCompatActivity {

    ListView lst;
    ArrayList<pojo> list;
    String url,result;

    ArrayList<String>arrayList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        lst=(ListView)findViewById(R.id.lst);

        //url = "https://studentdemo36.000webhostapp.com/retrive.php";

        url = "https://nemiproject.000webhostapp.com/retrive.php";

        new retrive1().execute();

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pojo p = (pojo)list.get(position);

                final String sid = p.getId();

                AlertDialog.Builder builder = new AlertDialog.Builder(retrive.this);
                builder.setMessage("You want Delete Or Update ?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(retrive.this,update.class);
                        i.putExtra("id",sid);
                        startActivity(i);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        url="https://nemiproject.000webhostapp.com/delete.php?id="+sid;
                        new delete().execute();
                    }
                });
                builder.show();
            }
        });
    }
    class delete extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(retrive.this);
            pd.setTitle("Deleting...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json o = new json();
            result=o.insert(url);
            list=new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1 = ja.getJSONObject(i);
                    pojo p = new pojo();
                    p.setId(jo1.getString("id"));
                    p.setName(jo1.getString("name"));
                    p.setSirname(jo1.getString("sirname"));
                    p.setScore(jo1.getString("score"));
                    list.add(p);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter adp = new adapter(retrive.this,list);
        lst.setAdapter(adp);
        if(pd.isShowing())
        {
            pd.dismiss();
        }
        url="https://nemiproject.000webhostapp.com/retrive.php";
        new retrive1().execute();
    }
}
    class retrive1 extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(retrive.this);
            pd.setTitle("Retriving...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            json o = new json();
            result=o.insert(url);
            list=new ArrayList<>();
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");

                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1 = ja.getJSONObject(i);
                    pojo p = new pojo();
                    p.setId(jo1.getString("id"));
                    p.setName(jo1.getString("name"));
                    p.setSirname(jo1.getString("sirname"));
                    p.setScore(jo1.getString("score"));
                    list.add(p);
                    //uname[i] = list;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter adp = new adapter(retrive.this,list);
            lst.setAdapter(adp);


            if(pd.isShowing())
            {
                pd.dismiss();
            }
        }
    }
}
