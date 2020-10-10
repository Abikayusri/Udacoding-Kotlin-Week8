package abika.sinau.assignmentweek8.ui.splash_screen

import abika.sinau.assignmentweek8.R
import abika.sinau.assignmentweek8.data.preferences.SessionManager
import abika.sinau.assignmentweek8.ui.home.MainActivity
import abika.sinau.assignmentweek8.ui.main.BoardingActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val session = SessionManager(this)

        Handler().postDelayed({
            if (session.isLogin != false) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, BoardingActivity::class.java))
            }
            finishAffinity()
        }, 3000)

    }
}