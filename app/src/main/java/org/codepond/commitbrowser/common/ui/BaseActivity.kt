package org.codepond.commitbrowser.common.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import org.codepond.commitbrowser.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<T : ViewModel, R : ViewDataBinding> : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel: T by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }

    protected val binding: R by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView<R>(this, layoutId)
    }

    protected abstract val viewModelClass: Class<T>

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }
}
