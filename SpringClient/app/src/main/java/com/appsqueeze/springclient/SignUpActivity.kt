package com.appsqueeze.springclient

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsqueeze.springclient.model.Student
import com.appsqueeze.springclient.reotrfit.CourseApi
import com.appsqueeze.springclient.reotrfit.RetrofitService
import com.appsqueeze.springclient.reotrfit.StudentApi
import com.genuinecoder.springclient.R
import com.genuinecoder.springclient.databinding.ActivitySignUpBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    private val retrofitService = RetrofitService()
    private val apiService: StudentApi = retrofitService.retrofit.create(StudentApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
    }

    private fun setListeners() {
        binding.signupButton.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (username.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "Please Enter username or password", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val call: Call<Student>? = apiService.addStudent(Student(0,username,password))
            call!!.enqueue(object : Callback<Student> {
                override fun onResponse(p0: Call<Student>, p1: Response<Student>) {
                    Snackbar.make(binding.root, "Registered Successfully, ${p1.body()?.username}", Snackbar.LENGTH_LONG).show()
                }

                override fun onFailure(p0: Call<Student>, p1: Throwable) {
                Snackbar.make(binding.root, "Server is down at the moment. Please try again later.", Snackbar.LENGTH_LONG).show()
                }
            })
        }
    }
}