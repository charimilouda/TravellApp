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

import com.example.voyageapp.data.model.Parc;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class ParcAdapter extends RecyclerView.Adapter<ParcAdapter.ParcViewHolder> {
    private ArrayList<Parc> parcs;
    private Context context;

    public ParcAdapter(ArrayList<Parc> parcs) {this.parcs = parcs;}


    @NonNull
    @Override
    public ParcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ParcViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcViewHolder holder, int position) {
        Parc parc = parcs.get(position);
        holder.Bind(parc);


        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", parc.getType());
            intent.putExtra("longitude", parc.getLongitude());
            intent.putExtra("latitude", parc.getLatitude());
            intent.putExtra("name", parc.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("address", parc.getAddress());
            intent.putExtra("country", parc.getCountry());
            intent.putExtra("city",parc.getCity());
            intent.putExtra(
                    "description",
                    "Immergez-vous dans la nature au Parc Naturel Régional "+parc.getName()+" et " +
                            "admirez une faune et une flore exceptionnelles. Parcourez les sentiers" +
                            " de randonnée, observez les oiseaux ou faites une pause pique-nique. " +
                            "Un véritable bol d'air frais !"
            );
            intent.putExtra("website", parc.getWebsite());
            intent.putExtra("imageUrl", parc.getImageUrl());
            intent.putExtra("wheelchairAccessible", parc.isWheelchairAccessible());
            intent.putExtra("openingHours", parc.getOpeningHours());
            intent.putExtra("panoramaImageUrl",parc.getPanoramaImageUrl());
            intent.putExtra("heritageLevel",parc.getHeritageLevel());
            context.startActivity(intent); // Démarrez l'activité de détail
        });
    }

    @Override
    public int getItemCount() {
        return parcs.size();
    }

    public void updateData(List<Parc> newParcs) {
        this.parcs.clear();
        this.parcs.addAll(newParcs);
        notifyDataSetChanged(); // Notifie le RecyclerView des modifications
    }


    public static class ParcViewHolder extends RecyclerView.ViewHolder {
        private LieuItemBinding binding;


        public ParcViewHolder(@NonNull LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(Parc parc){
            binding.lieuTitle.setText(parc.getName());
            binding.lieuRat.setText(String.valueOf(3.4));
            binding.lieuAddress.setText(parc.getAddress());
            if (parc.getImageUrl()!=null && ! parc.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(parc.getImageUrl())
                        .into(binding.lieuImage);
            }

        }
    }
}
