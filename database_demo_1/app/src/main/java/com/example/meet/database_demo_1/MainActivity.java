package com.example.meet.database_demo_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,sirname,score;
    Button insert,retrive;
    String url,sname,ssirname,sscore,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        sirname = (EditText)findViewById(R.id.sirname);
        score = (EditText)findViewById(R.id.score);
        insert = (Button)findViewById(R.id.insert);
        retrive = (Button)findViewById(R.id.retrive);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                sname=name.getText().toString();
                ssirname=sirname.getText().toString();
                sscore=score.getText().toString();

                //url  = "https://anserine-screwdrive.000webhostapp.com/insert.php?Name="+sname+"&Sirname="+ssirname+"&score="+sscore;

                url = "https://nemiproject.000webhostapp.com/insert.php?name="+sname+"&sirname="+ssirname+"&score="+sscore;

                //url = "https://clerkliest-excuse.000webhostapp.com/insert.php?name="+sname+"&sirname="+ssirname+"&score="+sscore;
                new insert().execute();
            }
        });
        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,retrive.class);
                startActivity(i);
            }
        });

    }

    class insert extends AsyncTask<Void,Void,Void>
    {

        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Inserting...");
            pd.setMessage("Please wait....");
            pd.setIndeterminate(false);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            json j = new json();
            result=j.insert(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(pd.isShowing())
            {
                pd.dismiss();
            }
            Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
        }
    }

}
