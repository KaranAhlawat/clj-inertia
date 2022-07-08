import { createInertiaApp } from "@inertiajs/inertia-svelte";

// Function to resolve the correct Svelte component file based on the
// component name passed by Inertia.
// For each page we check if it matches the name, and then return it back
function resolvePageComponent(name, pages) {
  for (const path in pages) {
    if (path.endsWith(`${name.replace(".", "/")}.svelte`)) {
      return typeof pages[path] === "function" ? pages[path]() : pages[path];
    }
  }

  throw new Error(`Page not found: ${name}`);
}

// Create the inertia app. The "App" svelte component is internal to
// inertia-svelte package.
createInertiaApp({
  resolve: (name) =>
    resolvePageComponent(name, import.meta.glob("./pages/**/*.svelte")),
  setup({ el, App, props }) {
    new App({ target: el, props });
  },
});
