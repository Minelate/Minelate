<template>
  <div class="w-screen h-screen">
    <Navbar/>
    <div class="bg-minelate-background-dark w-full min-h-screen">
      <slot/>
    </div>
  </div>
</template>
<script setup lang="ts">
import Navbar from "~/components/nav/Navbar.vue";
</script>