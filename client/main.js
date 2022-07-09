// Importing soem global styles from OpenProps (only the normalize)
// These are not tree shaked afaik, which is what we want.
import "./style.css";
import { createInertiaApp } from "@inertiajs/inertia-svelte";

// Serves as the default persistent layout. So instead of defining it in each
// svelte component, we just define it here.
import Layout from "./shared/Layout.svelte";

// Progress indicator from Inertia, for some visual feedback on non-instant
// pages.
import { InertiaProgress } from "@inertiajs/progress";

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
  resolve: async (name) => {
    let page = await resolvePageComponent(
      name,
      import.meta.glob("./pages/**/*.svelte")
    );

    // Due to some reason, the page is read-only cuz of vite.
    // So we return a new mutable object with the correct layout.
    return Object.assign({ layout: Layout }, page);
  },
  setup({ el, App, props }) {
    new App({ target: el, props });
  },
});

InertiaProgress.init()