package com.example.android_assignment_question2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameCanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F5F7FA")
    }

    private val cardPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F44336")
    }

    private val rectanglePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2196F3")
    }

    private val trianglePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#4CAF50")
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 8f
    }

    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 52f
        typeface = Typeface.DEFAULT_BOLD
    }

    private val infoPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        textAlign = Paint.Align.CENTER
        textSize = 36f
    }

    private var circleOffset = -220f
    private var direction = 7f

    private var rotationAngle = 0f

    private var score = 0

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cx = width / 2f
        val cy = height / 2f

        canvas.drawPaint(backgroundPaint)

        // Dashboard Card
        canvas.drawRoundRect(
            cx - 330f,
            cy - 300f,
            cx + 330f,
            cy + 330f,
            35f,
            35f,
            cardPaint
        )

        // Title
        canvas.drawText(
            "Interactive Graphics Dashboard",
            cx,
            cy - 240f,
            titlePaint
        )

        canvas.drawText(
            "Canvas Animation Demo",
            cx,
            cy - 190f,
            infoPaint
        )

        // Moving Circle
        canvas.drawCircle(
            cx + circleOffset,
            cy - 80f,
            60f,
            circlePaint
        )

        // Rectangle
        canvas.drawRect(
            cx - 130f,
            cy + 20f,
            cx + 130f,
            cy + 170f,
            rectanglePaint
        )

        // Line
        canvas.drawLine(
            cx - 220f,
            cy + 230f,
            cx + 220f,
            cy + 230f,
            linePaint
        )

        // Rotating Triangle
        canvas.save()

        canvas.rotate(rotationAngle, cx, cy + 90f)

        val triangle = Path()

        triangle.moveTo(cx, cy - 10f)
        triangle.lineTo(cx - 70f, cy + 110f)
        triangle.lineTo(cx + 70f, cy + 110f)
        triangle.close()

        canvas.drawPath(triangle, trianglePaint)

        canvas.restore()

        // Score
        canvas.drawText(
            "Score : $score",
            cx,
            cy + 295f,
            infoPaint
        )

        updateAnimation()

        postInvalidateDelayed(16)
    }

    private fun updateAnimation() {

        circleOffset += direction

        if (circleOffset > 220f) {

            direction = -7f
            score++
        }

        if (circleOffset < -220f) {

            direction = 7f
            score++
        }

        rotationAngle += 2f

        if (rotationAngle >= 360f) {
            rotationAngle = 0f
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN) {

            circleOffset = -220f
            rotationAngle = 0f
            score = 0

            invalidate()
        }

        return true
    }
}