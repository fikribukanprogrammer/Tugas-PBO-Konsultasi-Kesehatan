package com.example.tugasprojek;

public class DokterModel{
    private int id_dokter;
    private int profileImage;
    private String doctorName;
    private String doctorSpesialis;
    private String doctorAddress;
    private String biaya_konsultasi;
    private String formatBiaya;

    public DokterModel() {
    }

    public DokterModel(int id_dokter,int profileImage, String doctorName, String doctorSpesialis, String doctorAddress, String biaya_konsultasi) {
        this.id_dokter = id_dokter;
        this.profileImage = profileImage;
        this.doctorName = doctorName;
        this.doctorSpesialis = doctorSpesialis;
        this.doctorAddress = doctorAddress;
        this.biaya_konsultasi= biaya_konsultasi;
    }

    public int getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(int id_dokter) {
        this.id_dokter = id_dokter;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSpesialis() {
        return doctorSpesialis;
    }

    public void setDoctorSpesialis(String doctorSpesialis) {
        this.doctorSpesialis = doctorSpesialis;
    }

    public String getDoctorAddress() {
        return doctorAddress;
    }

    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }

    public String getBiaya_konsultasi() {
        return biaya_konsultasi;
    }

    public void setBiaya_konsultasi(String biaya_konsultasi) {
        this.biaya_konsultasi = biaya_konsultasi;
    }

    public String getFormatBiaya() {
        return formatBiaya;
    }

    public void setFormatBiaya(String formatBiaya) {
        this.formatBiaya = formatBiaya;
    }
}
