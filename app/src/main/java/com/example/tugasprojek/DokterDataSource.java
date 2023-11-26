package com.example.tugasprojek;

import java.util.ArrayList;
import java.util.List;

public class DokterDataSource {
    List<DokterModel>DokterModels;
    public List<DokterModel> getDokterModels(){
        DokterModels = new ArrayList<>();
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));
        DokterModels.add(new DokterModel(R.drawable.personcircle4,"5,5", "M. Adhim Rahman", "Spesialis Penyakit Dalam", "Somba Opu, Sulawesi Selatan"));

        return DokterModels;

    }
}
