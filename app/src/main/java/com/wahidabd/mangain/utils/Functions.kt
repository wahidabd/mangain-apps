package com.wahidabd.mangain.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target

fun circularProgress(context: Context) : CircularProgressDrawable{
    val circular = CircularProgressDrawable(context)
    circular.strokeWidth = 8f
    circular.centerRadius = 40f
    circular.start()

    return circular
}

fun ImageView.setImageChapter(image: String, circular: CircularProgressDrawable){
    Glide.with(this.context)
        .load(image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(Target.SIZE_ORIGINAL)
        .placeholder(circular)
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