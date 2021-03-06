package abika.sinau.assignmentweek8.data.preferences

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Abika Chairul Yusri on 10/10/2020
 * Bismillahirrahmanirrahim
 */
class SessionManager(var c: Context) {
    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGINREGISTER"

    var ISLOGIN = "isLogin"
    var NAME = "name"
    var EMAIL = "email"

    init {
        pref = c.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    var isLogin: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(isLogin) {
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }
    var email: String?
        get() = pref?.getString(EMAIL, "")
        set(email) {
            editor?.putString(EMAIL, email)
            editor?.commit()
        }

    fun logout() {
        editor?.clear()
        editor?.commit()
    }
}