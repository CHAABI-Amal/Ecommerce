package com.example.ecommerce.dao;


import com.example.ecommerce.entities.Categorie;
import com.example.ecommerce.entities.Produit;

import java.util.List;

public interface IProduitDao {
    public Produit save(Produit p);
    public List<Produit> produitsParMC(String mc);
    public Produit getProduit(int id);
    public Produit update(Produit p);
    public void deleteProduit(int id);


}
