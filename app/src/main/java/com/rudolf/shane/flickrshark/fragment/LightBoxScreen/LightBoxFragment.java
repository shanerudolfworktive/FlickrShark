package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.squareup.picasso.Picasso;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxFragment extends BaseFragment{

    Drawable thumpNailDrawable;
    String originalImageUrl;
    public static LightBoxFragment create(Drawable thumpNailDrawable, String originalImageUrl){
        LightBoxFragment lightBoxFragment = new LightBoxFragment();
        lightBoxFragment.thumpNailDrawable = thumpNailDrawable;
        lightBoxFragment.originalImageUrl = originalImageUrl;
        return lightBoxFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewLightBox);

//        new SimpleCountDownTimer(1000){
//            @Override
//            public void onFinish() {
                Picasso.with(getContext()).load(originalImageUrl).fit().centerInside().placeholder(thumpNailDrawable).into(imageView);
//            }
//        }.start();

        return rootView;
    }


}
