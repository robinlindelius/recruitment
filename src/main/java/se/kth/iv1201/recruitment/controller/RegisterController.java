package se.kth.iv1201.recruitment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import se.kth.iv1201.recruitment.entity.Person;
import se.kth.iv1201.recruitment.entity.User;
import se.kth.iv1201.recruitment.entity.UserRole;
import se.kth.iv1201.recruitment.exception.UsernameAlreadyExistsException;
import se.kth.iv1201.recruitment.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String getRegister(Model model) {
        User user = new User();
        Person person = new Person();

        model.addAttribute("user", user);
        model.addAttribute("person", person);
        return "register";
    }

    @PostMapping(path="/register")
    public String postRegister(@Valid User user, BindingResult bindingResultUser,
                               @Valid Person person, BindingResult bindingResultPerson) throws UsernameAlreadyExistsException {

        if(bindingResultUser.hasErrors() || bindingResultPerson.hasErrors()) {
            return "register";
        }

        if(userRepository.findOne(user.getUsername()) != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        user.setEnabled(true);
        user.addRole(UserRole.APPLICANT);
        user.setPerson(person);

        userRepository.save(user);
        return "home";
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ModelAndView conflict(HttpServletRequest request, UsernameAlreadyExistsException exception) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", exception);
        mav.addObject("user", new User());
        mav.addObject("person", new Person());
        mav.setViewName("register");
        return mav;
    }
}