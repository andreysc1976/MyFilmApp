package ru.a_party.myfilmapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.a_party.myfilmapp.model.LoadData
import ru.a_party.myfilmapp.model.LoadState

class MainViewModel : ViewModel() {
    val liveDataToObserve: MutableLiveData<LoadState> = MutableLiveData()


}