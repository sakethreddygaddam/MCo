package com.example.sakethreddy.mco;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {
    static String name = "";

    FirebaseAuth mAuth;
     TextView username;
     TextView memail;
     ImageView prof;
    GoogleSignInAccount acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uname = user.getDisplayName();
        String email = user.getEmail();
        //Toast.makeText(this,uname,Toast.LENGTH_LONG).show();
        //Toast.makeText(this,email,Toast.LENGTH_LONG).show();
        username =(TextView)findViewById(R.id.usernam);
        username.setText(uname);
        memail =(TextView)findViewById(R.id.uemail);
        memail.setText(email);
        prof = (ImageView)findViewById(R.id.profileimg);
        Uri uri = user.getPhotoUrl();
        Picasso.with(getApplicationContext())
                .load(uri)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(prof);







    }




}
