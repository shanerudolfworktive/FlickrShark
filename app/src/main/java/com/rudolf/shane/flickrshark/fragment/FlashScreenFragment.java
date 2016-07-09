package com.rudolf.shane.flickrshark.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.MainActivity;
import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.workerObj.SimpleCountDownTimer;

/**
 * Created by shane on 7/9/16.
 */
public class FlashScreenFragment extends BaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preFetchData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flash_screen, container, false);
    }

    private void preFetchData(){
        new SimpleCountDownTimer(5000){
            @Override
            public void onFinish() {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }.start();
    }
}
