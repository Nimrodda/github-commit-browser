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
