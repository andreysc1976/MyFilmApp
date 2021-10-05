package ru.a_party.myfilmapp.model

import android.graphics.Bitmap

class ImageLoader:Thread() {
    override fun run() {

    }

    interface OnImageLoad{
        fun onLoadImage(image:Bitmap)
    }
}