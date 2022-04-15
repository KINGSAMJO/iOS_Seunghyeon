package co.kr.sopt_seminar_30th.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import co.kr.sopt_seminar_30th.R
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("setProfileImage")
    fun setProfileImage(view: ImageView, src: Uri?) {
        if(src != null) {
            Glide.with(view.context)
                .load(src)
                .error(R.mipmap.sopt_logo)
                .into(view)
        } else {
            Glide.with(view.context)
                .load(R.mipmap.sopt_logo)
                .into(view)
        }
    }
}