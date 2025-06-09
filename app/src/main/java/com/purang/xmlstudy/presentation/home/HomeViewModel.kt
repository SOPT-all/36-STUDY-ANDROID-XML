package com.purang.xmlstudy.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.purang.xmlstudy.R
import com.purang.xmlstudy.data.model.home.Item

class HomeViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>> get() = _items

    init {
        val items = listOf(
            Item(R.drawable.series_recruit1),
            Item(R.drawable.series_recruit2),
            Item(R.drawable.series_recruit3),
        )

        _items.value = items
    }
}