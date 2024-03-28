package com.example.ecommerce.web;

import com.example.ecommerce.dao.IProduitDao;
import com.example.ecommerce.dao.ProduitDaoImpl;
import com.example.ecommerce.entities.Categorie;
import com.example.ecommerce.entities.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@WebServlet(name="cs", urlPatterns={"*.php"})
public class ControleurServlet extends HttpServlet {
    private IProduitDao metier;
    public void init() throws ServletException {
        metier =new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/index.php")) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        if (path.equals("/chercher.php")) {
            String motCle = req.getParameter("motCle");
            ProduitModel model = new ProduitModel();
            model.setMotCle(motCle);
            List<Produit> produits = metier.produitsParMC("%" + motCle + "%");
            model.setProduitsFinal(produits);
            req.setAttribute("model", model);
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        } else if (path.equals("/Saisie.php")) {
            req.setAttribute("produit", new Produit());
            req.getRequestDispatcher("SaisieProduit.jsp").forward(req, resp);

        } else if (path.equals("/SaveProduit.php") && (req.getMethod().equals("POST"))) {
            String des = req.getParameter("designation");
            double prix = Double.parseDouble(req.getParameter("prix"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            int idCategorie = Integer.parseInt(req.getParameter("idCategorie"));
            Produit p = metier.save(new Produit(des, prix, quantite, idCategorie));
            req.setAttribute("produit", p);
            req.getRequestDispatcher("Confimation.jsp").forward(req, resp);
        } else if (path.equals("/Supprimer.php")) {
            int id = Integer.parseInt(req.getParameter("id"));
            metier.deleteProduit(id);
            //req.getRequestDispatcher("index.jsp").forward(req,resp);
            resp.sendRedirect("chercher.php?motCle=");
        } else if (path.equals("/SupprimerPanier.php")) {
            int id = Integer.parseInt(req.getParameter("id"));

            // Récupérer la liste des produits dans le panier depuis la session
            List<Produit> produitsDansPanier = (List<Produit>) req.getSession().getAttribute("panier");

            if (produitsDansPanier != null) {
                // Parcourir la liste pour trouver le produit à supprimer
                Iterator<Produit> iterator = produitsDansPanier.iterator();
                while (iterator.hasNext()) {
                    Produit produit = iterator.next();
                    if (produit.getId() == id) {
                        // Supprimer le produit de la liste
                        iterator.remove();
                        break;
                    }
                }
            }

            // Mettre à jour la liste des produits dans la session
            req.getSession().setAttribute("panier", produitsDansPanier);

            // Rediriger vers la page JSP du panier
            resp.sendRedirect("Panier.php?motCle=");
        } else if (path.equals("/Edit.php")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Produit p = metier.getProduit(id);
            req.setAttribute("produit", p);
            req.getRequestDispatcher("EditProduit.jsp").forward(req, resp);
        } else if (path.equals("/UpdateProduit.php") && (req.getMethod().equals("POST"))) {
            int id = Integer.parseInt(req.getParameter("id"));
            String des = req.getParameter("designation");
            double prix = Double.parseDouble(req.getParameter("prix"));
            int quantite = Integer.parseInt(req.getParameter("quantite"));
            int idCategorie = Integer.parseInt(req.getParameter("idCategorie"));
            Produit p = new Produit(des, prix, quantite, idCategorie);
            p.setId(id);
            metier.update(p);
            req.setAttribute("produit", p);
            req.getRequestDispatcher("Confimation.jsp").forward(req, resp);
        } else if (path.equals("/Commande.php")) {
            req.setAttribute("produit", new Produit());
            req.getRequestDispatcher("CommandeProduit.jsp").forward(req, resp);

        } else if (path.equals("/chercherPourCommander.php")) {
            String motCle = req.getParameter("motCle");
            ProduitModel model = new ProduitModel();
            model.setMotCle(motCle);
            List<Produit> produits = metier.produitsParMC("%" + motCle + "%");
            model.setProduitsFinal(produits);
            req.setAttribute("model", model);
            req.getRequestDispatcher("CommandeProduit.jsp").forward(req, resp);

        } else if ("/Panier.php".equals(path)) {
            String idParam = req.getParameter("id");
            if (idParam != null) {
                int id = Integer.parseInt(idParam);

                ProduitDaoImpl pi = new ProduitDaoImpl();
                Produit p = pi.getProduit(id); // Récupérer le produit par son ID depuis la base de données

                // Récupérer la quantité disponible dans le stock
                int qteDisponible = p.getQuantite();

                // Récupérer la quantité dans le panier (si le produit est déjà dans le panier)
                int qteDansPanier = 0; // Initialiser à 0 si le produit n'est pas encore dans le panier
                List<Produit> produitsDansPanier = (List<Produit>) req.getSession().getAttribute("panier");

                if (produitsDansPanier != null) {
                    for (Produit produit : produitsDansPanier) {
                        if (produit.getId() == p.getId()) {
                            qteDansPanier = produit.getQuantite(); // Récupérer la quantité dans le panier
                            break;
                        }
                    }
                }

                // Demander la quantité à ajouter au panier
                int qteAAjouter = qteDansPanier + 1;

                // Vérifier si la quantité totale demandée dépasse la quantité disponible dans le stock
                if (qteAAjouter > qteDisponible) {
                    // Si la quantité totale demandée dépasse la quantité disponible dans le stock, afficher un message d'erreur
                    req.getSession().setAttribute("errorMessage", "La quantité maximale disponible dans le stock est atteinte pour ce produit.");
                    // Rediriger vers la page JSP de commandeProduit.jsp pour afficher le message d'erreur
                    resp.sendRedirect("CommandeProduit.jsp");
                    return; // Arrêter l'exécution de cette méthode pour éviter la redirection vers Panier.jsp
                } else {
                    // Supprimer le message d'erreur de la session après l'avoir affiché une fois
                    req.getSession().removeAttribute("errorMessage");

                    // Mettre à jour la quantité dans le panier
                    if (produitsDansPanier == null) {
                        produitsDansPanier = new ArrayList<>();
                    }
                    boolean produitExisteDansPanier = false;
                    for (Produit produit : produitsDansPanier) {
                        if (produit.getId() == p.getId()) {
                            produitExisteDansPanier = true;
                            produit.setQuantite(qteAAjouter);
                            break;
                        }
                    }
                    if (!produitExisteDansPanier) {
                        Produit produitPanier = new Produit();
                        produitPanier.setId(p.getId());
                        produitPanier.setDesignation(p.getDesignation());
                        produitPanier.setPrix(p.getPrix());
                        produitPanier.setQuantite(qteAAjouter);
                        produitsDansPanier.add(produitPanier);
                    }
                    req.getSession().setAttribute("panier", produitsDansPanier);
                }
            }

            req.getSession().removeAttribute("errorMessage"); // Ajouter cette ligne pour supprimer le message d'erreur
            resp.sendRedirect("Panier.jsp");
        }



        else if (path.equals("/Login.php")) {
                // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
                req.getRequestDispatcher("Login.jsp").forward(req, resp);

        }
        else if (path.equals("/creerCompte.php")) {
            // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
            req.getRequestDispatcher("creerCompte.jsp").forward(req, resp);

        }
        else if (path.equals("/informationPersonneles.php")) {
            // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
            req.getRequestDispatcher("informationPersonneles.jsp").forward(req, resp);

        }else if (path.equals("/ToPanier.php")) {
            // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
            req.getRequestDispatcher("Panier.jsp").forward(req, resp);

        }
        else if (path.equals("/SaisiePaiement.php")) {
            // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
            req.getRequestDispatcher("Paiement.jsp").forward(req, resp);

        }else if (path.equals("/CommandeProduitApresValidePaiement.php")) {
            // req.setAttribute("produit",new Produit()); il faux passer produits dans panier
            req.getRequestDispatcher("CommandeProduit.jsp").forward(req, resp);

        }else if (path.equals("/ToIndex.php")) {
            List<Produit> produitsDansPanier = (List<Produit>) req.getSession().getAttribute("panier");
            if (produitsDansPanier != null) {
                ProduitDaoImpl produitDao = new ProduitDaoImpl();
                produitDao.decrementQuantities(produitsDansPanier);

                // Vider le panier en supprimant son contenu de la session
                req.getSession().removeAttribute("panier");
                req.getSession().setAttribute("confirmationPaiement", "Le paiement a été effectué avec succès !");
                req.setAttribute("message", "Le paiement a été effectué avec succès !");

            }
            req.getRequestDispatcher("CommandeProduit.jsp").forward(req, resp);
        }


        else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }

        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}