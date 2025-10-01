<script setup lang="ts">
const search = defineModel()
const props = defineProps(['projectAmount'])
</script>

<template>
<div class="w-full">
  <input v-model="search" :placeholder="'Search in ' + props.projectAmount + ' projects...'" type="search"
         class="focus:outline-2 focus:outline-minelate-accent-dark rounded px-5 py-2 bg-minelate-background-lighter w-full"/>
</div>
</template>

<style scoped>

</style>