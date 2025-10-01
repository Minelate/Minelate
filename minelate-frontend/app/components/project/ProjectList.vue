<script setup lang="ts">
const props = defineProps(['projects'])

</script>
<template>
  <div class="rounded w-full flex flex-col gap-5 overflow-scroll h-[40vh]">
    <ProjectItem v-for="project in projects " :project="project" />
  </div>
</template>

<style scoped>

</style>