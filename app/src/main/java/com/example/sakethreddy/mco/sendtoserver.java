package com.example.sakethreddy.mco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class sendtoserver extends AppCompatActivity {

    Button sen;
    EditText d1;
    char a[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendtoserver);

        d1 = (EditText)findViewById(R.id.msg);
        sen= (Button)findViewById(R.id.send);
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
                // final String token = sharedPreferences.getString(getString(R.string.FCM_TOKEN)," ");
                sendTokentoServer();
            }
        });
    }

    private void sendTokentoServer(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String token = FirebaseInstanceId.getInstance().getToken();
        Toast.makeText(sendtoserver.this,token,Toast.LENGTH_LONG).show();
        final String email = user.getEmail();
        final String uname = user.getDisplayName();

        Toast.makeText(sendtoserver.this,uname,Toast.LENGTH_LONG).show();

        //Toast.makeText(sendtoserver.this,email,Toast.LENGTH_LONG).show();

        final String message = d1.getText().toString();


        String app_server_url ="https://medco.000webhostapp.com/fcm_insert.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, app_server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("token",token);
                params.put("email",email);
                params.put("name",uname);
                params.put("message",message);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        // MySingleton.getInstance(Home.this).addToRequestque(stringRequest);
    }
}
