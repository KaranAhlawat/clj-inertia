import { defineConfig } from "vite";
import { svelte } from "@sveltejs/vite-plugin-svelte";

export default defineConfig({
  root: "./client",
  build: {
    manifest: true,
    outDir: "../resources/",
    emptyOutDir: true,
  },
  plugins: [svelte()],
  optimizeDeps: {
    include: [
      "@inertiajs/inertia",
      "@inertiajs/inertia-svelte",
    ],
  },
});
