import { defineConfig } from "vite";
import { svelte } from "@sveltejs/vite-plugin-svelte";

export default defineConfig(({ mode }) => {
  return {
    root: "./client",
    server: {
      cors: mode === "development",
    },
    build: {
      manifest: true,
      outDir: "../resources/dist/",
      emptyOutDir: true,
      rollupOptions: {
        input: "./client/main.js",    
      }
    },
    plugins: [svelte()],
    optimizeDeps: {
      include: ["@inertiajs/inertia", "@inertiajs/inertia-svelte"],
    },
  };
});
