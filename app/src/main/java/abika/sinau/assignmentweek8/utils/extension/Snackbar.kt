package abika.sinau.assignmentweek8.utils.extension

import androidx.annotation.ColorInt
import com.google.android.material.snackbar.Snackbar

fun Snackbar.withColor(@ColorInt colorInt: Int): Snackbar{
    this.view.setBackgroundColor(colorInt)
    return this
}

