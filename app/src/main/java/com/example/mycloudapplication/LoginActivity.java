package com.example.mycloudapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    int AUTHUI_REQUEST_CODE = 10001;
    Button btn_sign_in;
    Button btn_sign_out;
    List<AuthUI.IdpConfig> provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    //FirebaseUI provider "EMAIL"
        provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        signIn();
    }
    //Sign in intent and RUN
    private void signIn(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(provider)
                        .setTheme(R.style.MyTheme)
                        .build(),AUTHUI_REQUEST_CODE


        );
    }

    // CHECK if SIGN IN is OK OR NOT PRINT EMAIL OF LOGGGED IN
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==AUTHUI_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode==RESULT_OK)
            {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Toast.makeText(this,""+ user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);

            }
        }



    }
}



