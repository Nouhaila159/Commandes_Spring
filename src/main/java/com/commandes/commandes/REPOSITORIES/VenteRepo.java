package com.commandes.commandes.REPOSITORIES;

import com.commandes.commandes.ENTITIES.Article;
import com.commandes.commandes.ENTITIES.Client;
import com.commandes.commandes.ENTITIES.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenteRepo extends JpaRepository<Vente,Long> {
    List<Vente> findByClient_IdC(Long id);
    List<Vente> findByArticle_Id(Long id);
    List<Vente> findByClient_NomprenomC(String rech);

}
