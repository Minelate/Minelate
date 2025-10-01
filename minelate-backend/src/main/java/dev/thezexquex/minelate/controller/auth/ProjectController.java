package dev.thezexquex.minelate.controller.auth;

import dev.thezexquex.minelate.model.Project;
import dev.thezexquex.minelate.repository.ProjectRepository;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.Optional;

@Controller("api/projects")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository repo) {
        this.projectRepository = repo;
    }

    @Get("/{id}")
    public Optional<Project> get(String id) {
        return projectRepository.findById(id);
    }

    @Get
    public Iterable<Project> list() {
        return projectRepository.findAll();
    }

    @Post
    public void create(@Body Project project) {
        projectRepository.save(project);
    }

    @Put("/{id}")
    public void update(@Body Project project, String id) {
        project.setId(id);
        projectRepository.update(project);
    }

    @Delete("/{id}")
    public void delete(String id) {
        projectRepository.deleteById(id);
    }
}
