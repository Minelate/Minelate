// https://nuxt.com/docs/api/configuration/nuxt-config
import tailwindcss from "@tailwindcss/vite";

export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  ssr: false,
  css: ['~/assets/css/main.css'],

  vite: {
    plugins: [
        tailwindcss(),
    ],
  },

  modules: ['@nuxt/fonts', '@nuxt/icon', '@nuxt/image'],

  runtimeConfig: {
    public: {
      apiBase: 'http://localhost:8080',
    }
  },

  fonts: {
    defaults: {
      weights: [400, 500, 600, 700, 800, 900],
    },
    families: [
      { name: 'Baloo Paaji 2', provider: 'google' },
      { name: 'DynaPuff', provider: 'google' },
      { name: 'SUSE Mono', provider: 'google'}
    ]
  },
})