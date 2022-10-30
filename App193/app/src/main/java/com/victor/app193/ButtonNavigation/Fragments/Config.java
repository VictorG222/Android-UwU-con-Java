package com.victor.app193.ButtonNavigation.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.victor.app193.Authentication.Login;
import com.victor.app193.R;


public class Config extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_config, container, false);

        Button changePassword = view.findViewById(R.id.btnChangePassword);
        Button deleteAccount = view.findViewById(R.id.btnDeleteAccount);

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAlertDialog(2);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAlertDialog(1);
            }
        });


        return view;
    }

    private void getAlertDialog(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_auth,null);


        EditText email = dialogView.findViewById(R.id.dialogEmail);
        EditText pass = dialogView.findViewById(R.id.dialogPassword);
        EditText newpass = dialogView.findViewById(R.id.dialogNewPassword);
        Button reautenticar = dialogView.findViewById(R.id.btnReAutenticar);

        if (i == 1){
            newpass.setVisibility(View.VISIBLE);
        }

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        reautenticar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                AuthCredential credential = EmailAuthProvider
                        .getCredential(email.getText().toString(), pass.getText().toString());

                if (user !=null) {
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        getOption(i, alertDialog, newpass.getText().toString());
                                    } else {
                                        alertDialog.dismiss();
                                        Toast.makeText(getContext(), "No se pudo :(" + pass, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void getOption(int i, AlertDialog alertDialog, String newpass) {
        if (i == 1){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            user.updatePassword(newpass)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                alertDialog.dismiss();
                                Toast.makeText(getContext(), "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }else{
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    alertDialog.dismiss();
                    Toast.makeText(getContext(), "Adiosss!!!!!", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getContext(), Login.class));
                    getActivity().finish();
                }else{
                    alertDialog.dismiss();
                    Toast.makeText(getContext(), "No se pudo :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }



}