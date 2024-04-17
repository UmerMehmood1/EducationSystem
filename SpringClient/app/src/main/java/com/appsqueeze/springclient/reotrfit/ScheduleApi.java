package com.appsqueeze.springclient.reotrfit;

import com.appsqueeze.springclient.model.Course;
import com.appsqueeze.springclient.model.Schedule;
import com.appsqueeze.springclient.model.Student;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ScheduleApi {

  @GET("/schedules/get-all")
  Call<List<Schedule>> getAllSchedules();

  @POST("/schedule/save")
  Call<Schedule> saveSchedule(@Body Schedule schedule);

  @GET("/schedule/{id}")
  Call<Schedule> getScheduleById(@Path("id") Long id);

  @PUT("/schedule/update/{id}")
  Call<Schedule> updateSchedule(@Path("id") Long id, @Body Schedule schedule);

  @DELETE("/schedule/delete/{id}")
  Call<Void> deleteSchedule(@Path("id") Long id);
}

