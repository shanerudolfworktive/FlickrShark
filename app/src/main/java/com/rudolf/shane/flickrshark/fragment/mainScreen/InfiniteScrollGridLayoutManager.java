package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * Created by shane on 7/9/16.
 */
public class InfiniteScrollGridLayoutManager extends GridLayoutManager{

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public InfiniteScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }
}
