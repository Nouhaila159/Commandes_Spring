package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Client;
import com.commandes.commandes.ENTITIES.User;
import com.commandes.commandes.REPOSITORIES.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private UserRepo userRepo;

    @GetMapping(path = "/users")
    public String AffUser(Model model) {
        List<User> users = userRepo.findAll();
        model.addAttribute("listeUsers", users);
        return "users";
    }
}
