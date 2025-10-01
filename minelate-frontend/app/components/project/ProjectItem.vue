<script setup lang="ts">
const props = defineProps(['project'])

const base = useRuntimeConfig().public.apiBase

function editProject() {

}

async function deleteProject() {
  await fetch(base + '/api/projects/' + props.project.id, {
    method: 'DELETE',
    credentials: 'include',
    headers: {
      'Content-Type': 'application/json'
    }
  })
}
</script>
<template>
  <div class="bg-minelate-background-lighter rounded p-5 flex justify-between h-fit">
    <div class="grid">
      <div class="flex items-center gap-3">
        <span class="text-2xl font-dyna font-bold">
        {{ props.project.name }}
      </span>
        <span class="text-xs font-dyna font-bold text-gray-500  px-2 py-0.5 rounded-xl border-dashed border-2">
        {{ props.project.id }}
      </span>
      </div>
      <span class="text-gray-400">
        {{ props.project.description ? props.project.description : "No description. Edit this project to set one." }}
      </span>
    </div>
    <div class="text-2xl flex justify-center items-center gap-5">
      <button @click="editProject()" class="bg-blue-950 text-blue-500 p-2 flex rounded">
        <Icon name="solar:pen-linear"/>
      </button>

      <button @click="deleteProject()" class="bg-red-950 text-red-500 p-2 flex rounded">
        <Icon name="solar:trash-bin-minimalistic-broken"/>
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>