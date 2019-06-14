package com.lukelorusso.simplepaperviewsample

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.lukelorusso.simplepaperview.SimplePaperView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var colors: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //region INIT VIEW
        colors = listOf(
            ContextCompat.getColor(this, R.color.line1),
            ContextCompat.getColor(this, R.color.line2),
            ContextCompat.getColor(this, R.color.line3),
            ContextCompat.getColor(this, R.color.line4)
        )

        drawStuff()
        mainSimplePaperView.listener = { mainScrollView.post { mainScrollView.fullScroll(View.FOCUS_RIGHT) } }
        mainBtnClear.setOnClickListener { mainSimplePaperView.clearPaper() }
        mainBtnRedraw.setOnClickListener {
            mainSimplePaperView.clearPaper(false)
            drawStuff()
        }
        //endregion
    }

    private fun drawStuff() {
        drawText()
        draw1stItem()
        Handler().postDelayed({ draw2ndItem() }, 500)
        Handler().postDelayed({ draw3thItem() }, 1000)
        Handler().postDelayed({ draw4thItem() }, 1500)
    }

    private fun drawText() {
        mainSimplePaperView.drawInDp(
            SimplePaperView.TextLabel(
                getString(R.string.textLabel),
                14F,
                100F,
                30F,
                ContextCompat.getColor(this, R.color.textLabel),
                true,
                ResourcesCompat.getFont(this, R.font.roboto_italic)
            )
        )
    }

    private fun draw1stItem() {
        mainSimplePaperView.drawInDp(
            SimplePaperView.Line(0F, 0F, 200F, 200F, colors[0], 4F)
        )
    }

    private fun draw2ndItem() {
        mainSimplePaperView.drawInDp(
            SimplePaperView.Line(200F, 200F, 400F, 0F, colors[1], 8F)
        )
    }

    private fun draw3thItem() {
        mainSimplePaperView.drawInDp(
            SimplePaperView.Line(400F, 0F, 600F, 180F, colors[2], 2F)
        )
    }

    private fun draw4thItem() {
        mainSimplePaperView.drawInDp(
            SimplePaperView.Circle(600F, 90F, 25F, colors[3])
        )
    }

}
