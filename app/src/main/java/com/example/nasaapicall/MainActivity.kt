package com.example.nasaapicall

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nasaapicall.api.NasaApi
import com.example.nasaapicall.databinding.ActivityMainBinding
import com.example.nasaapicall.model.APODModel
import com.example.nasaapicall.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val TAG = "MainActivity"
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: calling getdata function")
        val data = viewModel.getData()
        Log.d(TAG, "onCreate: before sleep $data")
        Thread.sleep(7000)
        Log.d(TAG, "onCreate: after sleep $data")
        binding.title.text = data.title
        binding.date.text = data.date
        binding.description.text =Editable.Factory.getInstance().newEditable(data.explanation)
        Glide.with(this).load(data.url)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(R.drawable.ic_launcher_background).into(binding.globeImage)

    }


}