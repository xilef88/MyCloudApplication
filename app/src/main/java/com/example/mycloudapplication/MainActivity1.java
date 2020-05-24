package com.example.mycloudapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;

import java.util.List;

public class MainActivity1 extends AppCompatActivity {
    int AUTHUI_REQUEST_CODE = 10001;
    Button btn_loginIntent;
    Button btn_sign_in;

    List<AuthUI.IdpConfig>provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);


        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new  Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);


            }
        });



    }
}
