package wsb.wsb_bugtracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import wsb.wsb_bugtracker.models.Issue;
import wsb.wsb_bugtracker.models.Project;
import wsb.wsb_bugtracker.repositories.IssueRepository;
import wsb.wsb_bugtracker.repositories.ProjectRepository;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;
    public ProjectController (ProjectRepository projectRepository, IssueRepository issueRepository) { this.projectRepository = projectRepository;
        this.issueRepository = issueRepository;
    }

    @GetMapping
    ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView("projects/index");
        modelAndView.addObject("projects", projectRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("projects/create");

        Project project = new Project();
        modelAndView.addObject("project", project);

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projects/create");

        Project project = projectRepository.findById(id).orElse(null);

        modelAndView.addObject("project", project);

        return modelAndView;
    }

    @PostMapping("/save")
    String save(@ModelAttribute Project project) {

        boolean isNew = project.getId() == null;

        projectRepository.save(project);

        return "redirect:/projects";
    }
}
