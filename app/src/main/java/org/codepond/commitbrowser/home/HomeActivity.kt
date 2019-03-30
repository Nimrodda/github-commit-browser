package org.codepond.commitbrowser.home

import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.databinding.HomeActivityBinding

class HomeActivity : BaseActivity<HomeViewModel, HomeActivityBinding>() {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    override val layoutId: Int = R.layout.home_activity
}
