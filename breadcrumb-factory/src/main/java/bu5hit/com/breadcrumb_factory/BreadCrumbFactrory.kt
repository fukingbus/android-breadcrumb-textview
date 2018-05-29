package bu5hit.com.breadcrumb_factory

import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.dip

class BreadCrumbFactrory(val context: Context) {
    private var breadTitle = ""
    private var isStartEdgeOpen = true
    private var isEndEdgeOpen = true
    private var breadCrumbColor = "#000000"
    private var breadTextColor = "#FFFFFF"

    fun setTitle(title: String) = apply {
        this.breadTitle = title
    }

    fun setStartEdgeOpen(open : Boolean) = apply { // set breadcrumb's start edge open or not
        this.isStartEdgeOpen = open
    }
    fun setEndEdgeOpen(open: Boolean) = apply {// set breadcrumb's end edge open or not
        this.isEndEdgeOpen = open
    }
    fun setBreadCrumbColor(hexCode : String) = apply {// set breadcrumb's background color
        this.breadCrumbColor = hexCode
    }
    fun setBreadTextColor(hexCode: String) = apply {// set breadcrumb's text
        this.breadTextColor = hexCode
    }

    fun make(): TextView{
        val bread = TextView(context) // breadcrumb view
        val breadCrumbDrawable = BreadCrumbLabel() // breadcrumb background
        breadCrumbDrawable.setCrumbStart(isStartEdgeOpen)// set breadcrumb's start edge open or not
        breadCrumbDrawable.setCrumbEnd(isEndEdgeOpen)// set breadcrumb's end edge open or not
        breadCrumbDrawable.setColor(breadCrumbColor)// set breadcrumb's background color
        bread.setBackgroundDrawable(breadCrumbDrawable) // apply into breadcrumb
        bread.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        bread.gravity = Gravity.CENTER
        bread.setSingleLine(true)
        bread.ellipsize = TextUtils.TruncateAt.END
        bread.text = breadTitle // set its text
        bread.setTextColor(Color.parseColor(breadTextColor))// set its color
        bread.setPadding( // add padding to the textview
                if(isStartEdgeOpen) context.dip(24) else context.dip(8),
                context.dip(8),
                context.dip(16),
                context.dip(8)
        )

        if(isStartEdgeOpen){
            val param = bread.layoutParams as LinearLayout.LayoutParams
            param.leftMargin = context.dip(-16)
            //bread.layoutParams = param
        }
        return bread
    }
}