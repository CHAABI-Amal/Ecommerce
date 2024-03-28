package com.example.ecommerce.entities;

import java.io.Serializable;

public class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String designation;
    private double prix;
    private int quantite;
    private int idCategorie;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Produit() {
        super();
    }

    public Produit( String designation, double prix, int quantite, int idCategorie) {

        this.designation = designation;
        this.prix = prix;
        this.quantite = quantite;
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", idCategorie=" + idCategorie +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID du produit doit être un entier positif.");
        }
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            throw new IllegalArgumentException("La désignation du produit ne peut pas être vide.");
        }
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if (prix < 0) {
            throw new IllegalArgumentException("Le prix du produit ne peut pas être négatif.");
        }
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité du produit ne peut pas être négative.");
        }
        this.quantite = quantite;
    }
}