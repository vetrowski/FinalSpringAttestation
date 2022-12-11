package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


// http:localhost:8080/authentication/login
@Controller
@RequestMapping("/authentication")
public class AuthenticationController {
    private final PersonValidator personValidator;

    private final PersonService personService;

    @Autowired
    public AuthenticationController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        personService.register(person);
        return "redirect:/login";
    }

    @GetMapping("/change")
    public String changePassword(Model model){
        model.addAttribute("person", new Person());
        return "change";
    }

    @PostMapping("/change")
    public String changePassword(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        personValidator.findUser(person, bindingResult);
        Person person_db = personService.getPersonFindByLogin(person);

        if (bindingResult.hasErrors()){
            return "change";
        }

        int id = person_db.getId();
        String password = person.getPassword();

        personService.changePassword(id, password);
        return "redirect:/authentication/login";
    }
}
