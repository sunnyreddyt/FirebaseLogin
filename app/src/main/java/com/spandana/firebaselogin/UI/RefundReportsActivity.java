package com.spandana.firebaselogin.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.spandana.firebaselogin.R;
import com.spandana.firebaselogin.adapters.BankDetailsAdapter;
import com.spandana.firebaselogin.adapters.RefundReportsAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class RefundReportsActivity extends AppCompatActivity {

    RecyclerView usersRecycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_report_details);

        usersRecycleView = (RecyclerView) findViewById(R.id.usersRecycleView);


       /* if (abUtil.isConnectingToInternet()) {
            new Userslist().execute();
            abUtil.showSmileProgressDialog(AsyncActivity.this);
        }*/
        new RefundReports().execute();

    }


    private class RefundReports extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            String str = postData();
            return str;
        }

        protected void onPostExecute(String json) {

            try {
                // abUtil.dismissSmileProgressDialog();
                if (json.length() > 0) {

                    JSONObject jsonObjectMain;

                    try {

                        jsonObjectMain = new JSONObject(json);
                        String path = "";
                        Log.e("jsonObjectMain", "" + jsonObjectMain);

                        if (jsonObjectMain.has("Code") && jsonObjectMain.getString("Code").equalsIgnoreCase("200")) {

                            String responseMessage = jsonObjectMain.getString("Response");

                            JSONArray jsonArray = jsonObjectMain.getJSONArray("topup_reports");

                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RefundReportsActivity.this, LinearLayoutManager.VERTICAL, false);
                            usersRecycleView.setLayoutManager(linearLayoutManager);
                            usersRecycleView.setHasFixedSize(true);
                            RefundReportsAdapter refundReportsAdapter = new RefundReportsAdapter(RefundReportsActivity.this, jsonArray);
                            usersRecycleView.setAdapter(refundReportsAdapter);

                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        @SuppressWarnings("deprecation")
        public String postData() {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost("https://www.esytopup.co.in/mobile_service/Prepaid_Recharge/Get_Bank_Details");

            String json = "";
            try {

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                /*            nameValuePairs.add(new BasicNameValuePair("sign", "2"));*/

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity httpEntity = response.getEntity();
                InputStream is = httpEntity.getContent();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                is.close();
                json = sb.toString();
                Log.v("objJsonMain", "" + json);

            } catch (ClientProtocolException e) {
                e.printStackTrace();
                // TODO Auto-generated catch block
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;
        }
    }

}