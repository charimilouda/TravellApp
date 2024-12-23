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

import com.example.voyageapp.data.model.Cafe;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.CafeViewHolder>{
    private ArrayList<Cafe> cafes;
    private Context context;

    public CafeAdapter(ArrayList<Cafe> cafes){
        this.cafes = cafes;
    }


    @NonNull
    @Override
    public CafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CafeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CafeViewHolder holder, int position) {
        Cafe cafe = this.cafes.get(position);
        holder.Bind(cafe);


        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", cafe.getType());
            intent.putExtra("name", cafe.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("longitude", cafe.getLongitude());
            intent.putExtra("latitude", cafe.getLatitude());
            intent.putExtra("address", cafe.getAddress());
            intent.putExtra("country", cafe.getCountry());
            intent.putExtra("city",cafe.getCity());
            intent.putExtra(
                    "description",
                    "Découvrez le " + cafe.getName() + " à "+cafe.getCity()+", "+cafe.getCountry()+
                            ", un lieu convivial où vous pourrez déguster un délicieux café tout en profitant de la" +
                            " connexion Wi-Fi gratuite. Ouvert de "
                            + (cafe.getOpeningHours()==null? cafe.getOpeningHours() : " 6h30 à 23h00, 7j/7" )+
                            ". Terrasse et accès PMR disponibles."
            );
            intent.putExtra("phone", cafe.getPhone());
            intent.putExtra("website", cafe.getWebsite());
            intent.putExtra("imageUrl", cafe.getImageUrl());
            intent.putExtra("startDate",cafe.getInternationalName());
            intent.putExtra("openingHours",cafe.getOpeningHours());
            intent.putExtra("wikipediaLink",cafe.isAirConditioning());
            intent.putExtra("wheelchairAccessible", cafe.isWheelchairAccessible());
            intent.putExtra("internetAccess",cafe.isInternetAccess());
            intent.putExtra("wikidataLink",cafe.getWikidataLink());
            intent.putExtra("buildingHeight",cafe.isOutdoorSeating());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return this.cafes.size();
    }

    public void updateData(List<Cafe> newCafes) {
        this.cafes.clear();
        this.cafes.addAll(newCafes);
        notifyDataSetChanged(); // Notifie le RecyclerView des modifications
    }


    public static class CafeViewHolder extends RecyclerView.ViewHolder {
        private LieuItemBinding binding;

        public CafeViewHolder(@NonNull LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(Cafe cafe){
            binding.lieuTitle.setText(cafe.getName());
            binding.lieuRat.setText(String.valueOf(4.4));
            binding.lieuAddress.setText(cafe.getAddress());
            if (cafe.getImageUrl()!=null && ! cafe.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(cafe.getImageUrl())
                        .into(binding.lieuImage);

            }

        }
    }
}
