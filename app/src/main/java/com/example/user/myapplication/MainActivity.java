package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText account, passwd, realname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = (EditText)findViewById(R.id.account);
        passwd = (EditText)findViewById(R.id.passwd);
        realname = (EditText)findViewById(R.id.realname);

    }

    public void test1(View view){
        new Thread(){
            @Override
            public void run() {

                try {
                    URL url = new URL("http://120.108.137.125/ltu/ltu01.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = br.readLine();
                    br.close();

                    Log.v("brad", line);

                } catch (Exception e) {
                    Log.v("brad", e.getLocalizedMessage());
                }


            }
        }.start();
    }

    public void test2(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    String urlString = "http://120.108.137.125/ltu/ltu03.php?account="
                            + account.getText() +
                            "&passwd=" + passwd.getText()
                            + "&realname=" + realname.getText();
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = br.readLine();
                    br.close();

                    Log.v("brad", line);

                } catch (Exception e) {
                    Log.v("brad", e.getLocalizedMessage());
                }
            }
        }.start();
    }

    public void test3(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    MultipartUtility mu = new MultipartUtility("http://120.108.137.125/ltu/ltu03.php","", "UTF-8");
                    mu.addFormField("account", account.getText().toString());
                    mu.addFormField("passwd", passwd.getText().toString());
                    mu.addFormField("realname", realname.getText().toString());
                    List<String> ret = mu.finish();
                    for (String line : ret){
                        Log.v("brad", line);
                    }
                } catch (Exception e) {
                    Log.v("brad", e.toString());
                }
            }
        }.start();
    }

    public void test4(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    MultipartUtility mu = new MultipartUtility(
                            "http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx",
                            "", "UTF-8");
                    List<String> ret = mu.finish();
                    for (String line : ret){
                        Log.v("brad", line);
                    }

                }catch(Exception e){
                    Log.v("brad", e.toString());
                }
            }
        }.start();
    }


}
