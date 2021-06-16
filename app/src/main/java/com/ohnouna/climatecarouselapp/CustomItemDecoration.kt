package com.ohnouna.climatecarouselapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomItemDecoration(): RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)



        val itemWidth = view.layoutParams.width
        val offset = (parent.width - itemWidth) / 8


        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0) {
                left = offset/2
            }
            //} else if(parent.getChildAdapterPosition(view)==state.itemCount-1) {
                right = offset
            //}
        }
    }

}