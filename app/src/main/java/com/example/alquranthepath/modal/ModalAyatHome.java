package com.example.alquranthepath.modal;

public class ModalAyatHome {
    private String asma, name, arti;
    private String nomor;

    public ModalAyatHome(String asma, String name, String arti, String nomor) {
        this.asma = asma;
        this.name = name;
        this.arti = arti;
        this.nomor = nomor;
    }

    public String getAsma() {
        return asma;
    }

    public void setAsma(String asma) {
        this.asma = asma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArti() {
        return arti;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }
}
