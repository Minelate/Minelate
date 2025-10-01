import {ref, computed} from 'vue'
import type {Project} from "~/types";

export function useProjects() {
    const projects = ref<Project[]>([])
    const search = ref('')

    const ws = new WebSocket('ws://localhost:8080/ws/projects')

    ws.onmessage = (event) => {
        const data = JSON.parse(event.data)
        if (data.type === 'projects:init') {
            projects.value = data.projects
        }
        if (data.type === 'projects:created') {
            projects.value.push(data.project)
        }
        if (data.type === 'projects:updated') {
            const index = projects.value.findIndex(p => p.id === data.project.id)
            if (index !== -1) projects.value[index] = data.project
        }
        if (data.type === 'projects:deleted') {
            projects.value = projects.value.filter(p => p.id !== data.projectId)
        }
    }


    const filteredProjects = computed(() =>
        projects.value.filter(project =>
            project.name.toLowerCase().includes(search.value.toLowerCase())
        )
    )

    return {search, filteredProjects, projects}
}
