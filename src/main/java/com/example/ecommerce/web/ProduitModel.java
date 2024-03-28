package com.example.ecommerce.web;

import com.example.ecommerce.entities.Categorie;
import com.example.ecommerce.entities.Produit;

import java.util.ArrayList;
import java.util.List;

public class ProduitModel {
    private String motCle;
    private List<Produit> produitsFinal=new ArrayList<Produit>();
    private List<Categorie> produits=new ArrayList<Categorie>();

    public ProduitModel(String motCle, List<Produit> produitsFinal, List<Categorie> produits) {
        this.motCle = motCle;
        this.produitsFinal = produitsFinal;
        this.produits = produits;
    }

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Produit> getProduitsFinal() {
        return produitsFinal;
    }

    public void setProduitsFinal(List<Produit> produitsFinal) {
        this.produitsFinal = produitsFinal;
    }

    public List<Categorie> getProduits() {
        return produits;
    }

    public void setProduits(List<Categorie> produits) {
        this.produits = produits;
    }

    public ProduitModel()
    {

    }

}
