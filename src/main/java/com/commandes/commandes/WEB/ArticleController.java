package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Article;
import com.commandes.commandes.ENTITIES.Categorie;
import com.commandes.commandes.ENTITIES.Vente;
import com.commandes.commandes.REPOSITORIES.ArticleRepo;
import com.commandes.commandes.REPOSITORIES.CategorieRepo;
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
public class ArticleController {
    private ArticleRepo articleRepo;
    private CategorieRepo categorieRepo;
    private VenteRepo venteRepo;

    //affichage
    @GetMapping(path = "/articles")
    public String getAllArticle(Model model) {
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("listArticles", articles);
        List<Categorie> categories = categorieRepo.findAll();
        model.addAttribute("listeCategorie", categories);
        System.out.println(categories);
        return "article";

    }

    //ajout article---------------------------Categorie
    @GetMapping(path = "/addArticles")
    public String AddArticle(Model model) {
        List<Categorie> categories = categorieRepo.findAll();
        model.addAttribute("listeCategorie", categories);
        return "addArticles";
    }

    @PostMapping(path = "/newArticle")
    public String ajout(Article newArticle) {
        articleRepo.save(newArticle);
        return "redirect:/articles";
    }

    @PostMapping(path = "/newCategorie")
    public String ajoutCat(Categorie newCategorie) {
        categorieRepo.save(newCategorie);
        return "redirect:/articles";
    }

    //----------------------------modification-----------------
    @GetMapping(path = "/updateArticle")
    public String update(Model model, @RequestParam Long id) {
        List<Categorie> categories = categorieRepo.findAll();
        model.addAttribute("listeCategorie", categories);
        Article a = articleRepo.findById(id).orElse(null);
        model.addAttribute("update_article", a);

        return "updateArticle";

    }


    //-----------------------delete categorie-> supp article------------------------
    @GetMapping(path = "/deleteCategorie")
    public String delCat(@RequestParam Long id) {
        Categorie c = categorieRepo.findById(id).orElse(null);
        if (c != null) {
            List<Article> articles = articleRepo.findByCategorie_Idcategorie(id);
            for (Article a : articles) {
                articleRepo.delete(a);
            }
            categorieRepo.delete(c);
        }

        return "redirect:/articles";
    }

    //--------------------------supprimer article--> supp vente------------------------
    @GetMapping(path = "/deleteArticle")
    public String delArticle(@RequestParam Long id) {
        Article a = articleRepo.findById(id).orElse(null);
        if (a != null) {
            List<Vente> ventes = venteRepo.findByArticle_Id(id);
            venteRepo.deleteAll(ventes);
            articleRepo.delete(a);
        }

        return "redirect:/articles";
    }
    ////Page recherche-----------------------
    @GetMapping(path = "/rechercheArticle")
    public String rechercheArticle(Model model ){
        List<Article> articles = articleRepo.findAll();
        model.addAttribute("lisArticles",articles);
        return "rechercheArticle";
    }

    ////Page recherche-----------------------
    @GetMapping(path = "/searchArticle")
    public String searchArticle(Model model, @RequestParam String rech ){
        List<Article> articles = articleRepo.findByNomarticleContains(rech);
        model.addAttribute("ArticleChercher",articles);
        model.addAttribute("rech",rech);
        return "rechercheArticle";//article.html
    }
}


