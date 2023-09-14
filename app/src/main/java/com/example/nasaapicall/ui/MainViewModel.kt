package com.example.nasaapicall.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasaapicall.model.APODModel

import com.example.nasaapicall.repository.ApiCallRepository
import com.example.nasaapicall.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiCallRepository: ApiCallRepository
) : ViewModel(){


    private val _getApiData = MutableLiveData<UiState<APODModel>>()
    val getApiData:LiveData<UiState<APODModel>>
        get() = _getApiData

    fun getData() : APODModel{
        Log.d(TAG, "getData: ");
        var data = APODModel("","","","","","","")
        viewModelScope.launch (Dispatchers.IO){
             data  = apiCallRepository.getData().body()!!

            Log.d(TAG, "getData: $data")

        }
        Thread.sleep(5000)
        Log.d(TAG, "getData: competed returned $data")
        return data
    }

}