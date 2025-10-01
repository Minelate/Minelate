// https://nuxt.com/docs/api/configuration/nuxt-config
import tailwindcss from "@tailwindcss/vite";

export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  ssr: true,
  css: ['~/assets/css/main.css'],

  vite: {
    plugins: [
        tailwindcss(),
    ],
  },

  modules: ['@nuxt/fonts', '@nuxt/icon'],

  runtimeConfig: {
    public: {
      apiBase: 'http://localhost:8080',
    }
  },

  fonts: {
    families: [
      { name: 'Comic Relief', provider: 'google' },
      { name: 'Baloo Paaji 2', provider: 'google' },
      { name: 'DynaPuff', provider: 'google' },
    ]
  },
})