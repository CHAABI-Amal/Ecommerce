package com.example.ecommerce.dao;


import com.example.ecommerce.entities.Produit;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        ProduitDaoImpl dao=new ProduitDaoImpl();
        Produit p1=dao.save(new Produit("HP 2000",900,45,1));//primary key al7am9a deja inseritih fi bdd sql plus
        Produit p2=dao.save(new Produit("Epson 20",900,45,1));
        System.out.println("chercher des produit");
        List<Produit> prods=dao.produitsParMC("%H%");
        for(Produit p:prods){
            System.out.println(p.toString());
        }
/**/
        System.out.println("test***********************************************************");
    }
}
