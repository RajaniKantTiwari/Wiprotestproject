package com.wipro.util

import android.content.Context
import android.util.DisplayMetrics
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object UIUtils {
    fun dpToPx(dp: Int,context: Context): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }
    fun setImageIntoView(context: Context, imageUrl: String?, imageView: ImageView, cornerRadius : Int = 1 ) {
        Glide.with(context)
            .load(imageUrl)
            .apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(
                        dpToPx(
                            cornerRadius,
                            context
                        )
                    )
                )
            ).into(imageView)
    }
}