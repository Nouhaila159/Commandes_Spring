package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Article;
import com.commandes.commandes.ENTITIES.Categorie;
import com.commandes.commandes.ENTITIES.Client;
import com.commandes.commandes.ENTITIES.Vente;
import com.commandes.commandes.REPOSITORIES.ArticleRepo;
import com.commandes.commandes.REPOSITORIES.CategorieRepo;
import com.commandes.commandes.REPOSITORIES.ClientRepo;
import com.commandes.commandes.REPOSITORIES.VenteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class VenteController {
    private VenteRepo venteRepo;
    private ArticleRepo articleRepo;
    private ClientRepo clientRepo;

    //affichage
    @GetMapping(path = "/ventes")
    public String getAllVentes(Model model) {
        List<Vente> ventes = venteRepo.findAll();
        model.addAttribute("listeVentes", ventes);
        //client
        List<Client> clients = clientRepo.findAll();
        model.addAttribute("listeClients", clients);
        //article
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listeArticles", articles);
        return "ventes";

    }

    @PostMapping(path = "/newVente")
    public String ajout(Vente newVente) {
        venteRepo.save(newVente);
        return "redirect:/ventes";
    }


  //----------------------------modification-----------------
    @GetMapping(path = "/updateVente")
    public String update(Model model, @RequestParam Long id) {
        //client
        List<Client> clients = clientRepo.findAll();
        model.addAttribute("listeClients", clients);
        //article
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listeArticles", articles);
        //Vente
        Vente v = venteRepo.findById(id).orElse(null);
        model.addAttribute("update_vente", v);

        return "updateVente";

    }



    //--------------------------supprimer
    @GetMapping(path = "/deleteVente")
    public String delArticle(@RequestParam Long id) {
        Vente v = venteRepo.findById(id).orElse(null);
        if (v != null) {
            venteRepo.delete(v);
        }

        return "redirect:/ventes";
    }
    ////Page recherche-----------------------
    @GetMapping(path = "/rechercheVente")
    public String rechercheVente(Model model ){
        List<Vente> ventes = venteRepo.findAll();
        model.addAttribute("VenteChercher",ventes);
        return "rechercheVente";
    }

    ////Page recherche-----------------------
    @GetMapping(path = "/searchVente")
    public String searchVente(Model model, @RequestParam String rech ){
        List<Vente> ventes = venteRepo.findByClient_NomprenomC(rech);
        model.addAttribute("VenteChercher",ventes);
        model.addAttribute("rech",rech);
        return "rechercheVente";//article.html
    }
}

