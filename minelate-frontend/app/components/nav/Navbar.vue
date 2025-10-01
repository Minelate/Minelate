<script setup lang="ts">
import type {MinelateUser} from "~/types";

const {user} = useAuth();
</script>
<template>

  <div
      class="text-white bg-minelate-background-light w-full flex justify-between items-center p-10 font-suse font-extrabold">
    <div class="bg-white w-15 h-15 rounded">

    </div>
    <div class="flex items-center gap-5">
      <div>
        <span>Welcome </span><span>{{ user?.preferred_username }}</span>
      </div>
      <button @click="" class="text-lg rounded flex items-center gap-3 text-blue-500 w-14 h-14 ">
        <NuxtImg v-if="user?.picture"
                 :src="user.picture"
                 :alt="user.preferred_username"
                 class="rounded"/>
        <NuxtImg v-else
                 src="/img/avatar.svg"
                 class="rounded"/>
      </button>
    </div>
  </div>
</template>