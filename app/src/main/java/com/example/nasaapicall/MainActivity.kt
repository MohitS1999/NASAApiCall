package com.example.nasaapicall

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.nasaapicall.api.NasaApi
import com.example.nasaapicall.databinding.ActivityMainBinding
import com.example.nasaapicall.model.APODModel
import com.example.nasaapicall.ui.MainViewModel
import com.example.nasaapicall.util.UiState
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
        observer()

        viewModel.getData()


    }

    private fun observer() {
                viewModel.getApiData.observe(this){
                    when(it){
                        is UiState.Success -> {
                            binding.date.visibility = View.VISIBLE
                            binding.globeImage.visibility = View.VISIBLE
                            binding.description.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE

                            Log.d(TAG, "observeData: Success ${it.data}")
                            binding.title.text = it.data.title
                            binding.date.text = it.data.date
                            binding.description.text =Editable.Factory.getInstance().newEditable(it.data.explanation)
                            Glide.with(this).load(it.data.url)
                                .fitCenter()
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).error(R.drawable.ic_launcher_background).into(binding.globeImage)

                        }
                        is UiState.Failure -> {
                            Log.d(TAG, "observeData: Failure")
                            Toast.makeText(this,it.error,Toast.LENGTH_LONG).show()

                        }
                        is UiState.Loading -> {
                            Log.d(TAG, "observeData: Loading.... $it")

                        }
                    }
                }
    }



}