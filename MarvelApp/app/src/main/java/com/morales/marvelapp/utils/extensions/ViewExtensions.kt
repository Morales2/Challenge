@file:JvmName("ExtensionsUtils")

package com.morales.marvelapp.utils.extensions

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.morales.marvelapp.R
import com.morales.marvelapp.data.source.api.Image
import com.squareup.picasso.Picasso


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun ImageView.loadImg(image: Image) {
    val imageUrl = "${image.path}.${image.extension}"
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).fit().into(this)
    } else {
        Picasso.with(context).load(imageUrl).fit().into(this)
    }
}
