package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
import com.rudolf.shane.flickrshark.fragment.LightBoxScreen.LightBoxFragment;
import com.rudolf.shane.flickrshark.model.FlickrSearchPhotoModel;
import com.rudolf.shane.flickrshark.utils.Constants;
import com.rudolf.shane.flickrshark.volley.GsonRequest;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment {

    //View States
    Integer totalChildBeforOrientationChange = null;
    Integer listPositionBeforOrientationChange = null;

    //view
    RecyclerView recyclerView;
    MainRecycleViewAdapter adapter = new MainRecycleViewAdapter();;
    SwipeRefreshLayout swipeRefreshLayout;

    GsonRequest<FlickrSearchPhotoModel> photosModelRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        loadInitialView(rootView);//load initial views
        restoreViewState(rootView);//restore view state
        return rootView;
    }

    private void loadInitialView(View rootView){
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMainShark);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new InifiniteScrollListener(layoutManager, adapter));
        adapter.setOnItemSelectedListener(new MainRecycleViewAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Drawable drawable, FlickrSearchPhotoModel.PhotoModel photo) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_light_box_scene_animation, R.anim.exit_light_box_scene_animation, R.anim.pop_enter_light_box_scene_animation, R.anim.pop_exit_light_box_scene_animation).replace(getId(), LightBoxFragment.create(drawable, photo.originalPhotoUrl, photo.id)).addToBackStack(null).commit();
            }
        });

        swipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                photosModelRequest.setShouldCacheResponse(true).sendRequest(getActivity());
            }
        });

        //send request if it is first time loading the view
        if (photosModelRequest == null) photosModelRequest = createFlickrSearchRequest().setShouldCacheResponse(true).setShouldGetCachedResponseFirst(true).setShouldOnlyNotifyFromCache(true).sendRequest(getActivity());
    }

    private void restoreViewState(View rootView) {
        if (totalChildBeforOrientationChange != null) adapter.setItemCount(totalChildBeforOrientationChange);
        if (listPositionBeforOrientationChange != null) recyclerView.offsetChildrenVertical(listPositionBeforOrientationChange);
    }

    private void saveViewState(){
        totalChildBeforOrientationChange = adapter.getItemCount();
        listPositionBeforOrientationChange = recyclerView.computeVerticalScrollOffset();
    }

    private GsonRequest<FlickrSearchPhotoModel> createFlickrSearchRequest(){
        GsonRequest<FlickrSearchPhotoModel> request = new GsonRequest<FlickrSearchPhotoModel>(Constants.FLICKR_SEARCH_URL, FlickrSearchPhotoModel.class, null) {
            @Override
            protected void deliverResponse(FlickrSearchPhotoModel response, boolean isFromCache) {
                adapter.setData(response.photos.photo);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void deliverError(VolleyError error, FlickrSearchPhotoModel cachedResponse) {
                Toast.makeText(getActivity(), R.string.network_error_message, Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        requestToCancelOnDestroy.add(request);
        return request;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveViewState();
    }
}
