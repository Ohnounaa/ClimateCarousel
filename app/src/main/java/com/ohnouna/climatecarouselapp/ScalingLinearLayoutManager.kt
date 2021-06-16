package com.ohnouna.climatecarouselapp

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScalingLinearLayoutManager(context: Context): LinearLayoutManager(context,
    HORIZONTAL,
    false) {


    private val maximumScaleDistanceFactor: Float = 1.5F
    private val scaleDownQuantity: Float = 0.5F

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
    }


    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ) =
       super.scrollHorizontallyBy(dx, recycler, state).also { adjustScaleOfItems() }


    private fun adjustScaleOfItems() {
        val recyclerViewMiddle = width/2f

        val distanceToCenterCutoff = maximumScaleDistanceFactor * recyclerViewMiddle

        for(i in 0 until  childCount) {
            val child = getChildAt(i)

            val childCenter = (child?.left?.plus(child.right))?.div(2f)
            val childDistanceFromCenter = (childCenter?.minus(recyclerViewMiddle))
            val scaleDownFactor = (childDistanceFromCenter?.div(distanceToCenterCutoff))?.coerceAtMost(1f)
            val scale = scaleDownFactor?.times(scaleDownQuantity)?.let { 1f.minus(it) }

            val translationDirection = if(childCenter!! >recyclerViewMiddle) -1 else 1
            val translationXFromScale = translationDirection * child.width *(1- scale!!)/2f
            child.translationX = translationXFromScale


            child.apply {
                scaleX = scale
                scaleY = scale
            }

        }
        }
    }
