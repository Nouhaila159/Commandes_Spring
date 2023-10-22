package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Article;
import com.commandes.commandes.ENTITIES.Categorie;
import com.commandes.commandes.ENTITIES.Client;
import com.commandes.commandes.ENTITIES.Vente;
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
public class ClientController {

    private ClientRepo clientRepo;
    private VenteRepo venteRepo;

    //affichage
    //ajout-----------------------------------
    @GetMapping(path = "/clients")
    public String AddClient(Model model) {
        List<Client> clients = clientRepo.findAll();
        model.addAttribute("listclients", clients);
        return "clients";
    }

    @PostMapping(path = "/newClient")
    public String ajout(Client newClient) {
        clientRepo.save(newClient);
        return "redirect:/clients";
    }

    //----------------------------modification-----------------
    @GetMapping(path = "/updateClient")
    public String update(Model model, @RequestParam Long idC) {
        Client c = clientRepo.findById(idC).orElse(null);
        model.addAttribute("update_client", c);

        return "updateClient";

    }


    //--------------------------supprimer
    @GetMapping(path = "/deleteClient")
    public String delClient(@RequestParam Long idC) {
        Client c = clientRepo.findById(idC).orElse(null);
        if (c != null) {
            List<Vente> ventes = venteRepo.findByClient_IdC(idC);
            venteRepo.deleteAll(ventes);
            clientRepo.delete(c);
        }

        return "redirect:/clients";
    }
    ////Page recherche-----------------------
    @GetMapping(path = "/rechercheClient")
    public String rechercheClient(Model model ){
        List<Client> clients = clientRepo.findAll();
        model.addAttribute("listclients",clients);
        return "rechercheClient";
    }

    ////Page recherche-----------------------
    @GetMapping(path = "/searchClient")
    public String searchClient(Model model, @RequestParam String rech){
        List<Client> clients = clientRepo.findByNomprenomC(rech);
        model.addAttribute("listclients",clients);
        model.addAttribute("rech",rech);
        return "rechercheClient";//article.html
    }


}
