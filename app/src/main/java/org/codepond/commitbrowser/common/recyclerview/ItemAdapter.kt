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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

typealias OnItemClickListener = (Int) -> Unit

class ItemAdapter(private val items: ObservableList<Item>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder<*>>() {

    private val onListChangedCallback = object : ObservableList.OnListChangedCallback<ObservableList<Item>>() {
        override fun onChanged(items: ObservableList<Item>) {

        }

        override fun onItemRangeChanged(items: ObservableList<Item>, positionStart: Int, itemCount: Int) {

        }

        override fun onItemRangeInserted(items: ObservableList<Item>, positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(items: ObservableList<Item>, fromPosition: Int, toPosition: Int, itemCount: Int) {

        }

        override fun onItemRangeRemoved(items: ObservableList<Item>, positionStart: Int, itemCount: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder<ViewDataBinding>(DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder<*>, position: Int) {
        val item = items[position]
        item.bind(holder, onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layoutId
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onAttachedToRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        items.addOnListChangedCallback(onListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: androidx.recyclerview.widget.RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        items.removeOnListChangedCallback(onListChangedCallback)
    }
}

