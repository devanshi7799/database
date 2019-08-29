package com.example.meet.database_demo_1;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gary on 25-05-2017.
 */

public class update extends AppCompatActivity {

    EditText name,sirname,score;
    Button btnupdate;
    String url,url2,sid,result,sname,sfield,sscore,jname,jsirname,jscore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        name=(EditText)findViewById(R.id.name);
        sirname=(EditText) findViewById(R.id.sirname);
        score=(EditText) findViewById(R.id.score);

        btnupdate=(Button) findViewById(R.id.btnupdate);

        Intent i = getIntent();
        sid=i.getStringExtra("id");

        url="https://nemiproject.000webhostapp.com/select.php?id="+sid;
        new update1().execute();

        Toast.makeText(this,sid, Toast.LENGTH_SHORT).show();

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jname=name.getText().toString();
                jsirname=sirname.getText().toString();
                jscore=score.getText().toString();

                url2 = "https://nemiproject.000webhostapp.com/update.php?name="+jname+"&score="+jscore+"&sirname="+jsirname+"&id="+sid;
                //url2 = "https://clerkliest-excuse.000webhostapp.com/update.php?name="+jname+"&sirname="+jsirname+"&score="+jscore+"&id="+sid;
                new insert().execute();
            }
        });
    }
    class update1 extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            json o = new json();
            result=o.insert(url);
            try {
                JSONObject jo = new JSONObject(result);
                JSONArray ja = jo.getJSONArray("data");
                for(int i=0;i<ja.length();i++)
                {
                    JSONObject jo1 = ja.getJSONObject(i);
                    sname=jo1.getString("name");
                    sfield=jo1.getString("sirname");
                    sscore=jo1.getString("score");


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            name.setText(sname);
            sirname.setText(sfield);
            score.setText(sscore);
        }
    }
    class insert extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            json o = new json();
            result=o.insert(url2);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(update.this,result, Toast.LENGTH_SHORT).show();

        }
    }
}
