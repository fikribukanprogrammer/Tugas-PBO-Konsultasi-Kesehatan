package com.example.tugasprojek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.viewHolder> {
    private List<DokterModel> dokter;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public DokterAdapter(List<DokterModel> dokter, Context context, OnItemClickListener listener) {
        this.dokter = dokter;
        this.context = context;
        this.listener = listener;
    }

    public void setDokterList(List<DokterModel> dokterList) {
        this.dokter = dokterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DokterAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DokterAdapter.viewHolder holder, int position) {
        DokterModel currentDokter = dokter.get(position);
        holder.dokterName.setText(currentDokter.getDoctorName());
        holder.dokterSpesialis.setText(currentDokter.getDoctorSpesialis());
        holder.dokterAddress.setText(currentDokter.getDoctorAddress());
        holder.biaya_konsultasi.setText(String.valueOf(currentDokter.getBiaya_konsultasi()));
    }

    @Override
    public int getItemCount() {
        return dokter.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView dokterName, dokterSpesialis, dokterAddress, biaya_konsultasi;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dokterName = itemView.findViewById(R.id.txtName);
            dokterSpesialis = itemView.findViewById(R.id.txtSpesialis);
            dokterAddress = itemView.findViewById(R.id.txtAddress);
            biaya_konsultasi = itemView.findViewById(R.id.txtPrice);

            // Tambahkan listener ke item view di sini
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
