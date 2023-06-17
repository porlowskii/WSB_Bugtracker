package wsb.wsb_bugtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.wsb_bugtracker.models.Authority;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.AuthorityRepository;
import wsb.wsb_bugtracker.repositories.PersonRepository;
import wsb.wsb_bugtracker.services.PersonService;

import java.util.List;

@Controller
@RequestMapping("/people")
@Secured("ROLE_USER_TAB")
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public PersonController(PersonRepository personRepository, PersonService personService, AuthorityRepository authorityRepository) {
        this.personRepository = personRepository;
        this.personService = personService;
        this.authorityRepository = authorityRepository;
    }
    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("people/index");
        modelAndView.addObject("people", personRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("people/create");

        Person person = new Person();
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_USERS")
    ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("people/create");

        Person person = personRepository.findById(id).orElse(null);
        List<Authority> authority = authorityRepository.findAll();


        modelAndView.addObject("person", person);
        modelAndView.addObject("authorities", authority);

        return modelAndView;
    }

    @PostMapping("/save")
    @Secured("ROLE_MANAGE_USERS")
    String save(@ModelAttribute Person person) {

        boolean isNew = person.getId() == null;
//        personRepository.save(person);
        personService.savePerson(person);


        return "redirect:/people";
    }

}
