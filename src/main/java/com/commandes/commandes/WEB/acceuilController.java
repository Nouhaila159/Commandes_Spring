package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.*;
import com.commandes.commandes.REPOSITORIES.ArticleRepo;
import com.commandes.commandes.REPOSITORIES.ClientRepo;
import com.commandes.commandes.REPOSITORIES.UserRepo;
import com.commandes.commandes.REPOSITORIES.VenteRepo;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@AllArgsConstructor
public class acceuilController {
    private ArticleRepo articleRepo;
    private VenteRepo venteRepo;
    private ClientRepo clientRepo;
    private UserRepo userRepo;

    @GetMapping(path = "/accueil")
    public String getAllArticle(Model model){
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listArticles", articles);


        List<Vente> ventes = venteRepo.findAll();
        model.addAttribute("listeVentes", ventes);

        List<Client> clients = clientRepo.findAll();
        model.addAttribute("listeClient", clients);

        List<User> users = userRepo.findAll();
        model.addAttribute("listeUsers", users);

        return "index";

    }


}
