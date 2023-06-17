package wsb.wsb_bugtracker.controllers;

import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.wsb_bugtracker.models.Issue;
import wsb.wsb_bugtracker.models.Mail;
import wsb.wsb_bugtracker.models.Person;
import wsb.wsb_bugtracker.repositories.IssueRepository;
import wsb.wsb_bugtracker.repositories.PersonRepository;
import wsb.wsb_bugtracker.repositories.ProjectRepository;
import wsb.wsb_bugtracker.services.MailService;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;

    public IssueController(IssueRepository issueRepository, ProjectRepository projectRepository, PersonRepository personRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }
    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("issues/index");
        modelAndView.addObject("issues", issueRepository.findAll());
        modelAndView.addObject("project", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issues/create");

        Issue issue = new Issue();
        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("issues/create");

        Issue issue = issueRepository.findById(id).orElse(null);

        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/save")
    ModelAndView save(@ModelAttribute @Valid Issue issue,
                      BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("issues/create");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("issue", issue);
            modelAndView.addObject("projects", projectRepository.findAll());
            modelAndView.addObject("people", personRepository.findAll());
            return modelAndView;
        }

        issueRepository.save(issue);

        modelAndView.setViewName("redirect:/issues");

        return modelAndView;
    }

    @Secured("ROLE_MANAGE_PROJECT")
    @GetMapping("/delete/{id}")
    public String deleteIssue(@PathVariable("id") Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Mail mail = new Mail(issue.getPerson().getEmail(),
                "Usunięto zgłoszenie "+issue.getTitle()+" id: "+issue.getId(),
                "Zgłoszenie zostało usunięte przez: "+authentication.getName()+". Należało do projektu "+issue.getProject().getName());
        MailService.send(mail);

        issueRepository.delete(issue);
        return "redirect:/issues";
    }
}
