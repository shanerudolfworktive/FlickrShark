package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxFragment extends BaseFragment{

    Drawable thumpNailDrawable;
    String originalImageUrl;

    boolean isOverLayShown = false;
    LightBoxOverLayFragment lightBoxOverLayFragment = new LightBoxOverLayFragment();
    public static LightBoxFragment create(Drawable thumpNailDrawable, String originalImageUrl){
        LightBoxFragment lightBoxFragment = new LightBoxFragment();
        lightBoxFragment.thumpNailDrawable = thumpNailDrawable;
        lightBoxFragment.originalImageUrl = originalImageUrl;
        return lightBoxFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box, container, false);
        loadInitialView(rootView);
        return rootView;
    }

    private void loadInitialView(View rootView ){
        PhotoView photoView = (PhotoView) rootView.findViewById(R.id.imageViewLightBox);
        photoView.setOnPhotoTapListener(createToggleOverLayListener());
        Picasso.with(getContext()).load(originalImageUrl).fit().centerInside().placeholder(thumpNailDrawable).into(photoView);
    }

    private PhotoViewAttacher.OnPhotoTapListener createToggleOverLayListener(){
        return new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (isOverLayShown){
                    getActivity().getSupportFragmentManager().beginTransaction().remove(lightBoxOverLayFragment).commit();
                }else {
                    getActivity().getSupportFragmentManager().beginTransaction().add(getId(), lightBoxOverLayFragment).commit();
                }
                isOverLayShown = !isOverLayShown;
            }
        };
    }

}
