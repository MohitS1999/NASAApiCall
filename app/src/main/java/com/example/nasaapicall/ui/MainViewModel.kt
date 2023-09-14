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
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "MainViewModel"
@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiCallRepository: ApiCallRepository
) : ViewModel(){


    private val _getApiData = MutableLiveData<UiState<APODModel>>()
    val getApiData:LiveData<UiState<APODModel>>
        get() = _getApiData

    fun getData() {
        _getApiData.value = UiState.Loading
        Log.d(TAG, "getData: ");
        viewModelScope.launch (Dispatchers.IO){
            try {
                _getApiData.postValue(UiState.Success( apiCallRepository.getData().body()!!))
            } catch(e: HttpException){
                _getApiData.value = UiState.Failure(e.message())

            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                UiState.Failure("Please check your network connection")
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                UiState.Failure("Something went wrong")
            }


    }
    }



}