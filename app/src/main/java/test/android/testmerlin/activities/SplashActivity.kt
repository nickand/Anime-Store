package test.android.testmerlin.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

import test.android.testmerlin.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class SplashActivity : AppCompatActivity() {
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()

        setListeners()
    }

    private fun initViews() {

        setContentView(R.layout.activity_splash)
    }

    private fun setListeners() {
        val logoScaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_in)
        textSplash.startAnimation(logoScaleAnimation)

        logoScaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                animation.cancel()
                mHandler = Handler()
                val r = Runnable {
                    if (Companion.isDestroyed)
                        return@Runnable

                    openMainActivity()
                }
                mHandler!!.postDelayed(r, SPLASH_TIMER)
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }

    private fun openMainActivity() {
        val openSplashActivity = Intent(this@SplashActivity, MainActivity::class.java)
        openSplashActivity.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(openSplashActivity)
        overridePendingTransition(0, R.anim.screen_splash_fade_out)
        finish()
    }


    override fun onDestroy() {
        super.onDestroy()
        Companion.isDestroyed = true
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    companion object {

        private val SPLASH_TIMER: Long = 3000
        private val CLASS_TAG = SplashActivity::class.simpleName
        var isDestroyed = false
    }
}
