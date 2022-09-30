package com.victor.app193.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.victor.app193.R;

public class Register extends AppCompatActivity {

    Button login, register;
    EditText textoCorreo, textoPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = findViewById(R.id.btnLoginRegistro);
        register = findViewById(R.id.btnRegistroRegistro);
        textoCorreo = findViewById(R.id.correoRegistro);
        textoPassword = findViewById(R.id.contraseniaRegistro);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {metodoExplicito(); }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });
    }

    private void signup() {
        String email = textoCorreo.getText().toString();
        String pass = textoPassword.getText().toString();

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

        if (pass.length() <= 6){
            Toast.makeText(this, "Nonononononono!!!!! "+ pass, Toast.LENGTH_SHORT).show();
        }
        boolean validPassword = isValidPassword(pass,regex);
        Toast.makeText(this, validPassword + " " + pass, Toast.LENGTH_SHORT).show();



    }

    private void metodoExplicito(){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }

    private static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}