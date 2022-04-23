package com.wahidabd.mangain.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target

fun ImageView.setImageChapter(image: String){
    Glide.with(this.context)
        .load(image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(Target.SIZE_ORIGINAL)
        .into(this)
}

fun ImageView.setImageGlide(image: String){
    GlideApp.with(this)
        .load(image)
        .override(Target.SIZE_ORIGINAL)
        .into(this)
}

fun Fragment.quickShowToast(msg: String){
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun ImageView.setFlag(value: String){
    when(value){
        Constant.MANGA -> {
            this.load("https://img.icons8.com/fluency/48/000000/japan-circular.png")
        }

        Constant.MANHWA -> {
            this.load("https://img.icons8.com/fluency/48/000000/south-korea-circular.png")
        }

        Constant.MANHUA -> {
            this.load("https://img.icons8.com/fluency/48/000000/china-circular.png")
        }
    }
}