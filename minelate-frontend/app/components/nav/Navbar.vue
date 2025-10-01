<script setup lang="ts">
function logout() {

}
</script>

<template>
<div class="text-white bg-minelate-background-light w-full flex justify-between items-center p-10">
  <div class="bg-white w-15 h-15 rounded">

  </div>
  <button @click="logout()" class="bg-minelate-accent-dark p-4 text-2xl rounded flex items-center gap-3">
    <Icon name="solar:login-2-bold" class="text"/><span>Logout</span>
  </button>
</div>
</template>

<style scoped>

</style>