package org.codepond.commitbrowser.common.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.epoxy.ViewStateEpoxyController
import org.codepond.commitbrowser.di.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<S, T : BaseViewModel<S>, B : ViewDataBinding> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var controller: ViewStateEpoxyController<S>

    protected val viewModel: T by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
    }

    protected lateinit var binding: B

    protected abstract val viewModelClass: Class<T>

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.viewState.observe(this, Observer { state ->
            if (state is ViewState.Error) {
                showError(state.throwable)
            }
            Timber.d("Updating controller")
            controller.setData(state)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    private fun showError(error: Throwable) {
        Snackbar.make(binding.root, R.string.no_internet_connection, Snackbar.LENGTH_LONG).show()
    }
}
