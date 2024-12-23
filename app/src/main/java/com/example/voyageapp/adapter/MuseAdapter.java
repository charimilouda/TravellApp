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

import com.example.voyageapp.data.model.Musee;
import com.example.voyageapp.databinding.LieuItemBinding;
import com.example.voyageapp.ui.resultats.DetailHotelActivity;

public class MuseAdapter extends RecyclerView.Adapter<MuseAdapter.MuseViewHolder> {
    private ArrayList<Musee> musees;
    private Context context;

    public  MuseAdapter(ArrayList<Musee> musees) { this.musees = musees;}


    @NonNull
    @Override
    public MuseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LieuItemBinding binding = LieuItemBinding.inflate(LayoutInflater.from(context), parent, false);
        return new MuseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseViewHolder holder, int position) {
        Musee musee = this.musees.get(position);
        holder.Bind(musee);

        holder.itemView.setOnClickListener(v -> {
            // Créez un Intent pour ouvrir l'activité de détail
            Intent intent = new Intent(context, DetailHotelActivity.class);
            intent.putExtra("type", musee.getType());
            intent.putExtra("longitude", musee.getLongitude());
            intent.putExtra("latitude", musee.getLatitude());
            intent.putExtra("name", musee.getName()); // Passez l'ID de l'hôtel ou d'autres données nécessaires
            intent.putExtra("address", musee.getAddress());
            intent.putExtra("country", musee.getCountry());
            intent.putExtra("city",musee.getCity());
            intent.putExtra(
                    "description",
                    "Découvrez le Musée "+musee.getName()+" de la Ville de "+musee.getCity()+" et laissez-vous" +
                            " émerveiller par les œuvres de maîtres tels que Picasso, Matisse" +
                            " et Miró. Ouvert tous les jours, de "+(musee.getOpeningHours()!=null? musee.getOpeningHours() :"08h00 à 19h.")
            );
            intent.putExtra("phone", musee.getPhone());
            intent.putExtra("website", musee.getWebsite());
            intent.putExtra("imageUrl", musee.getImageUrl());
            intent.putExtra("operator",musee.getOperator());
            intent.putExtra("openingHours",musee.getOpeningHours());
            intent.putExtra("wikipediaLink",musee.getWikipediaLink());
            intent.putExtra("wheelchairAccessible", musee.isWheelchairAccessible());
            intent.putExtra("buildingLevels", musee.getBuildingStartDate());
            intent.putExtra("operator",musee.getOperator());
            intent.putExtra("heritageInscriptionDate",musee.getHeritageInscriptionDate());
            intent.putExtra("heritageLevel",musee.getHeritageLevel());
            intent.putExtra("wikidataLink",musee.getWikidataLink());
            intent.putExtra("heritageRef",musee.getHeritageRef());
            intent.putExtra("buildingHeight",musee.getBuildingHeight());
            intent.putExtra("buildingType",musee.getBuildingType());
            intent.putExtra("wikimediaCommons",musee.getWikimediaCommons());
            context.startActivity(intent); // Démarrez l'activité de détail
        });
    }

    @Override
    public int getItemCount() {
        return this.musees.size();
    }

    public void updateData(List<Musee> newMusee) {
        this.musees.clear();
        this.musees.addAll(newMusee);
        notifyDataSetChanged();
    }

    public static class MuseViewHolder extends RecyclerView.ViewHolder {
        private LieuItemBinding binding;

        public MuseViewHolder(@NonNull LieuItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void Bind(Musee musee){
            binding.lieuTitle.setText(musee.getName());
            binding.lieuRat.setText(String.valueOf(4.4));
            binding.lieuAddress.setText(musee.getAddress());
            if (musee.getImageUrl()!=null && ! musee.getImageUrl().isEmpty()){
                Glide
                        .with(itemView.getContext())
                        .load(musee.getImageUrl())
                        .into(binding.lieuImage);

            }

        }

    }
}