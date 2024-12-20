package com.example.voyageapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.voyageapp.R;
import com.example.voyageapp.model.SuggestedPlaces;

import java.util.List;

public class SuggestedAdapter extends RecyclerView.Adapter<SuggestedAdapter.SuggestedViewHolder> {
    Context context;
    List<SuggestedPlaces> suggestedPlacesList;

    public SuggestedAdapter(Context context, List<SuggestedPlaces> suggestedPlacesList) {
        this.context = context;
        this.suggestedPlacesList = suggestedPlacesList;
    }

    @NonNull
    @Override
    public SuggestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.suggested_row_item,parent,false);
        //here we create a recycler view row item layout file
        return new SuggestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedViewHolder holder, int position) {


        holder.countryName.setText(suggestedPlacesList.get(position).getCountryName());
        holder.placeName.setText(suggestedPlacesList.get(position).getPlaceName());
        holder.categorie.setText(suggestedPlacesList.get(position).getCategorie());
        holder.placeImage.setImageResource(suggestedPlacesList.get(position).getImageUrl());


    }

    @Override
    public int getItemCount() {
        return suggestedPlacesList.size();
    }

    public static final class SuggestedViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage;
        TextView placeName,countryName,categorie;
        public SuggestedViewHolder(@NonNull View itemView) {
            super(itemView);
            placeImage=itemView.findViewById(R.id.place_image);
            placeName=itemView.findViewById(R.id.place_name);
            countryName=itemView.findViewById(R.id.country_name);
            categorie=itemView.findViewById(R.id.categorie);


        }
    }
}
