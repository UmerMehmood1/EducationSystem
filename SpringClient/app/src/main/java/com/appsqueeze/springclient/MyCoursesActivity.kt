package com.appsqueeze.springclient

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.appsqueeze.springclient.adapters.CourseAdapter
import com.appsqueeze.springclient.model.Course
import com.appsqueeze.springclient.reotrfit.CourseApi
import com.appsqueeze.springclient.reotrfit.RetrofitService
import com.genuinecoder.springclient.databinding.ActivityMyCoursesBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyCoursesActivity : AppCompatActivity() {
    lateinit var binding : ActivityMyCoursesBinding
    private lateinit var courseAdapter: CourseAdapter
    private val retrofitService = RetrofitService()
    private val apiService: CourseApi = retrofitService.retrofit.create(CourseApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMyCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        setCourseAdapter()
    }
    private fun setCourseAdapter(){
        val refinedCourses = ArrayList<Course>()
        courseAdapter = CourseAdapter(this@MyCoursesActivity, refinedCourses)
        binding.courseRecyclerView.adapter = courseAdapter
        val layoutManager = LinearLayoutManager(this@MyCoursesActivity)
        binding.courseRecyclerView.layoutManager = layoutManager

    }
    private fun setListeners(){
        binding.courseName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val call: Call<MutableList<Course>>? = apiService.allCourses
                call?.enqueue(object : Callback<MutableList<Course>> {
                    override fun onResponse(call: Call<MutableList<Course>>, response: Response<MutableList<Course>>) {
                        if (response.isSuccessful) {
                            val courses = response.body()
                            val refinedCourses = ArrayList<Course>()
                            for (course in courses!!){
                                if (course.name.contains(p0.toString())){
                                    refinedCourses.add(course)
                                }
                            }
                            courseAdapter.setCourses(refinedCourses)
                        } else {
                            Snackbar.make(binding.root, "Please check internet connection or Server is down", Snackbar.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(p0: Call<MutableList<Course>>, p1: Throwable) {
                            Snackbar.make(binding.root, "Please check internet connection or Server is down", Snackbar.LENGTH_LONG).show()
                    }

                })

            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}