package com.example.tugasprojek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.viewHolder> {
    private List<DokterModel> dokter;
    private Context context;
    public DokterAdapter(List<DokterModel> dokter){
        this.dokter = dokter;
        this.context = context;
    }
    @NonNull
    @Override
    public DokterAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterAdapter.viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dokter.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView profilImage;
        TextView dokterName, dokterSpesialis, dokterAddress, dokterRating;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            profilImage = itemView.findViewById(R.id.imgProfil);
            dokterName = itemView.findViewById(R.id.txtName);
            dokterSpesialis = itemView.findViewById(R.id.txtSpesialis);
            dokterAddress = itemView.findViewById(R.id.txtAddress);
            dokterRating = itemView.findViewById(R.id.txtRating);
        }
    }
}
