package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxOverLayFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box_overlay, container, false);
        return rootView;
    }
}
