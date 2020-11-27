package io.github.moh_mohsin.todoapp.util

import androidx.lifecycle.*

class ViewModelFactory(private val viewModelBlock: () -> ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return viewModelBlock() as T
    }
}

inline fun <reified T : ViewModel> ViewModelStoreOwner.getViewModel(noinline viewModelBlock: () -> T) =
    ViewModelProvider(this, ViewModelFactory {
        viewModelBlock()
    }).get(T::class.java)

inline fun <reified T : ViewModel> ViewModelStoreOwner.createViewModelFactory(noinline viewModelBlock: () -> T) =
    ViewModelFactory {
        viewModelBlock()
    }

fun <T> MutableLiveData<T>.toLive() = this as LiveData<T>


inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>): T = f() as T
    }