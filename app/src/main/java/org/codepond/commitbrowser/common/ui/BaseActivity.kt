package org.codepond.commitbrowser.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewModel, R : ViewDataBinding> : DaggerAppCompatActivity() {
    lateinit var viewModel: T
    lateinit var binding: R

    private var networkErrorSnackBar: NetworkErrorSnackBar? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected abstract val viewModelClass: Class<T>

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
        networkErrorSnackBar = NetworkErrorSnackBar(binding.root)
    }
}
