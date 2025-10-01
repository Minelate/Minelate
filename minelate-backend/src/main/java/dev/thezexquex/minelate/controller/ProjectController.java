package dev.thezexquex.minelate.controller;

import dev.thezexquex.minelate.model.Project;
import dev.thezexquex.minelate.repository.ProjectRepository;
import dev.thezexquex.minelate.websocket.ProjectWebSocket;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.util.Optional;

@Controller("/api/projects")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectWebSocket projectWebSocket;

    public ProjectController(ProjectRepository repo, ProjectWebSocket projectWebSocket) {
        this.projectRepository = repo;
        this.projectWebSocket = projectWebSocket;
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
        Project savedProject = projectRepository.save(project);
        projectWebSocket.broadcastProjectCreated(savedProject);
    }

    @Put("/{id}")
    public void update(@Body Project project, String id) {
        project.setId(id);
        Project updatedProject = projectRepository.update(project);
        projectWebSocket.broadcastProjectUpdated(updatedProject);
    }

    @Delete("/{id}")
    public void delete(String id) {
        projectRepository.deleteById(id);
        projectWebSocket.broadcastProjectDeleted(id);
    }
}
