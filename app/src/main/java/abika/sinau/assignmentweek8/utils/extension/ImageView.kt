package abika.sinau.assignmentweek8.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.io.File

internal fun ImageView.loadImage(file: File): Boolean {

    var loaded = false

    if (file.exists()) {
        Glide
            .with(this)
            .load(file)
            .into(this);

        loaded = true
    }

    return loaded
}

internal fun ImageView.loadImage(url: String, placeholder: Int) {
//    CommonUtil.log("load-image: $url")
    Glide
        .with(this)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}

internal fun ImageView.loadImage(url: String?) {
//    CommonUtil.log("load-image: $url")
    Glide
        .with(this)
        .load(url)
        .into(this);
}