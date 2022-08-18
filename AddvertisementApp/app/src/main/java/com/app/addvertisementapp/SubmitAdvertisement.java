package com.app.addvertisementapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.addvertisementapp.Utils.CustomRequest;
import com.app.addvertisementapp.Utils.SharedPref;
import com.app.addvertisementapp.Utils.Urls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SubmitAdvertisement extends AppCompatActivity {
    EditText City, CellPhone, AddText;
    Button Submit;
    SharedPref sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_add);
        sf = new SharedPref(getApplicationContext());
        City = findViewById(R.id.City);
        CellPhone = findViewById(R.id.CellPhone);
        AddText = findViewById(R.id.AddText);

        Submit = findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!City.getText().toString().equals("") &&
                        !AddText.getText().toString().equals("") &&
                        !CellPhone.getText().toString().equals("")) {
                    SubmitAdd();
                } else {
                    Toast.makeText(getApplicationContext(), "لطفا همه ی فیلد هارا به درستی وارد کنید", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void SubmitAdd() {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", sf.getUserId());
            jsonObject.put("text", AddText.getText().toString());
            jsonObject.put("city", City.getText().toString());
            jsonObject.put("cellPhoneNumber", CellPhone.getText().toString());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomRequest customRequest = new CustomRequest(Request.Method.POST, Urls.SubmitAdvertisement, headers, jsonObject, response -> {
            String Message = "";
            String UserId = "";
            try {
                Log.e("Response", response.toString());
                Message = response.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (Message.equals("Data Saved")) {
                Toast.makeText(SubmitAdvertisement.this, "آگهی شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else {
                Toast.makeText(SubmitAdvertisement.this, "مشکلی در ثبت اطلاعات پیش آمده لطفا مجددا بررسی نمایید", Toast.LENGTH_SHORT).show();
            }

        }, error -> {
            Log.e("jsonError", String.valueOf(error));
        });
        RequestQueue queue = Volley.newRequestQueue(SubmitAdvertisement.this);
        queue.add(customRequest);
    }

}