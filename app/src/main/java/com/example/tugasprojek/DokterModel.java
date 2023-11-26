package com.example.tugasprojek;

public class DokterModel {
    private int profileImage ;
    private String docterName,docterSpesialis,docterAddres, docterRating;

    public int getProfileImage() {
        return profileImage;
    }

    public String getDocterRating() {
        return docterRating;
    }

    public String getDocterName() {
        return docterName;
    }

    public String getDocterSpesialis() {
        return docterSpesialis;
    }

    public String getDocterAddres() {
        return docterAddres;
    }

    public DokterModel(int profileImage, String docterRating, String docterName, String docterSpesialis, String docterAddres){
        this.profileImage=profileImage;
        this.docterRating=docterRating;
        this.docterName=docterName;
        this.docterSpesialis=docterSpesialis;
        this.docterAddres=docterAddres;
    }
}
