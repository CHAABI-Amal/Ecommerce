package com.example.ecommerce.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.Date;
import java.util.List;

public class Commande {
    private int idcmd;

    private Date datecmd;

   // @Transient
    private float total;

    private List<Ligne_Commande> lignes;

    public int getIdcmd() {
        return idcmd;
    }
    public void setIdcmd(int idcmd) {
        this.idcmd = idcmd;
    }
    public Date getDatecmd() {
        return datecmd;
    }
    public void setDatecmd(Date datecmd) {
        this.datecmd = datecmd;
    }

    public Commande(int idcmd, Date datecmd) {
        super();
        this.idcmd = idcmd;
        this.datecmd = datecmd;
    }





    @Override
    public String toString() {
        return "Commande [idcmd=" + idcmd + ", datecmd=" + datecmd + "]";
    }
    public Commande() {
        super();
    }
    public float getTotal() {
        return total;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public List<Ligne_Commande> getLignes() {
        return lignes;
    }
    public void setLignes(List<Ligne_Commande> lignes) {
        this.lignes = lignes;
    }
}
