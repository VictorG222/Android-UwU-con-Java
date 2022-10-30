package com.victor.app193.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.victor.app193.R;


public class RestorePass extends AppCompatActivity {

    Button restore;
    EditText textoCorreo;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore_pass);

        restore = findViewById(R.id.btnCambiarPass);
        textoCorreo = findViewById(R.id.passCorreoCambiar);


        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restorePass();
            }
        });
    }

    private void restorePass(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = textoCorreo.getText().toString();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RestorePass.this, "Contraseña restablecida" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}