package com.rudolf.shane.flickrshark.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.fragment.FlashScreenFragment;

/**
 * Created by shane on 7/9/16.
 */
public class FlashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, new FlashScreenFragment()).commit();
        }
    }
}
