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

import com.example.voyageapp.data.model.CentreCommercial;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class CentreCommercialAdapter extends RecyclerView.Adapter<CentreCommercialAdapter.CentreCommercialViewHolder> {
    private ArrayList<CentreCommercial> centresCommerciaux;
    private Context context;

    public CentreCommercialAdapter(ArrayList<CentreCommercial> centresCommerciaux) {
        this.centresCommerciaux = centresCommerciaux != null ? centresCommerciaux : new ArrayList<>();
    }


    @NonNull
    @Override
    public CentreCommercialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new CentreCommercialViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CentreCommercialViewHolder holder, int position) {
        CentreCommercial centreCommercial = this.centresCommerciaux.get(position);
        holder.Bind(centreCommercial);

        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", centreCommercial.getType());
            intent.putExtra("longitude", centreCommercial.getLongitude());
            intent.putExtra("latitude", centreCommercial.getLatitude());
            intent.putExtra("name", centreCommercial.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("address", centreCommercial.getAddress());
            intent.putExtra("country", centreCommercial.getCountry());
            intent.putExtra("city",centreCommercial.getCity());
            intent.putExtra(
                    "description",
                    centreCommercial.getName()+" est un grand centre commercial familial situé à "+centreCommercial.getCity()+
                            ", "+centreCommercial.getCountry()+". Ouvert de 8h00 à 22h00, il offre un accès pour" +
                            " personnes à mobilité réduite, des toilettes, un cinéma, une aire de " +
                            "jeux pour enfants et un large choix de boutiques. Découvrez notre sélection" +
                            " de restaurants et profitez d'une expérience de shopping unique."
            );
            intent.putExtra("phone", centreCommercial.getPhone());
            intent.putExtra("website", centreCommercial.getWebsite());
            intent.putExtra("imageUrl", centreCommercial.getImageUrl());
            intent.putExtra("startDate",centreCommercial.getStartDate());
            intent.putExtra("openingHours",centreCommercial.getOpeningHours());
            intent.putExtra("wikipediaLink",centreCommercial.getWikipediaLink());
            intent.putExtra("wheelchairAccessible", centreCommercial.isWheelchairAccessible());
            intent.putExtra("hasToilets",centreCommercial.isHasToilets());
            intent.putExtra("wikidataLink",centreCommercial.getWikidataLink());
            intent.putExtra("buildingHeight",centreCommercial.getBuildingHeight());
            intent.putExtra("buildingType",centreCommercial.getBuildingType());
            context.startActivity(intent); // Démarrez l'activité de détail
        });
    }

    @Override
    public int getItemCount() {
        return (this.centresCommerciaux != null) ? this.centresCommerciaux.size() : 0;
    }

    public void updateData(List<CentreCommercial> newCentresCommerciaux) {
        if (this.centresCommerciaux == null) {
            this.centresCommerciaux = new ArrayList<>();
        }
        this.centresCommerciaux.clear();
        if (newCentresCommerciaux != null && !newCentresCommerciaux.isEmpty()) {
            this.centresCommerciaux.addAll(newCentresCommerciaux);
        }
        notifyDataSetChanged(); // Notifie le RecyclerView des modifications
    }

    public static class CentreCommercialViewHolder extends RecyclerView.ViewHolder {
        private LieuItemBinding binding;

        public CentreCommercialViewHolder(@NonNull LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(CentreCommercial centreCommercial){
            binding.lieuTitle.setText(centreCommercial.getName());
            binding.lieuRat.setText(String.valueOf(6.5));
            binding.lieuAddress.setText(centreCommercial.getAddress());
            if (centreCommercial.getImageUrl()!=null && ! centreCommercial.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(centreCommercial.getImageUrl())
                        .into(binding.lieuImage);

            }
        }
    }
}
