package dev.thezexquex.minelate.websocket;

import dev.thezexquex.minelate.model.Project;
import dev.thezexquex.minelate.repository.ProjectRepository;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
@ServerWebSocket("/ws/projects")
@Secured(SecurityRule.IS_ANONYMOUS)
public class ProjectWebSocket {

    private final WebSocketBroadcaster broadcaster;
    private final ObjectMapper mapper;
    private final ProjectRepository projectRepository;
    private final Logger logger = Logger.getLogger(ProjectWebSocket.class.getName());

    @Inject
    public ProjectWebSocket(WebSocketBroadcaster broadcaster, ObjectMapper mapper, ProjectRepository projectRepository) {
        this.broadcaster = broadcaster;
        this.mapper = mapper;
        this.projectRepository = projectRepository;
    }

    @OnOpen
    public void onOpen(WebSocketSession session) throws Exception {
        Iterable<Project> projects = projectRepository.findAll();
        String payload = mapper.writeValueAsString(Map.of("type", "projects:init", "projects", projects));
        session.sendSync(payload);
    }

    @OnMessage
    public void onMessage(String message, WebSocketSession session) throws Exception {
    }

    @OnClose
    public void onClose(WebSocketSession session) {}

    @OnError
    public void onError(Throwable error) {
        logger.log(Level.SEVERE, "Error in project websocket", error);
    }

    public void broadcastProjectCreated(Project project) {
        try {
            String payload = mapper.writeValueAsString(Map.of("type", "projects:created", "project", project));
            broadcaster.broadcastSync(payload);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to broadcast project creation", e);
        }
    }

    public void broadcastProjectUpdated(Project project) {
        try {
            String payload = mapper.writeValueAsString(Map.of("type", "projects:updated", "project", project));
            broadcaster.broadcastSync(payload);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to broadcast project update", e);
        }
    }

    public void broadcastProjectDeleted(String projectId) {
        try {
            String payload = mapper.writeValueAsString(Map.of("type", "projects:deleted", "projectId", projectId));
            broadcaster.broadcastSync(payload);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to broadcast project deletion", e);
        }
    }
}
