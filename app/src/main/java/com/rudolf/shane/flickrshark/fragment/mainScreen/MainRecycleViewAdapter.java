package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudolf.shane.flickrshark.R;

/**
 * Created by shane on 7/9/16.
 */
public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {
    private String[] mDataset = new String[]{"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};

    public MainRecycleViewAdapter(String[] myDataset) {

    }

    @Override
    public MainRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_main_shark_display, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.mTextView.setText(mDataset[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)

    int itemCount = 40;
    public void setItemCount(int itemCount){
        this.itemCount =itemCount;
//        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View rootView) {
            super(rootView);

        }
    }
}

