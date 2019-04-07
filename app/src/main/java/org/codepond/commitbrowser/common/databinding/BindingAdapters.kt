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

package org.codepond.commitbrowser.common.databinding

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.glide.GlideApp
import kotlin.math.roundToInt

/**
 * Binding adapter that loads an image into an ImageView using Glide
 */
@BindingAdapter("image_url")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
        .load(url)
        .fitCenter()
        .placeholder(R.mipmap.ic_launcher)
        .into(imageView)
}

/**
 * Binding adapter that sets the view height based on two mandatory attributes:
 * @param matchParent Boolean, if set to true, will set the view's height to 'match_parent'
 * @param layoutHeight Dimen resource that. If [matchParent] is false, the value from this resource will be used
 *          to set the height of the view.
 */
@BindingAdapter("match_parent", "layout_height", requireAll = true)
fun setLayoutHeight(view: View, matchParent: Boolean, layoutHeight: Float) {
    val params = view.layoutParams
    if (matchParent) {
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
    } else {
        params.height = layoutHeight.roundToInt()
    }
    view.layoutParams = params
}
