package com.gmail.apigeoneer.citywire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, NewsActivity::class.java)
            this.startActivity(mainIntent)

            //overriding the default sliding animation transition to the new activity

            // remove the Splash Activity from the stack (since we only want to show it once i.e., at the v beginning)
            this.finish()
        }, 3000)
    }
}