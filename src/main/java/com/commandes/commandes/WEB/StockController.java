package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Article;
import com.commandes.commandes.ENTITIES.Categorie;
import com.commandes.commandes.ENTITIES.Vente;
import com.commandes.commandes.REPOSITORIES.ArticleRepo;
import com.commandes.commandes.REPOSITORIES.VenteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class StockController {
    private ArticleRepo articleRepo;
    private VenteRepo venteRepo;

//Affichage de stock
    @GetMapping(path = "/stock")
    public String getAll(Model model) {
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listArticles", articles);
        List<Vente> ventes=venteRepo.findAll();
        model.addAttribute("listeVente", ventes);
        int quantiteVente;
        return "stock";

    }
    ////Page recherche-----------------------
    @GetMapping(path = "/rechercheStock")
    public String rechercheStock(Model model ){
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listArticles",articles);
        return "rechercheStock";
    }

    ////Page recherche-----------------------
    @GetMapping(path = "/searchStock")
    public String searchVente(Model model, @RequestParam String rech){
        List<Article> articles = articleRepo.findByNomarticleContains(rech);
        model.addAttribute("listArticles",articles);
        model.addAttribute("rech",rech);
        return "rechercheStock";//article.html
    }
}
