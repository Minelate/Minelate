<script setup>
import {ErrorMessage, Field, Form, useField, useForm} from 'vee-validate';
import {toTypedSchema} from '@vee-validate/zod';
import * as zod from 'zod';
import {
  DialogClose,
  DialogContent,
  DialogOverlay,
  DialogPortal,
  DialogRoot,
  DialogTitle,
  DialogDescription,
  DialogTrigger
} from "reka-ui";
import {ref} from "vue";
import {z} from "zod";
import {useRuntimeConfig} from "#app";

const validationSchema = toTypedSchema(
    zod.object({
      name: z.string().regex(/^([a-z0-9-_]+\/)*[a-z0-9-_]+$/i, "Invalid path format")
    })
);

const runtimeConfig = useRuntimeConfig()

const {handleSubmit, errors} = useForm({
  validationSchema,
});

const {value: name} = useField('name');

const onSubmit = handleSubmit(async values => {
  try {
    const response = await fetch(runtimeConfig.public.apiBase + '/api/projects', {
      method: 'POST',
      body: JSON.stringify(values),
      headers: {'Content-Type': 'application/json'}
    });

    if (!response.ok) {
      const errorText = await response.text();
      console.error('Error response:', errorText);
    }
  } catch (error) {
    console.error('Fetch error:', error);
  }
  open.value = false;
});

const open = defineModel(false)
const props = defineProps(['parent'])
</script>

<template>
  <DialogRoot v-model:open="open">
    <DialogTrigger class="flex cursor-pointer">
      <button class="bg-green-950 text-green-500 text-xl font-bold p-2 flex rounded gap-2 items-center justify-center">
        <Icon name="material-symbols:add-chart-outline-rounded" class="text-2xl"/><span>Create Project</span>
      </button>
    </DialogTrigger>
    <DialogPortal>
      <Transition name="fade">
        <DialogOverlay class="fixed inset-0 flex items-center justify-center bg-black/40"/>
      </Transition>
      <Transition name="fade">
        <DialogContent
            class="text-white fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2
             rounded-md bg-minelate-background-lighter p-10 shadow-lg grid">

          <DialogTitle class="font-dyna font-bold text-2xl">New translation project</DialogTitle>
          <DialogDescription class="font-baloo mb-10">Create a new translation project to localize a Paper or Velocity plugin</DialogDescription>
          <form @submit.prevent="onSubmit"
                class="grid font-baloo"
          >
            <field name="name" type="text"
                   class="bg-gray-900 rounded text-xl border-gray-800 border-dashed border-2 p-3 focus:outline-0"
                   placeholder="e.g tutorial"/>
            <ErrorMessage name="name" class="font-baloo text-red-500 font-bold"/>
            <div class="flex justify-end mt-5">

            </div>
            <button type="submit" class="bg-green-950 text-green-500 text-xl font-bold p-2 flex rounded gap-2 items-center justify-center">
              <Icon name="material-symbols:add-chart-outline-rounded" class="text-2xl"/><span>Create Project</span>
            </button>
          </form>
          <DialogClose/>
        </DialogContent>
      </Transition>
    </DialogPortal>
  </DialogRoot>
</template>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: all 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>