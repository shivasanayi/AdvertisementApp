package com.app.addvertisementapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.addvertisementapp.Adapter.GetAddsAdapter;
import com.app.addvertisementapp.Model.GetAddsObject;
import com.app.addvertisementapp.Utils.RecyclerTouchListener;
import com.app.addvertisementapp.Utils.Urls;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;




public class GetAdds extends AppCompatActivity {

    private RecyclerView mAddList;
    private RecyclerView.Adapter mAddListAdapter;
    private RecyclerView.LayoutManager mAddListLayoutManager;
    ArrayList<GetAddsObject> Addlist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_adds);
        Addlist = new ArrayList<>();
        initializeRecyclerView();
        getAdvertisementList();
        mAddList.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mAddList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //
                GetAddsObject game = Addlist.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }




    public void getAdvertisementList() {



        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.GetAdds,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray array = null;
                            array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject product = array.getJSONObject(i);
                                //adding the product to product list
                                GetAddsObject jobsObject = new GetAddsObject(product.getString("id"),product.getString("userId"),
                                        product.getString("text"),product.getString("city"),
                                        product.getString("cellPhoneNumber")
                                        );
                                Addlist.add(jobsObject);
                            }

                            mAddListAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("HttpClient", "error: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(GetAdds.this);
        requestQueue.add(stringRequest);

    }


    @SuppressLint("WrongConstant")
    private void initializeRecyclerView() {
        mAddList = findViewById(R.id.contactlist);
        mAddList.setNestedScrollingEnabled(false);
        mAddList.setHasFixedSize(false);
        mAddListLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        mAddList.setLayoutManager(mAddListLayoutManager);
        mAddListAdapter = new GetAddsAdapter(Addlist);
        mAddList.setAdapter(mAddListAdapter);
    }


}
