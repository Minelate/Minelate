<script setup lang="ts">
import ProjectList from "~/components/project/ProjectList.vue";
import CreateProjectModal from "~/components/project/CreateProjectModal.vue";
import type {Project} from "~/types";
const base = useRuntimeConfig().public.apiBase
const search = ref('')

const { data: projects } = await useFetch<Project[]>(() => base + `/api/projects`)

const filteredProjects = computed(() => {
  return projects.value?.filter(value => value.name.toLowerCase().includes(search.value.toLowerCase())) ?? []
})
</script>

<template>
<div class="text-white flex w-full justify-center pt-10 overflow-hidden">
  <div class="w-[80%] grid gap-5 h-fit">
    <CreateProjectModal />
    <SearchBox v-model="search"/>
    <ProjectList :projects="filteredProjects" />
  </div>
</div>
</template>

<style scoped>

</style>