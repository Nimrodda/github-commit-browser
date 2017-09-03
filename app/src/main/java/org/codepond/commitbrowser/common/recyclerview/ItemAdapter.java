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

package org.codepond.commitbrowser.common.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class ItemAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ObservableList<Item> items;

    public ItemAdapter(ObservableList<Item> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder<>(DataBindingUtil.inflate(inflater, viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = items.get(position);
        item.bind(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getLayoutId();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        items.addOnListChangedCallback(onListChangedCallback);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        items.removeOnListChangedCallback(onListChangedCallback);
    }

    private ObservableList.OnListChangedCallback<ObservableList<Item>> onListChangedCallback =
            new ObservableList.OnListChangedCallback<ObservableList<Item>>() {
                @Override
                public void onChanged(ObservableList<Item> items) {

                }

                @Override
                public void onItemRangeChanged(ObservableList<Item> items, int positionStart, int itemCount) {

                }

                @Override
                public void onItemRangeInserted(ObservableList<Item> items, int positionStart, int itemCount) {
                    notifyItemRangeInserted(positionStart, itemCount);
                }

                @Override
                public void onItemRangeMoved(ObservableList<Item> items, int fromPosition, int toPosition, int itemCount) {

                }

                @Override
                public void onItemRangeRemoved(ObservableList<Item> items, int positionStart, int itemCount) {

                }
            };
}

