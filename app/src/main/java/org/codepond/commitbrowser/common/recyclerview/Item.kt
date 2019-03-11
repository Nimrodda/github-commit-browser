/*
 * Copyright 2017 Nimrod Dayan CodePond.org
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codepond.commitbrowser.common.recyclerview

import android.support.annotation.LayoutRes

import com.android.databinding.library.baseAdapters.BR

abstract class Item {

    @get:LayoutRes
    abstract val layoutId: Int

    open fun bind(viewHolder: ViewHolder<*>, onItemClickListener: OnItemClickListener) {
        //viewHolder.binding.setVariable(BR.itemClickListener, onItemClickListener)
        bind(viewHolder)
    }

    open fun bind(viewHolder: ViewHolder<*>) {
        viewHolder.binding.executePendingBindings()
    }
}
