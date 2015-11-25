package com.example.winbold4.sampleapplicationredpine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


import com.android.redpine.Redpine;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //call a method when button clicked
                //pleaseLogin();
                new mySmallTask().execute();

            }
        });
    }

    public class mySmallTask extends AsyncTask<String, Void, JSONObject> {

        private mySmallTask(){

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            //BaasBox baas = new BaasBox();
            Redpine redpine = new Redpine();
            //JSONObject jsonResponse = redpine.getVersion(params[0], params[1]);
            //JSONObject jsonResponse = redpine.Login(params[0], params[1]);
            JSONObject jsonResponse = redpine.getState();
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            Log.i("Winbold", result.toString());
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(result.toString());
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
