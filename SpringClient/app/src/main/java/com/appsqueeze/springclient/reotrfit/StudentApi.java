package com.appsqueeze.springclient.reotrfit;

import com.appsqueeze.springclient.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApi {

    @GET("/student/get-all")
    Call<List<Student>> getAllStudents();

    @GET("/student/{id}")
    Call<Student> getStudentById(@Path("id") Long id);

    @POST("/student/save")
    Call<Student> addStudent(@Body Student student);

    @PUT("/student/update/{id}")
    Call<Student> updateStudent(@Path("id") Long id, @Body Student student);

    @DELETE("/student/delete/{id}")
    Call<Void> deleteStudent(@Path("id") Long id);
}
