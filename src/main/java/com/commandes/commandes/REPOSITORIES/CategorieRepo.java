package com.commandes.commandes.REPOSITORIES;


import com.commandes.commandes.ENTITIES.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie,Long> {
}
