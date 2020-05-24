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
    List<AuthUI.IdpConfig> provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        signIn();
    }
    private void signIn(){
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(provider)
                        .setTheme(R.style.MyTheme)
                        .build(),AUTHUI_REQUEST_CODE


        );
    }
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




/*  if (FirebaseAuth.getInstance().getCurrentUser() != null ) {
            startActivity(new Intent(this, MapsActivity.class));
            this.finish();
        }
    }

    public void handleLoginRegister(View view) {

        List<AuthUI.IdpConfig> provider = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

                Intent intent = AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(provider)
                        .setTosAndPrivacyPolicyUrls("https://example.com", "https://example.com")
                        .setLogo(R.drawable.ic_launcher_foreground)
                        .build();
                startActivityForResult(intent, AUTHUI_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AUTHUI_REQUEST_CODE){
            if (resultCode== RESULT_OK){
                // we have sign in the user or new user
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG,"onActivityResult: " + user.getEmail());
                if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {
                    Toast.makeText(this, "Welcome new user", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Welcome back again", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);


            } else {
                // Signing in failed
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null) {
                    Log.d(TAG, "onActivityResult: the user has cancelled the sign in request");
                } else {
                    Log.e(TAG, "onActivityResult: ", response.getError());
                }
            }
        }*/