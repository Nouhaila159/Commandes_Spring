package com.commandes.commandes.REPOSITORIES;

import com.commandes.commandes.ENTITIES.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article,Long> {
    List<Article> findByCategorie_Idcategorie(Long id);

    List<Article> findByNomarticleContains(String rech);

}
