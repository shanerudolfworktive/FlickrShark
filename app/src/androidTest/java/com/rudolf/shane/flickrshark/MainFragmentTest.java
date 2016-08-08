package com.rudolf.shane.flickrshark;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.test.suitebuilder.annotation.LargeTest;

import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.rudolf.shane.flickrshark.fragment.mainScreen.MainActivityFragment;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by shane on 8/6/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testDisplayingMainActivityFragment(){
        MainActivityFragment myFragment = (MainActivityFragment) mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG_MAIN_ACTIVITY_FRAGMENT);
        if (myFragment == null || !myFragment.isVisible()) {
            Assert.fail("not displaying MainActivityFragment");
        }
    }

    @Test
    public void testNetworkErrorHandling(){
        final MainActivityFragment mainActivityFragment = (MainActivityFragment) mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG_MAIN_ACTIVITY_FRAGMENT);
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityFragment.handleFlickrSearchPhotoError(new NetworkError(), null);
            }
        });
        mDevice.waitForIdle();
        onView(withText(R.string.network_error_message)).check(matches(withText(R.string.network_error_message)));
    }

    @Test
    public void testGenralErrorHandling(){
        final MainActivityFragment mainActivityFragment = (MainActivityFragment) mActivityRule.getActivity().getSupportFragmentManager().findFragmentByTag(MainActivityFragment.TAG_MAIN_ACTIVITY_FRAGMENT);
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivityFragment.handleFlickrSearchPhotoError(new TimeoutError(), null);
            }
        });
        mDevice.waitForIdle();
        onView(withText(R.string.generalErrorMessage)).check(matches(withText(R.string.generalErrorMessage)));
    }
}
