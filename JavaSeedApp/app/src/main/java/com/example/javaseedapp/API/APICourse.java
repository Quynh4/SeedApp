package com.example.javaseedapp.API;

import com.example.javaseedapp.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICourse {
    @GET("/api/Course/get")
    Call<List<Course>> getList();
}
