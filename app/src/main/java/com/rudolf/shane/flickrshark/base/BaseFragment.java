package com.rudolf.shane.flickrshark.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;

import com.android.volley.Request;

import java.util.ArrayList;

/**
 * Created by shane on 7/9/16.
 */
public abstract class BaseFragment extends Fragment {

    protected ArrayList<Request> requestToCancelOnDestroy= new ArrayList<>();
    public OnResumeListener onResumeListener;
    private boolean mNeedToAvoidAnimation;

    public interface OnResumeListener{
        void onResume();
    }

    public void setOnResumeListener(OnResumeListener onResumeListener){
        this.onResumeListener = onResumeListener;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNeedToAvoidAnimation = true;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        // This avoids the transaction animation when the orienatation changes
        boolean needToAvoidAnimation = mNeedToAvoidAnimation;
        mNeedToAvoidAnimation = false;
        return needToAvoidAnimation ? new Animation() {
        } : super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);//default to retain fragment
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onResumeListener != null) onResumeListener.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Request request: requestToCancelOnDestroy) request.cancel();

    }
}
