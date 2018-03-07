package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import se.kth.iv1201.recruitment.entity.Person;
import se.kth.iv1201.recruitment.entity.User;
import se.kth.iv1201.recruitment.exception.UsernameAlreadyExistsException;
import se.kth.iv1201.recruitment.service.MyUserDetailsService;

import javax.validation.Valid;
import java.util.logging.Logger;

/**
 * A controller that handles traffic between the register form, and the user service used to store and retrieve from db.
 */
@Controller
public class RegisterController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    /**
     * Method that runs when the /register page receives a http GET request, to add objects necessary for registration
     * to the model.
     * @param model the model to populate with objects.
     * @return String with a view name.
     */
    @GetMapping("/register")
    public String getRegister(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("person", new Person());
        return "register";
    }

    /**
     * Method that runs when the /register page receives a http POST request from the form located on that page,
     * tries to bind the values from the form into objects that are then stored in the DB.
     * @param user the User to store.
     * @param bindingResultUser the result of the attempt to bind the form user to an object.
     * @param person the Person to store.
     * @param bindingResultPerson the result of the attempt to bind the form person to an object.
     * @param model object that carries objects between views.
     * @return String with a view name.
     * @throws UsernameAlreadyExistsException if a duplicate username is found.
     */
    @PostMapping(path="/register")
    public String postRegister(@Valid User user, BindingResult bindingResultUser,
                               @Valid Person person, BindingResult bindingResultPerson, Model model) throws UsernameAlreadyExistsException {

        if(bindingResultUser.hasErrors() || bindingResultPerson.hasErrors()) {
            return "register";
        }

        try {
            user.setPerson(person);
            userDetailsService.registerUser(user);
        } catch (UsernameAlreadyExistsException exception) {
            model.addAttribute("exception", exception);
            return "register";
        }
        return "home";
    }
/*
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ModelAndView conflict(HttpServletRequest request, UsernameAlreadyExistsException exception) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", exception);
        mav.addObject("user", new User());
        mav.addObject("person", new Person());
        mav.setViewName("register");
        return mav;
    }*/
}