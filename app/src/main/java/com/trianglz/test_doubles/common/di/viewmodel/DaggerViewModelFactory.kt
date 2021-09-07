package com.trianglz.test_doubles.common.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class DaggerViewModelFactory @Inject constructor(private val viewModelsMap: Map<Class<*>, @JvmSuppressWildcards ViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var requiredViewModel = viewModelsMap[modelClass]

        if (requiredViewModel == null) {
            requiredViewModel = viewModelsMap.asIterable().firstOrNull()?.value
        }
        requiredViewModel?.let {
            if (modelClass.isAssignableFrom(requiredViewModel::class.java)) {
                return requiredViewModel as T
            }
        }
        throw IllegalArgumentException("Class not found!")
    }


}