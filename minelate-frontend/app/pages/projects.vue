<script setup lang="ts">
import ProjectList from "~/components/project/ProjectList.vue";
import CreateProjectModal from "~/components/project/CreateProjectModal.vue";
import {useProjects} from "~/composables/useProjects";

const { search, projects, filteredProjects} = useProjects()
</script>

<template>
<div class="text-white flex w-full justify-center pt-10 overflow-hidden">
  <ClientOnly>
    <div class="w-[80%] grid gap-5 h-fit">
      <div class="flex justify-between items-center">
        <span>
          Showing {{ filteredProjects.length }} of {{ projects.length }} Projects
        </span>
        <CreateProjectModal />
      </div>
      <SearchBox v-model="search" :project-amount="projects.length"/>
      <ProjectList :projects="filteredProjects" />
    </div>
  </ClientOnly>
</div>
</template>

<style scoped>

</style>