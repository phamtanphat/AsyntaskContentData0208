package com.ptp.phamtanphat.asyntaskcontentdata0208;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    Button btngetdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngetdata = (Button) findViewById(R.id.buttongetdata);
        btngetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetDataContent().execute("https://khoapham.vn/");
            }
        });
    }
    private class GetDataContent extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            Log.d("BBB",s);
            super.onPostExecute(s);
        }
        private String docNoiDung_Tu_URL(String theUrl){
            StringBuilder content = new StringBuilder();
            try    {
                // create a url object
                URL url = new URL(theUrl);

                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();

                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;

                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line + "\n");
                }
                bufferedReader.close();
            }
            catch(Exception e)    {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
}
