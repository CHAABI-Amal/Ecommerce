package com.example.ecommerce.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

public class Ligne_Commande {
    private int idligne;

    private int quantite;

    //@Transient
    private float sous_total;

    private Produit produit;

    private Commande commande;

    public int getIdligne() {
        return idligne;
    }
    public void setIdligne(int idligne) {
        this.idligne = idligne;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Commande getCommande() {
        return commande;
    }
    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    @Override
    public String toString() {
        return "Ligne_Commande [id=" + idligne + ", quantite=" + quantite + "]";
    }
    public Ligne_Commande(int idligne, int quantite) {
        super();
        this.idligne = idligne;
        this.quantite = quantite;
    }
    public Ligne_Commande() {
        super();
    }
    public float getSous_total() {
        return sous_total;
    }

    public void setSous_total(float sous_total) {
        this.sous_total = sous_total;
    }
}
