package com.commandes.commandes.REPOSITORIES;


import com.commandes.commandes.ENTITIES.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

}
