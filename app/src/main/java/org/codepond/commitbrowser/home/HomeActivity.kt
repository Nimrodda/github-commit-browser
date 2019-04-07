/*
 * Copyright 2019 Nimrod Dayan nimroddayan.com
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

package org.codepond.commitbrowser.home

import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.ui.BaseActivity
import org.codepond.commitbrowser.databinding.HomeActivityBinding

class HomeActivity : BaseActivity<HomeViewModel, HomeActivityBinding>() {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    override val layoutId: Int = R.layout.home_activity
}
