package com.victor.app193.HttpRequest;

import com.victor.app193.HttpRequest.Response.Notes;
import com.victor.app193.HttpRequest.Request.NoteSchema;
import com.victor.app193.HttpRequest.Response.SQL;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("notes/")
    Call<Notes[]> getNotes();

    @DELETE("notes/{id}")
    Call<SQL> deleteNote(@Path("id") int id);

    @POST("notes/")
    Call<SQL> addNote(@Body NoteSchema noteSchema);

}
