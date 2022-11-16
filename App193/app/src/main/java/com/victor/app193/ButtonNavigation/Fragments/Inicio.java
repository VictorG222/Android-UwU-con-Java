package com.victor.app193.ButtonNavigation.Fragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;



import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.victor.app193.HttpRequest.API;
import com.victor.app193.HttpRequest.ApiService;
import com.victor.app193.HttpRequest.Request.NoteSchema;
import com.victor.app193.HttpRequest.Response.Notes;
import com.victor.app193.HttpRequest.Response.SQL;
import com.victor.app193.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inicio extends Fragment {

    EditText textoTitulo, textoMensajeEnviado;
    Button botonEnviarMensaje;
    View view;
    API api = new API();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        setNote("Titulo","Mensaje");



        textoTitulo = view.findViewById(R.id.txtTitulo);
        textoMensajeEnviado = view.findViewById(R.id.txtMensajeEnviado);
        botonEnviarMensaje = view.findViewById(R.id.btnEnviarMensaje);





        botonEnviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensaje();
            }
        });


        return view;


    }

    private void enviarMensaje() {
        String tlt = textoTitulo.getText().toString();
        String msj = textoMensajeEnviado.getText().toString();
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getURL())
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            NoteSchema noteSchema = new NoteSchema(tlt, msj);
            Call<SQL> call = apiService.addNote(noteSchema);
            call.enqueue(new Callback<SQL>() {
                @Override
                public void onResponse(Call<SQL> call, Response<SQL> response) {
                    if(!response.isSuccessful()){
                        Log.d("notes", "onResponse: " + response.code());
                        return;
                    } else{
                        // Obtenemos la respuesta en un array
                        SQL notes = response.body();

                        Toast.makeText(getContext(), "Eiiiiiiiii", Toast.LENGTH_SHORT).show();
                        Log.d("notes", "Eiiiiiiiiii");
                    }
                }
                @Override
                public void onFailure(Call<SQL> call, Throwable t) {
                    Log.d("notes", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("notes", "Catch: " + e.getMessage());
        }
    }

    private void setNote(String sTitle, String sMessage) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getURL())
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            NoteSchema noteSchema = new NoteSchema(sTitle, sMessage);
            Call<SQL> call = apiService.addNote(noteSchema);
            call.enqueue(new Callback<SQL>() {
                @Override
                public void onResponse(Call<SQL> call, Response<SQL> response) {
                    if(!response.isSuccessful()){
                        Log.d("notes", "onResponse: " + response.code());
                        return;
                    } else{
                        // Obtenemos la respuesta en un array
                        SQL notes = response.body();

                        Toast.makeText(getContext(), "Eiiiiiiiii", Toast.LENGTH_SHORT).show();
                        Log.d("notes", "Eiiiiiiiiii");
                    }
                }
                @Override
                public void onFailure(Call<SQL> call, Throwable t) {
                    Log.d("notes", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("notes", "Catch: " + e.getMessage());
        }
    }
}