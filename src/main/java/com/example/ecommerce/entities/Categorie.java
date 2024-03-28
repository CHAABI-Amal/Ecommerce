package com.example.ecommerce.entities;

public class Categorie {
    private int idCategorie;
    private String type;

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", type='" + type + '\'' +
                '}';
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Categorie(int idCategorie, String type) {
        this.idCategorie = idCategorie;
        this.type = type;
    }
}
