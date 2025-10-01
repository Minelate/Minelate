<template>
  <div class="overflow-hidden w-screen h-screen">
    <Navbar/>
    <div class="bg-minelate-background-dark w-full h-screen">
      <slot/>
    </div>
  </div>
</template>
<script setup lang="ts">
import Navbar from "~/components/nav/Navbar.vue";
</script>