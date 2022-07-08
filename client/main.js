import { createInertiaApp } from "@inertiajs/inertia-svelte";

function resolvePageComponent(name, pages) {
  for (const path in pages) {
    if (path.endsWith(`${name.replace('.', '/')}.svelte`)) {
      return typeof pages[path] === 'function'
        ? pages[path]()
        : pages[path]
    }
  }

  throw new Error(`Page not found: ${name}`)
}

createInertiaApp({
  resolve: (name) => resolvePageComponent(name, import.meta.glob('./pages/**/*.svelte')),
  setup({ el, App, props }) {
    new App({ target: el, props })
  },
});
