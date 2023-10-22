package com.commandes.commandes.WEB;

import com.commandes.commandes.ENTITIES.Client;
import com.commandes.commandes.ENTITIES.User;
import com.commandes.commandes.REPOSITORIES.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class SignInUpController {
    private UserRepo userRepo;

    @GetMapping(path = "/login")
    public String afflogin(Model model){
        return "login";
    }

    @PostMapping(path = "/newUser")
    public String ajout(User newUser) {
        userRepo.save(newUser);
        return "redirect:/accueil";
    }
}
