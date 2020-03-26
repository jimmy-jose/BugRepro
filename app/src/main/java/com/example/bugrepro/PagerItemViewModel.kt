package com.example.bugrepro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bugrepro.dummy.DummyContent

class PagerItemViewModel : ViewModel() {
    fun refresh(oneOrTwo: Boolean) {
        if(oneOrTwo)
            _listItems.value = DummyContent.ITEMSONE
        else
            _listItems.value = DummyContent.ITEMS
    }

    private val _listItems
            = MutableLiveData<List<DummyContent.DummyItem>>().apply {
        value = emptyList()
    }
    val listItems: LiveData<List<DummyContent.DummyItem>> = _listItems
}
