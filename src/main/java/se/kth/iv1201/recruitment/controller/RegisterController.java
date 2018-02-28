package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import se.kth.iv1201.recruitment.entity.Person;
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
        Person person = new Person();
        User user = new User();
        //user.setPerson(person);
        //user.setEnabled(true);
        //user.addRole(UserRole.APPLICANT);

        model.addAttribute("user", user);
        model.addAttribute("person", person);
        return "register";
    }

    @PostMapping(path="/register")
    public String postRegister(@Valid User user, BindingResult bindingResultUser,
                               @Valid Person person, BindingResult bindingResultPerson) {

        if(bindingResultUser.hasErrors() || bindingResultPerson.hasErrors()) {
            System.out.println("REGISTER ERROR");
            return "register";
        }
        user.setEnabled(true);
        user.addRole(UserRole.APPLICANT);
        user.setPerson(person);

        userRepository.save(user);
        return "home";
    }
}