package wsb.wsb_bugtracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.PersonRepository;
import wsb.wsb_bugtracker.services.PersonService;

@Controller
@RequestMapping("/account")
public class MyAccController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    @Autowired
    public MyAccController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping("/my")
    ModelAndView myacc () {
        ModelAndView modelAndView = new ModelAndView("account/index");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        System.out.println(username);
        Person persona = personRepository.findByUsername(username);

        modelAndView.addObject("people", personRepository.findById(persona.getId()).orElse(null));
        return modelAndView;
    }
    @GetMapping("/edit")
    ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView("account/create");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Person person = personRepository.findByUsername(username);

        modelAndView.addObject("person", person);

        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Person person) {

        boolean isNew = person.getId() == null;
        personService.savePerson(person);


        return "redirect:/account/my";
    }

}
