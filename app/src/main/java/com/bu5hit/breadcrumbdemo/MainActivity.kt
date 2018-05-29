package com.bu5hit.breadcrumbdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import bu5hit.com.breadcrumb_factory.BreadCrumbFactrory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val breadNames = listOf<String>(
            "First Bread" ,
            "Second Bread",
            "Third Bread" ,
            "Forth Bread" ,
            "Fifth Bread"
    )
    private val breadColors = listOf<String>(
            "#F44336",
            "#FF9800",
            "#FFEB3B",
            "#4CAF50",
            "#03A9F4"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        breadNames.forEachIndexed { index, breadCrumbTitle ->
            pathContainer.addView(
                    BreadCrumbFactrory(context = this)
                            .setTitle(breadCrumbTitle)
                            .setBreadCrumbColor(breadColors[index])
                            .setStartEdgeOpen(index != 0)
                            .setEndEdgeOpen(true)
                            .make()
            )
        }


    }
}
