import { createInertiaApp } from "@inertiajs/inertia-svelte";

createInertiaApp({
  resolve: async (name) => {
    let svelteName = name[0].toUpperCase() + name.slice(1);
    let page = (await import(`./pages/${svelteName}.svelte`)).default;
    console.log(svelteName);
    console.dir(page);
    return page;
  },
  setup({ el, App, props }) {
    new App({ target: el, props })
  },
});
