<script setup lang="ts">
const props = defineProps(['project'])

function editProject() {

}

function deleteProject() {

}
</script>
<template>
  <div class="bg-minelate-background-lighter rounded p-5 flex justify-between h-fit">
    <div class="grid">
      <span class="text-2xl font-dyna font-bold">
        {{ props.project.name }}
      </span>
      <span class="text-gray-400">
        {{ props.project.description ? props.project.description : "No description. Edit this project to set one." }}
      </span>
    </div>
    <div class="text-2xl flex justify-center items-center gap-5">
      <button @click="editProject()" class="bg-blue-950 text-blue-500 p-2 flex rounded">
        <Icon name="solar:pen-linear" />
      </button>

      <button @click="deleteProject()" class="bg-red-950 text-red-500 p-2 flex rounded">
        <Icon name="solar:trash-bin-minimalistic-broken" />
      </button>
    </div>
  </div>
</template>

<style scoped>

</style>