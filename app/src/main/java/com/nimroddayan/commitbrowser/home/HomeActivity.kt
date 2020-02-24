/*
 * Copyright 2020 Nimrod Dayan nimroddayan.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nimroddayan.commitbrowser.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nimroddayan.commitbrowser.R
import com.nimroddayan.commitbrowser.common.ui.BaseActivity
import com.nimroddayan.commitbrowser.databinding.HomeActivityBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class HomeActivity : BaseActivity<HomeViewModel, HomeActivityBinding>() {
    @Inject
    internal lateinit var navigator: HomeNavigator

    private lateinit var navHostFragment: NavHostFragment
    override val viewModel: HomeViewModel by viewModels()
    override val layoutId: Int = R.layout.home_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.toolbar.setupWithNavController(navHostFragment.navController)
        lifecycleScope.launchWhenStarted {
            navigator.navigationEventFlow.collect { request ->
                request.navigate(navHostFragment.navController)
            }
        }
    }
}
