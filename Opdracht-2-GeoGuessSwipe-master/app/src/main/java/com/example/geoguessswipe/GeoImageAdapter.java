package com.example.geoguessswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class GeoImageAdapter extends RecyclerView.Adapter<GeoImageAdapter.ViewHolder> {

    //Create list named mGeoImages
    private List<GeoImage> mGeoImageList;

    public GeoImageAdapter(List<GeoImage> mGeoImageList) {
        this.mGeoImageList = mGeoImageList;
    }

    @NonNull
    @Override
    public GeoImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.geo_image_view, null);

        // Return a new holder instance
        return new GeoImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GeoImage geoImage = mGeoImageList.get(position);
        holder.imageView.setImageDrawable(geoImage.getId());
    }

    @Override
    public int getItemCount() {
        return mGeoImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.geoImage);
            view = itemView;
        }
    }

}


