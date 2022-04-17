package iostudio.`in`.counterapp.activity

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import iostudio.`in`.counterapp.R
import iostudio.`in`.counterapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

const val rippleRadius: Int = 21

class MainActivity : AppCompatActivity(), View.OnTouchListener {
    private lateinit var tvCount: TextView
    private val mainViewModel: MainViewModel by viewModel()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCount = findViewById(R.id.tvCount)
        val linearLayoutMain: LinearLayout = findViewById(R.id.linearLayoutMain)
        linearLayoutMain.setOnTouchListener(this)

        setupObserver()

    }

    /**
     * get counts and show it on UI
     */
    private fun setupObserver() {
        mainViewModel.counts.observe(this) {
            tvCount.text = it.counts.toString()
        }
    }

    /**
     * Used for showing ripple animation on anywhere on screen with small modified radius instead complete view.
     * Show ripple animation where touched.
     */
    private fun showRippleAnimation(v: View, x: Float, y: Float) {
        v.background = ContextCompat.getDrawable(applicationContext, R.drawable.ripple)
        val background: Drawable = v.background
        if (background is RippleDrawable) {
            background.setHotspotBounds(x.roundToInt(), y.roundToInt(), x.roundToInt(), y.roundToInt())
            background.setHotspot(x, y)
            background.radius = rippleRadius
            background.setVisible(true, true)
            mainViewModel.updateCountByOne()
            tvCount.animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View?, me: MotionEvent?): Boolean {
        view?.let {
            if (me?.action == ACTION_DOWN) showRippleAnimation(it, me.x, me.y)
        }
        return false
    }
}