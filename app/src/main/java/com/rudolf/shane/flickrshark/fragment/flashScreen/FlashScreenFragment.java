package com.rudolf.shane.flickrshark.fragment.flashScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.MainActivity;
import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.utils.Constants;
import com.rudolf.shane.flickrshark.volley.GsonRequest;
import com.rudolf.shane.flickrshark.workerObj.SimpleCountDownTimer;

/**
 * Created by shane on 7/9/16.
 */
public class FlashScreenFragment extends BaseFragment{

    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        preFetchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flash_screen, container, false);
    }

    private void preFetchData(){
        //prepopulate data
        GsonRequest.populateCache(getContext(), Constants.FLICKR_SEARCH_URL, null);

        //delay 1 second
        new SimpleCountDownTimer(1000){
            @Override
            public void onFinish() {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }.start();
    }
}
