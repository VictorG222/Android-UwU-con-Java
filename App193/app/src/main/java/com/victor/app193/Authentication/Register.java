package com.victor.app193.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.victor.app193.R;

public class Register extends AppCompatActivity {

    Button login, register;
    EditText textoCorreo, textoPassword, textoPasswordConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.btnLoginRegistro);
        register = findViewById(R.id.btnRegistroRegistro);
        textoCorreo = findViewById(R.id.correoRegistro);
        textoPassword = findViewById(R.id.contraseniaRegistro);
        textoPasswordConfirmar = findViewById(R.id.contraseniaConfirmarRegistro);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {metodoExplicito(); }
        });

//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                metodoExplicito();
//            }
//        });
    }

    private void metodoExplicito(){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}