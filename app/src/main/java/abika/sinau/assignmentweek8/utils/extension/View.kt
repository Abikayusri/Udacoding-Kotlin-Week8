package abika.sinau.assignmentweek8.utils.extension

import android.view.View

internal fun View.show() {
    this.visibility = View.VISIBLE
}

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.invisible() {
    this.visibility = View.INVISIBLE
}