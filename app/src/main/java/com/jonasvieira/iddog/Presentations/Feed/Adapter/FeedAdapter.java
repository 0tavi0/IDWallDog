package com.jonasvieira.iddog.Presentations.Feed.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.jonasvieira.iddog.R;
import com.jonasvieira.iddog.Utils.GlideHelper;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<String> mList;
    private Context context;

    public FeedAdapter(List<String> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dogs, parent, false);
        context = parent.getContext();
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        GlideHelper.getImage(context, mList.get(position), holder.imageDog, holder.progressBar);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        CardView cardDog;
        ImageView imageDog;
        ProgressBar progressBar;

        FeedViewHolder(View itemView) {
            super(itemView);

            cardDog = itemView.findViewById(R.id.cardDog);
            imageDog = itemView.findViewById(R.id.imgDog);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
