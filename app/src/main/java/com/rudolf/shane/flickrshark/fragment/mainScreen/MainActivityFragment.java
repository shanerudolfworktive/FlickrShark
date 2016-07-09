package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;
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

    RecyclerView recyclerView;
    MainRecycleViewAdapter adapter;
    GridLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;

    GsonRequest<FlickrSearchPhotoModel> photosModelRequest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MainRecycleViewAdapter();
        photosModelRequest = new GsonRequest<FlickrSearchPhotoModel>(Constants.FLICKR_SEARCH_URL, FlickrSearchPhotoModel.class, null) {
            @Override
            protected void deliverResponse(FlickrSearchPhotoModel response, boolean isFromCache) {
                adapter.setData(response.photos.photo);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void deliverError(VolleyError error, FlickrSearchPhotoModel cachedResponse) {
                Log.e("shaneTest", "fail="+error.getLocalizedMessage());
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        requestToCancelOnDestroy.add(photosModelRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMainShark);
        recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new InifiniteScrollListener(layoutManager, adapter));

        swipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                photosModelRequest.setShouldCacheResponse(true).setShouldGetCachedResponseFirst(true).setShouldOnlyNotifyFromCache(true).sendRequest(getActivity());
            }
        });

        //View States
        if (totalChildBeforOrientationChange != null) adapter.setItemCount(totalChildBeforOrientationChange);
        if (listPositionBeforOrientationChange != null) recyclerView.offsetChildrenVertical(listPositionBeforOrientationChange);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Save View States
        totalChildBeforOrientationChange = adapter.getItemCount();
        listPositionBeforOrientationChange = recyclerView.computeVerticalScrollOffset();
    }
}
