package com.example.bugrepro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugrepro.dummy.DummyContent
import javax.inject.Inject

class PagerItemViewModel @Inject constructor() : ViewModel() {

    private val _listItems
            = MutableLiveData<List<DummyContent.DummyItem>>().apply {
        value = emptyList()
    }
    val listItems: LiveData<List<DummyContent.DummyItem>> = _listItems

    fun refresh(oneOrTwo: Boolean) {
        if(oneOrTwo)
            _listItems.value = DummyContent.ITEMSONE
        else
            _listItems.value = DummyContent.ITEMS
    }
}
