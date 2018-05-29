package bu5hit.com.breadcrumb_factory

import android.graphics.*
import android.graphics.drawable.Drawable

class BreadCrumbLabel : Drawable() {

    private var paint : Paint? = null
    private var path : Path? = null
    private enum class Sides { OPEN , CLOSE}
    private var startEdge = Sides.CLOSE
    private var endEdge = Sides.OPEN
    private var backgroundColor = 0


    init {
        paint = Paint()
        paint?.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            color = Color.BLACK
        }
        path = Path()
    }

    override fun draw(canvas: Canvas?) {
        canvas?.drawPath(path,paint)
    }
    override fun setAlpha(alpha: Int) {}
    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }
    override fun setColorFilter(filter: ColorFilter?) {}

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        makeCrumb(bounds)
    }

    fun setColor(hexColor: String){
        backgroundColor = Color.parseColor(hexColor)
        invalidateSelf()
    }

    fun setCrumbStart(crumb: Boolean){
        this.startEdge = if(crumb) Sides.OPEN else Sides.CLOSE
        invalidateSelf()
    }

    fun setCrumbEnd(crumb: Boolean){
        this.endEdge = if(crumb) Sides.OPEN else Sides.CLOSE
        invalidateSelf()
    }

    private fun makeCrumb(bounds: Rect){
        val height = bounds.bottom.minus(bounds.top)
        paint?.color = backgroundColor

        path?.reset()

        path?.moveTo(bounds.left.toFloat(),bounds.top.toFloat())
        // Upper left corner
        if(endEdge == Sides.OPEN){
            // open breadcrumb on right edge
            path?.lineTo(bounds.right - height/2F,0F)
            path?.lineTo(bounds.right.toFloat(),bounds.top + height / 2F)
            path?.lineTo(bounds.right - height /2F, bounds.bottom.toFloat())
        }
        else{
            // closed breadcrumb on right edge
            path?.lineTo(bounds.right.toFloat(),bounds.top.toFloat())
            path?.lineTo(bounds.right.toFloat(),bounds.bottom.toFloat())
        }
        path?.lineTo(bounds.left.toFloat(),bounds.bottom.toFloat())
        // Bottom left corner
        if(startEdge == Sides.OPEN)
            path?.lineTo(bounds.left + height /2F, bounds.top + height/2F)
        //
        path?.close()
    }
}