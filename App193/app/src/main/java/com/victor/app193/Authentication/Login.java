package com.victor.app193.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.victor.app193.R;

public class Login extends AppCompatActivity {

    Button login, register;
    EditText textoCorreo, textoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegistro);
        textoCorreo = findViewById(R.id.correo);
        textoPassword = findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {login(); }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoExplicito();
            }
        });
    }

    private void login() {
        String correo = textoCorreo.getText().toString();
        String password = textoPassword.getText().toString();
    }

    private void metodoExplicito(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }
}