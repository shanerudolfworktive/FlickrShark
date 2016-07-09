package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.model.FlickrSearchPhotoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shane on 7/9/16.
 */
public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {
    ArrayList<FlickrSearchPhotoModel.PhotoModel> photos;

    public void setData(ArrayList<FlickrSearchPhotoModel.PhotoModel> photos){
        this.photos = photos;
        if(itemCount == null) itemCount = this.photos.size();
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
        int modulizedPosition = position % photos.size();
        Picasso.with(holder.imageView.getContext()).load(photos.get(modulizedPosition).thumbNeilUrl).fit().centerInside().noFade().into(holder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)

    Integer itemCount = null;
    public void setItemCount(int itemCount){
        this.itemCount =itemCount;
    }

    @Override
    public int getItemCount() {
        return itemCount == null ? 0 : itemCount;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View rootView) {
            super(rootView);
            imageView = (ImageView)rootView.findViewById(R.id.imageViewPhoto);
        }
    }
}


