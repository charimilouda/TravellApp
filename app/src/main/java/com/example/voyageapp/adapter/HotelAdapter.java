package com.example.voyageapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import com.example.voyageapp.data.model.Hotel;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.LieuViewHolder> {

    private ArrayList<Hotel> hotels;
    private Context context;



    public HotelAdapter(ArrayList<Hotel> hotels){
        this.hotels = hotels;
    }


    @NonNull
    @Override
    public LieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LieuViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull LieuViewHolder holder, int position) {
        Hotel hotel = this.hotels.get(position);
        holder.Bind(hotel);


        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", hotel.getType());
            intent.putExtra("name", hotel.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("address", hotel.getAddress());
            intent.putExtra("country", hotel.getCountry());
            intent.putExtra("city",hotel.getCity());
            intent.putExtra("longitude", hotel.getLongitude());
            intent.putExtra("latitude", hotel.getLatitude());
            intent.putExtra("description","Au cœur de "+hotel.getCity()+",l'Hôtel " + hotel.getName() + " vous offre un séjour urbain et dynamique." +
                    "Découvrez notre spa dernier cri, notre salle de fitness et " +
                    "nos chambres design. Ouvert " + hotel.getOpening_hours() + ", accessible à tous.");
            intent.putExtra("openingHours",hotel.getOpening_hours());
            intent.putExtra("phone", hotel.getPhone());
            intent.putExtra("website", hotel.getWebsite());
            intent.putExtra("imageUrl", hotel.getImageUrl());
            intent.putExtra("wheelchairAccessible", hotel.isWheelchairAccessible());
            intent.putExtra("buildingLevels", hotel.getBuildingLbevels());
            context.startActivity(intent); // Démarrez l'activité de détail
        });

    }


    @Override
    public int getItemCount()
    {
        return hotels.size();
    }

    public void updateData(List<Hotel> newHotels) {
        this.hotels.clear();
        this.hotels.addAll(newHotels);
        notifyDataSetChanged(); // Notifie le RecyclerView des modifications
    }


    public static class LieuViewHolder extends RecyclerView.ViewHolder {

        private LieuItemBinding binding;

        public LieuViewHolder(@NonNull LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(Hotel hotel){
            binding.lieuTitle.setText(hotel.getName());
            binding.lieuRat.setText(String.valueOf(4.4));
            binding.lieuAddress.setText(hotel.getAddress());

            if (hotel.getImageUrl()!=null && ! hotel.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(hotel.getImageUrl())
                        .into(binding.lieuImage);
            }



        }
    }
}
