package com.example.mobileaula_24_1;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class PagInicial extends AppCompatActivity {

    private RecyclerView recyclerViewUserDetail;
    private UserDetailAdapter userDetailAdapter;
    private List<UserDetail> userDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pag_inicial);

        recyclerViewUserDetail = findViewById(R.id.recyclerViewUserDetail);
        recyclerViewUserDetail.setLayoutManager(new LinearLayoutManager(this));
        userDetailList = new ArrayList<>();
        userDetailAdapter = new UserDetailAdapter(userDetailList);
        recyclerViewUserDetail.setAdapter(userDetailAdapter);

        String email = getIntent().getStringExtra("email");
        if (email != null) {
            getUserDetails(email);
        } else {
            Toast.makeText(this, "Email not received.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserDetails(String email) {
        String url = "https://jsonplaceholder.typicode.com/users?email=" + email;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() > 0) {
                                JSONObject userObject = response.getJSONObject(0);
                                String userName = userObject.getString("name");
                                int userId = userObject.getInt("id");
                                getPostAndComments(userId);
                            } else {
                                Toast.makeText(PagInicial.this, "User not found.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PagInicial.this, "Error processing JSON response.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PagInicial.this, "Network error. Check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void getPostAndComments(int userId) {
        String url = "https://jsonplaceholder.typicode.com/posts?userId=" + userId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if (response.length() > 0) {
                                JSONObject postObject = response.getJSONObject(0);
                                String title = postObject.getString("title");

                                String comment = "Sample Comment";

                                UserDetail userDetail = new UserDetail("Name Placeholder", title, comment);
                                userDetailList.add(userDetail);
                                userDetailAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PagInicial.this, "Network error. Check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
