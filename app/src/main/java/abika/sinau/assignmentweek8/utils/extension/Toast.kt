package abika.sinau.assignmentweek8.utils.extension

import android.content.Context
import android.widget.Toast

/**
 * Created by Abika Chairul Yusri on 20/09/2020
 * Bismillahirrahmanirrahim
 */

fun shortToast(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, message , duration).show()
}

fun longToast(context: Context, message: String?, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(context, message , duration).show()
}

fun stringToast(context: Context, message: Int, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, context.getString(message), duration).apply { show() }
}