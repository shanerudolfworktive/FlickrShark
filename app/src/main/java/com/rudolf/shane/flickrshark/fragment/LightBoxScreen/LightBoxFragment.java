package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.workerObj.SimpleCountDownTimer;
import com.squareup.picasso.Picasso;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxFragment extends BaseFragment{

    Bitmap thumpNailBitmap;
    String originalImageUrl;
    public static LightBoxFragment create(Bitmap thumpNailBitmap, String originalImageUrl){
        LightBoxFragment lightBoxFragment = new LightBoxFragment();
        lightBoxFragment.thumpNailBitmap = thumpNailBitmap;
        lightBoxFragment.originalImageUrl = originalImageUrl;
        return lightBoxFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box, container, false);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewLightBox);
        imageView.setImageBitmap(thumpNailBitmap);
        new SimpleCountDownTimer(1000){
            @Override
            public void onFinish() {
                Picasso.with(getContext()).load(originalImageUrl).fit().centerInside().placeholder(new BitmapDrawable(getResources(), thumpNailBitmap)).into(imageView);
            }
        }.start();

        return rootView;
    }


}
