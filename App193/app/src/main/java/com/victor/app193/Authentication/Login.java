package com.victor.app193.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.victor.app193.ButtonNavigation.Home;
import com.victor.app193.R;

public class Login extends AppCompatActivity {

    Button log, register, restorePass;
    EditText textoCorreo, textoPassword;
    Switch boolPass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        log = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegistro);
        restorePass = findViewById(R.id.btnChangePass);
        textoCorreo = findViewById(R.id.LogCorreo);
        textoPassword = findViewById(R.id.LogPass);
        boolPass = findViewById(R.id.stwRemember);
        mAuth = FirebaseAuth.getInstance();

        SharedPreferences sharedPref = this.getSharedPreferences("userInfo",Context.MODE_PRIVATE);

        Boolean guardarPass = sharedPref.getBoolean("uCheck",false);
        String uEmail = sharedPref.getString("uEmail",  "");
        String uPass = sharedPref.getString("uPass",  "");

        if (guardarPass){
            textoCorreo.setText(uEmail);
            textoPassword.setText(uPass);
            boolPass.setChecked(guardarPass);
        }else{
            boolPass.setChecked(guardarPass);
        }


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });

        restorePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restorePass();
            }
        });
    }

    private void log() {
        String email = textoCorreo.getText().toString();
        String pass = textoPassword.getText().toString();
        Boolean guardarPass = boolPass.isChecked();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginUI(user, guardarPass, pass, email);
                        } else {
                            // If sign in fails, display a message to the user.
                            loginUI(null, guardarPass, pass, email);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void loginUI(FirebaseUser user, Boolean guardarPass, String pass, String email) {
        if (user != null){
            if (guardarPass){
                SharedPreferences sharedPref = this.getSharedPreferences(
                        "userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("uPass", pass);
                editor.putString("uEmail", email);
                editor.putBoolean("uCheck", guardarPass);
                editor.apply();
            }else{
                SharedPreferences sharedPref = this.getSharedPreferences(
                        "userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("uPass", "");
                editor.putString("uEmail", "");
                editor.putBoolean("uCheck", guardarPass);
                editor.apply();
            }

            goToHome();
        }
    }

    private void metodoExplicito(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }

    private void restorePass(){
        Intent intent = new Intent(Login.this, RestorePass.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToHome();
        }
    }

    private void goToHome() {
        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }

}