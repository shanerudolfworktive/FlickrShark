package com.rudolf.shane.flickrshark.fragment.LightBoxScreen;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.model.FlickrInfoModel;
import com.rudolf.shane.flickrshark.utils.CapturePhotoUtils;
import com.rudolf.shane.flickrshark.utils.Constants;
import com.rudolf.shane.flickrshark.volley.GsonRequest;

/**
 * Created by shane on 7/9/16.
 */
public class LightBoxOverLayFragment extends BaseFragment{

    Bitmap bitmapToSaveToSDCard;
    long photoID;

    String title;
    String description;
    String username;

    GsonRequest<FlickrInfoModel> flickrInfoRequest;

    public static LightBoxOverLayFragment create(long photoID){
        LightBoxOverLayFragment fragment = new LightBoxOverLayFragment();
        fragment.photoID = photoID;
        return fragment;
    }

    public void setBitmapToSaveToSDCard(Bitmap bitmapToSaveToSDCard){
        this.bitmapToSaveToSDCard = bitmapToSaveToSDCard;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (title == null) title = getString(R.string.title);
        if (description == null) description = getString(R.string.description);
        if (username == null) username = getString(R.string.username);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_light_box_overlay, container, false);
        loadInitialViews(rootView);
        restoreViewState(rootView);
        return rootView;
    }

    private void loadInitialViews(View rootView){
        rootView.findViewById(R.id.imageButtonDownload).setOnClickListener(createDownloaddImageListener());
        if (flickrInfoRequest == null) flickrInfoRequest = createFlickrInfoRequest().setShouldCacheResponse(true).setShouldGetCachedResponseFirst(true).setShouldOnlyNotifyFromCache(true).sendRequest(getActivity());
    }

    private void restoreViewState(View rootView){
        updatePhotoContent(rootView, title, description, username);
    }


    private View.OnClickListener createDownloaddImageListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmapToSaveToSDCard == null){
                    Toast.makeText(getContext(), R.string.image_downloading_inprogress_error, Toast.LENGTH_LONG).show();
                }else {
                    CapturePhotoUtils.insertImage(getActivity().getContentResolver(), bitmapToSaveToSDCard, title, description);
                    Toast.makeText(getContext(), R.string.image_saved_to_camera, Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private GsonRequest<FlickrInfoModel> createFlickrInfoRequest(){
        GsonRequest<FlickrInfoModel> request = new GsonRequest<FlickrInfoModel>(Constants.FLICKR_PHOTO_INFO_URL(photoID), FlickrInfoModel.class, null) {
            @Override
            protected void deliverResponse(FlickrInfoModel response, boolean isFromCache) {
                title = response.photo.title.content;
                description = response.photo.description.content;
                username = response.photo.owner.username;
                updatePhotoContent(getView(), title, description, username);
            }

            @Override
            public void deliverError(VolleyError error, FlickrInfoModel cachedResponse) {

            }
        };
        requestToCancelOnDestroy.add(request);
        return request;
    }

    private void updatePhotoContent(View rootView,String title, String description, String username){
        if (rootView == null) return;
        TextView titleTextView = (TextView)rootView.findViewById(R.id.textViewTitle);
        titleTextView.setText(getString(R.string.title) + title);
        TextView descriptionTextView = (TextView)rootView.findViewById(R.id.textViewDescription);
        descriptionTextView.setText(getString(R.string.description) + description);
        TextView usernameTextView = (TextView)rootView.findViewById(R.id.textViewUsername);
        usernameTextView.setText(getString(R.string.username) + username);
    }
}
