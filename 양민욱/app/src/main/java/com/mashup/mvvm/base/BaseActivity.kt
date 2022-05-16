package com.mashup.mvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mashup.mvvm.extensions.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseActivity<out V : BaseViewModel> : AppCompatActivity() {
    protected val viewModel: V by lazy { injectViewModel() }

    abstract fun injectViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeBaseViewModel()
    }

    private fun observeBaseViewModel() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (isLoading) {
                    // show Dialog
                } else {
                    // hide Dialog
                }
            }
        }

        lifecycleScope.launch {
            viewModel.toastMessage.collectLatest { message ->
                showToast(message)
            }
        }
    }
}