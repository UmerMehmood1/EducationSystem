package com.appsqueeze.springclient

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.appsqueeze.springclient.model.Student
import com.appsqueeze.springclient.reotrfit.RetrofitService
import com.appsqueeze.springclient.reotrfit.StudentApi
import com.genuinecoder.springclient.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(binding.root.id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
        }
    private fun setListeners(){
        binding.loginButton.setOnClickListener{
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if (username.isEmpty() || password.isEmpty()){
                Snackbar.make(binding.root,"Please Enter username or password", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val isUserAuthentic = checkIfUserExists(username, password)
            if (isUserAuthentic){
                Snackbar.make(binding.root,"Logging in", Snackbar.LENGTH_LONG).show()
                startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
            }
            else{
                Snackbar.make(binding.root,"Invalid Credentials", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    private fun checkIfUserExists(username: String, password: String): Boolean{
        val retrofitService = RetrofitService()
        val apiService: StudentApi = retrofitService.retrofit.create(StudentApi::class.java)
        val call: Call<MutableList<Student>>? = apiService.allStudents
        var isCredentialCorrect = false
        call!!.enqueue(object : Callback<MutableList<Student>>{
            override fun onResponse(p0: Call<MutableList<Student>>, p1: Response<MutableList<Student>>) {
                if (p0.isExecuted){
                    val list = p1.body()
                    for (students in list!!){
                        isCredentialCorrect = (students.username == username && students.password == password)
                        break
                    }
                }
            }

            override fun onFailure(p0: Call<MutableList<Student>>, p1: Throwable) {
                isCredentialCorrect = false
            }

        })
        return isCredentialCorrect
    }
}