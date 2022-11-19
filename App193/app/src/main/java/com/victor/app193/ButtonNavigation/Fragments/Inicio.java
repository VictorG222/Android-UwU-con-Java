package com.victor.app193.ButtonNavigation.Fragments;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.victor.app193.HttpRequest.API;
import com.victor.app193.HttpRequest.ApiService;
import com.victor.app193.HttpRequest.Request.NoteSchema;
import com.victor.app193.HttpRequest.Response.Notes;
import com.victor.app193.HttpRequest.Response.SQL;
import com.victor.app193.R;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Inicio extends Fragment {

    EditText textoTitulo, textoMensajeEnviado;
    TextView tituloNota, descripcionNota;
    Button botonEnviarMensaje;
    View view;
    API api = new API();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);

//        setNote("Titulo","Mensaje");



        textoTitulo = view.findViewById(R.id.txtTitulo);
        textoMensajeEnviado = view.findViewById(R.id.txtMensajeEnviado);
        tituloNota = view.findViewById(R.id.txtTituloNota);
        descripcionNota = view.findViewById(R.id.txtTituloNota);

        botonEnviarMensaje = view.findViewById(R.id.btnEnviarMensaje);

        obtenerMensaje();



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

//    private void setNote(String sTitle, String sMessage) {
//        try {
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(api.getURL())
//                    .addConverterFactory((GsonConverterFactory.create()))
//                    .build();
//            ApiService apiService = retrofit.create(ApiService.class);
//            NoteSchema noteSchema = new NoteSchema(sTitle, sMessage);
//            Call<SQL> call = apiService.addNote(noteSchema);
//            call.enqueue(new Callback<SQL>() {
//                @Override
//                public void onResponse(Call<SQL> call, Response<SQL> response) {
//                    if(!response.isSuccessful()){
//                        Log.d("notes", "onResponse: " + response.code());
//                        return;
//                    } else{
//                        // Obtenemos la respuesta en un array
//                        SQL notes = response.body();
//
//                        Toast.makeText(getContext(), "Eiiiiiiiii", Toast.LENGTH_SHORT).show();
//                        Log.d("notes", "Eiiiiiiiiii");
//                    }
//                }
//                @Override
//                public void onFailure(Call<SQL> call, Throwable t) {
//                    Log.d("notes", "onFailure: " + t.getMessage());
//                }
//            });
//        } catch (Exception e) {
//            Log.d("notes", "Catch: " + e.getMessage());
//        }
//    }

    private void obtenerMensaje() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(api.getURL())
                    .addConverterFactory((GsonConverterFactory.create()))
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);
            Call<Notes[]> call = apiService.getNotes();
            call.enqueue(new Callback<Notes[]>() {
                @Override
                public void onResponse(Call<Notes[]> call, Response<Notes[]> response) {
                    if(!response.isSuccessful()){
                        Log.d("notes", "onResponse:" + response.code());
                        return;
                    } else{
                        // Obtenemos la respuesta en un array
                        Notes[] notes = response.body();
                        Log.d("getNotas", ""+ notes.length);


                        for (int i=0; i< notes.length; i++){
//                            Notes notes = response.body()[i].getTitulo();
//                            Log.d("getNotas", ""+ notes);
//                            tituloNota.setText(notes[i].getTitulo());
//                            descripcionNota.setText(notes[i].getMensaje());
                            Log.d("getNotas", ""+ notes[i].getTitulo());
                            Log.d("getNotas", ""+ notes[i].getMensaje());


                        }



                    }
                }
                @Override
                public void onFailure(Call<Notes[]> call, Throwable t) {
                    Log.d("notes", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("notes", "Catch: " + e.getMessage());
        }
    }



}