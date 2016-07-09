package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.utils.CapturePhotoUtils;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxOverLayFragment extends BaseFragment{

    Bitmap bitmapToSaveToSDCard;
    public static LightBoxOverLayFragment create(){
        LightBoxOverLayFragment fragment = new LightBoxOverLayFragment();
        return fragment;
    }

    public void setBitmapToSaveToSDCard(Bitmap bitmapToSaveToSDCard){
        this.bitmapToSaveToSDCard = bitmapToSaveToSDCard;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box_overlay, container, false);

        rootView.findViewById(R.id.imageButtonDownload).setOnClickListener(createDownloaddImageListener());

        return rootView;
    }

    View.OnClickListener createDownloaddImageListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmapToSaveToSDCard == null){
                    Toast.makeText(getContext(), R.string.image_downloading_inprogress_error, Toast.LENGTH_LONG).show();
                }else {
                    CapturePhotoUtils.insertImage(getActivity().getContentResolver(), bitmapToSaveToSDCard, "testTitle", "testDescription");
                    Toast.makeText(getContext(), R.string.image_saved_to_camera, Toast.LENGTH_LONG).show();
                }
            }
        };
    }
}
