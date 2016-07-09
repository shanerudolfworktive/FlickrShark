package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.R;
import com.rudolf.shane.flickrshark.base.BaseFragment;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMainShark);
         layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MainRecycleViewAdapter(new String[]{});
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new InifiniteScrollListener(layoutManager, adapter));

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
