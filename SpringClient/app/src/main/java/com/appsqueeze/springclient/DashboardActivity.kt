package com.appsqueeze.springclient

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appsqueeze.springclient.adapters.CourseAdapter
import com.appsqueeze.springclient.model.Course
import com.appsqueeze.springclient.reotrfit.CourseApi
import com.appsqueeze.springclient.reotrfit.RetrofitService
import com.genuinecoder.springclient.databinding.ActivityDashboardBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDashboardBinding
    private val retrofitService = RetrofitService()
    private val apiService: CourseApi = retrofitService.retrofit.create(CourseApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        setCourseAdapter()
    }
    private fun setListeners(){
        binding.myCourseButton.setOnClickListener{
            startActivity(Intent(this@DashboardActivity, MyCoursesActivity::class.java))
        }
    }
    private fun setCourseAdapter(){
        val call: Call<MutableList<Course>>? = apiService.allCourses
        call?.enqueue(object : Callback<MutableList<Course>> {
            override fun onResponse(call: Call<MutableList<Course>>, response: Response<MutableList<Course>>) {
                if (response.isSuccessful) {
                    val courses = response.body()
                    courses?.let {
                        // Update the adapter with the new data
                        val courseAdapter = CourseAdapter(this@DashboardActivity, it)
                        binding.courseRecyclerView.adapter = courseAdapter
                        val layoutManager = LinearLayoutManager(this@DashboardActivity)
                        binding.courseRecyclerView.layoutManager = layoutManager
                    }
                } else {
                    Snackbar.make(binding.root, "Please check internet connection or Server is down", Snackbar.LENGTH_LONG).show()
                }
            }
            override fun onFailure(p0: Call<MutableList<Course>>, p1: Throwable) {
                Snackbar.make(binding.root, "Please check internet connection or Server is down", Snackbar.LENGTH_LONG).show()
            }
        })
    }
}