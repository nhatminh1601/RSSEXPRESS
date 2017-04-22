package com.example.nguye.rssexpress;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by nguye on 19/04/2017.
 */

public class VerticalSpace extends RecyclerView.ItemDecoration {
    int Space;

    public VerticalSpace(int space) {
        Space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=Space;
        outRect.bottom=Space;
        outRect.right=Space;
        if(parent.getChildLayoutPosition(view)==0){
            outRect.top=Space;
        }
    }
}
