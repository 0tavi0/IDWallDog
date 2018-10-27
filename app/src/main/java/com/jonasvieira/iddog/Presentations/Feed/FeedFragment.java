package com.jonasvieira.iddog.Presentations.Feed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jonasvieira.iddog.Presentations.Feed.Adapter.FeedAdapter;
import com.jonasvieira.iddog.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedFragment extends Fragment implements DogOnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    public void setAdapter(List<String> list) {
        FeedAdapter feedAdapter = new FeedAdapter(list, this);
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void clickDog(String item, int position) {
        Toast.makeText(getContext(), item + position, Toast.LENGTH_SHORT).show();
    }
}
