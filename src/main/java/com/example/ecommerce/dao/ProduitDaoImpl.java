package com.example.ecommerce.dao;


import com.example.ecommerce.entities.Categorie;
import com.example.ecommerce.entities.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDaoImpl implements IProduitDao{

    @Override
    public Produit save(Produit p) {
        Connection connection= DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO produit(designation,prix,quantite,idCategorie) VALUES (?,?,?,?)"
            );

            ps.clearParameters();
            // ps.setInt(1,p.getId());
            ps.setString(1,p.getDesignation());
            ps.setDouble(2,p.getPrix());
            ps.setInt(3,p.getQuantite());
            ps.setInt(4,p.getIdCategorie());
            ps.executeUpdate();
            PreparedStatement ps2=connection.prepareStatement(
                    "SELECT MAX(id) as MAX_ID FROM produit"
            );
            ResultSet rs=ps2.executeQuery();
            if(rs.next()){
                p.setId(rs.getInt("MAX_ID"));
            }

            /* */
            ps.close();

            //    connection.close();//ne ferme pas dans singletonnnnnnnnnn
        }catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Produit> produitsParMC(String mc) {
        List<Produit> produitsFinal = new ArrayList<Produit>();
        Connection connection = DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id FROM categorie WHERE type LIKE ?");
            ps.setString(1, mc);
            ResultSet rs = ps.executeQuery();

            int idCategorie = -1; // Initialize with a default value
            if (rs.next()) {
                idCategorie = rs.getInt("id"); // Retrieve the idCategorie value
            }

            // Second PreparedStatement using the retrieved idCategorie value
            PreparedStatement ps2 = connection.prepareStatement(
                    "SELECT * FROM produit WHERE idCategorie = ?");

            ps2.setInt(1, idCategorie); // Set the idCategorie value from the first query
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                Produit p = new Produit();
                p.setId(rs2.getInt("id"));
                p.setDesignation(rs2.getString("designation"));
                p.setPrix(rs2.getDouble("prix"));
                p.setQuantite(rs2.getInt("quantite"));
                p.setIdCategorie(rs2.getInt("idCategorie"));

                produitsFinal.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    System.out.println(produitsFinal);
        return produitsFinal;
    }

    @Override
    public Produit getProduit(int id) {
        Produit p=null;
        Connection connection = DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM produit WHERE id = ?");

            ps.setInt(1,id); // Set the idCategorie value from the first query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p = new Produit();
                p.setId(rs.getInt("id"));
                p.setDesignation(rs.getString("designation"));
                p.setPrix(rs.getDouble("prix"));
                p.setQuantite(rs.getInt("quantite"));
                p.setIdCategorie(rs.getInt("idCategorie"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public Produit update(Produit p) {
        Connection connection= DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE produit set designation=?, prix=?, quantite=?, idCategorie=? where id=?"
            );

            ps.clearParameters();
            // ps.setInt(1,p.getId());
            ps.setString(1,p.getDesignation());
            ps.setDouble(2,p.getPrix());
            ps.setInt(3,p.getQuantite());
            ps.setInt(4,p.getIdCategorie());
            ps.setInt(5,p.getId());
            ps.executeUpdate();
            ps.close();

            //    connection.close();//ne ferme pas dans singletonnnnnnnnnn
        }catch(SQLException e){
            e.printStackTrace();
        }
        return p;
    }
    public int getQuantity(int productId) {
        Connection connection = DB.getConnection();
        int quantity = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT quantite FROM produit WHERE id = ?");
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantite");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public void update(int productId, int newQuantity) {
        Connection connection = DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE produit SET quantite = ? WHERE id = ?");
            ps.setInt(1, newQuantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void decrementQuantities(List<Produit> produitsDansPanier) {
        // Parcourez la liste des produits dans le panier
        for (Produit produit : produitsDansPanier) {
            // Récupérez l'identifiant du produit et la quantité dans le panier
            int productId = produit.getId();
            int quantityInCart = produit.getQuantite();

            // Récupérez la quantité disponible dans la base de données
            int quantityInDatabase = getQuantity(productId);

            // Mettez à jour la quantité dans la base de données
            int newQuantity = quantityInDatabase - quantityInCart;
            update(productId, newQuantity);
        }
    }

    @Override
    public void deleteProduit(int id) {
        Connection connection= DB.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "delete from produit where id=?"
            );

            ps.clearParameters();
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            //    connection.close();//ne ferme pas dans singletonnnnnnnnnn
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
