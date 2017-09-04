package org.codepond.commitbrowser.common.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import org.codepond.commitbrowser.R;
import org.codepond.commitbrowser.common.glide.GlideApp;

public class ImageUrlAdapter {
    @BindingAdapter("app:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher) // TODO: Replace placeholder
                .into(imageView);
    }
}
