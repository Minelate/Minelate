<script setup lang="ts">
function login() {
  const base = useRuntimeConfig().public.apiBase
  navigateTo(base + '/auth/login', { external: true})
}
</script>

<template>
  <div class="bg-minelate-background-lighter text-gray-50 p-10 rounded-sm gap-3 flex flex-col items-center">
    <div class="bg-white w-40 h-40 rounded"></div>
    <h2 class="font-dyna font-bold text-5xl my-10">Minelate Login</h2>
    <button class="bg-yellow-700 p-3 font-bold rounded-sm"
    @click="login()">
      <span>Login with OAuth</span>
    </button>
  </div>
</template>

<style scoped>

</style>