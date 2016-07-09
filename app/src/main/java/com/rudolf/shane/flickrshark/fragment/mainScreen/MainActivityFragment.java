package com.rudolf.shane.flickrshark.fragment.mainScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rudolf.shane.flickrshark.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMainShark);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        final MainRecycleViewAdapter adapter = new MainRecycleViewAdapter(new String[]{});
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new InifiniteScrollListener(layoutManager, adapter));

        return rootView;
    }
}
