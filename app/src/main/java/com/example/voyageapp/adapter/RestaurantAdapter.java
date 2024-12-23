package com.example.voyageapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.voyageapp.data.model.Restaurant;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private Context context;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new RestaurantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = this.restaurants.get(position);
        holder.Bind(restaurant);

        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", restaurant.getType());
            intent.putExtra("longitude", restaurant.getLongitude());
            intent.putExtra("latitude", restaurant.getLatitude());
            intent.putExtra("name", restaurant.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("address", restaurant.getAddress());
            intent.putExtra("country", restaurant.getCountry());
            intent.putExtra("description","Envie d'un repas rapide et savoureux ? Le " + restaurant.getName() + " vous propose une large sélection de burgers, de sandwichs et de salades, préparés à partir de produits frais.À emporter ou à consommer sur place.");
            intent.putExtra("city",restaurant.getCity());
            intent.putExtra("phone", restaurant.getPhone());
            intent.putExtra("website", restaurant.getWebsite());
            intent.putExtra("imageUrl", restaurant.getImageUrl());
            intent.putExtra("openingHours",restaurant.getOpeningHours());
            intent.putExtra("wheelchairAccessible", restaurant.isWheelchairAccessible());
            intent.putExtra("twitterHandle",restaurant.getTwitterHandle());
            intent.putExtra("facebookPage",restaurant.getFacebookPage());
            intent.putExtra("email",restaurant.getEmail());
            intent.putExtra("wheelchairToilets",restaurant.isWheelchairToilets());
            context.startActivity(intent); // Démarrez l'activité de détail
        });
    }

    @Override
    public int getItemCount() {
        return this.restaurants.size();
    }

    public void updateData(List<Restaurant> newRestaurants) {
        this.restaurants.clear();
        this.restaurants.addAll(newRestaurants);
        notifyDataSetChanged(); // Notifie le RecyclerView des modifications
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public LieuItemBinding binding;

        public RestaurantViewHolder(@NonNull  LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void Bind(Restaurant restaurant){
            binding.lieuTitle.setText(restaurant.getName());
            binding.lieuRat.setText(String.valueOf(6.1));
            binding.lieuAddress.setText(restaurant.getAddress());
            if (restaurant.getImageUrl()!=null && ! restaurant.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(restaurant.getImageUrl())
                        .into(binding.lieuImage);

            }


        }
    }
}


