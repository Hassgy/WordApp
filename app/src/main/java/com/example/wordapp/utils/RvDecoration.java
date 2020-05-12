package com.example.wordapp.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvDecoration extends RecyclerView.ItemDecoration {

    private int size;
    public RvDecoration(int size){
        this.size = size;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = size;
        outRect.right = size;
        outRect.bottom = size;

        if(parent.getItemDecorationCount() == 0){
            outRect.top = size;
        }
    }
}
