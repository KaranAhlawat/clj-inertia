import { defineConfig } from "vite";
// Using the vite Svelte plugin, otherwise Vite will throw a parser error.
import { svelte } from "@sveltejs/vite-plugin-svelte";

export default defineConfig(({ mode }) => {
  return {
    // Set root to the client folder, to keep stuff clean
    root: "./client",
    server: {
      // Only allow CORS if we're in development
      cors: mode === "development",
    },
    build: {
      // Generate a manifest, so we can dynamically import the bundled main.js
      manifest: true,
      // This is where it'll put the build output
      outDir: "../resources/dist/",
      emptyOutDir: true,
      rollupOptions: {
        // We're telling Rollup that our main entry point is main.js
        input: "./client/main.js",    
      }
    },
    plugins: [svelte()],
    // Does what it says, I'm guessing. Don't ask me :D
    optimizeDeps: {
      include: ["@inertiajs/inertia", "@inertiajs/inertia-svelte"],
    },
  };
});
