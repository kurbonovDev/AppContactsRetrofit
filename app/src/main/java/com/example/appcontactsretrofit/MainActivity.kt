package com.example.appcontactsretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcontactsretrofit.adapters.ContactAdapter
import com.example.appcontactsretrofit.databinding.ActivityMainBinding
import com.example.appcontactsretrofit.retrofit.ContactsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var contactsApi: ContactsApi
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        retrofit = Retrofit
            .Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://randomuser.me/")
            .build()

        contactsApi = retrofit.create(ContactsApi::class.java)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val item = contactsApi.getAllContacts(25)

                withContext(Dispatchers.Main) {
                    binding.rcView.adapter = ContactAdapter(item.results)
                }
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_LONG).show()
        }
    }


}