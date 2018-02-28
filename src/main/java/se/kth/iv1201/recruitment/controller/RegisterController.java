package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import se.kth.iv1201.recruitment.entity.User;
import se.kth.iv1201.recruitment.entity.UserRole;
import se.kth.iv1201.recruitment.repository.UserRepository;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String getRegister(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(path="/register")
    public String postRegister(@Valid User user, BindingResult result) {

        user.setEnabled(true);
        user.addRole(UserRole.APPLICANT);

        if(result.hasErrors()) {
            return "register";
        }

        userRepository.save(user);
        return "redirect:/login?reg=success";
    }
}