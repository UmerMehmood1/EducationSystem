package com.appsqueeze.springclient.reotrfit;

import com.appsqueeze.springclient.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CourseApi {

    @GET("/course/get-all")
    Call<List<Course>> getAllCourses();

    @GET("/course/{id}")
    Call<Course> getCourseById(@Path("id") Long id);

    @POST("/course/save")
    Call<Course> addCourse(@Body Course course);

    @PUT("/course/update/{id}")
    Call<Course> updateCourse(@Path("id") Long id, @Body Course course);

    @DELETE("/course/delete/{id}")
    Call<Void> deleteCourse(@Path("id") Long id);
}
