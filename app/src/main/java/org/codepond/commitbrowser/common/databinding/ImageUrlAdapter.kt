package org.codepond.commitbrowser.common.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import org.codepond.commitbrowser.R
import org.codepond.commitbrowser.common.glide.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    GlideApp.with(imageView.context)
            .load(url)
            .fitCenter()
            .placeholder(R.mipmap.ic_launcher) // TODO: Replace placeholder
            .into(imageView)
}
