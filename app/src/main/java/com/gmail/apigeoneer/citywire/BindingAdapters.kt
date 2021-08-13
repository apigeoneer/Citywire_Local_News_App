package com.gmail.apigeoneer.citywire

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("urlToImage")
fun bindImage(view: ImageView, imgUrl: String?) {
    Glide.with(view.context)
        .load(imgUrl)
        .into(view)
        .apply { RequestOptions()
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_error)
        }
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    url?.let {
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(view.context)
            .load(imgUri)
            .apply { RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_error)
            }
            .into(view)
    }
}