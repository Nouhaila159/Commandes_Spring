package com.commandes.commandes.REPOSITORIES;

import com.commandes.commandes.ENTITIES.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client,Long> {
    List<Client> findByNomprenomC(String rech);
}
