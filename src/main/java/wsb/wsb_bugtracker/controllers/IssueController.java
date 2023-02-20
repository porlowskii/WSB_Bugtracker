package wsb.wsb_bugtracker.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.wsb_bugtracker.models.Issue;
import wsb.wsb_bugtracker.models.Project;
import wsb.wsb_bugtracker.repositories.IssueRepository;
import wsb.wsb_bugtracker.repositories.ProjectRepository;

@Controller
@RequestMapping("/issues")
public class IssueController {

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;

    public IssueController(IssueRepository issueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.projectRepository = projectRepository;
    }
    @GetMapping
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("issues/index");
        modelAndView.addObject("issues", issueRepository.findAll());
        modelAndView.addObject("project", projectRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issues/create");

        Issue issue = new Issue();
        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("issues/create");

        Issue issue = issueRepository.findById(id).orElse(null);

        modelAndView.addObject("issue", issue);
        modelAndView.addObject("projects", projectRepository.findAll());

        return modelAndView;
    }

    @PostMapping("/save")
    ModelAndView save(@ModelAttribute @Valid Issue issue,
                      BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("issues/create");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("issue", issue);
            modelAndView.addObject("projects", projectRepository.findAll());
            return modelAndView;
        }

        issueRepository.save(issue);

        modelAndView.setViewName("redirect:/issues");

        return modelAndView;
    }
}
