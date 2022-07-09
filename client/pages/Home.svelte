<script>
  let sites = [
    {
      display: "Clojure",
      url: "https://clojure.org",
    },
    {
      display: "Svelte",
      url: "https://svelte.dev",
    },
    {
      display: "Inertia.js",
      url: "https://inertiajs.com,",
    },
  ];
</script>

<div class="container">
  <h2>Hello there,</h2>
  <h2>random stranger!</h2>
  <div class="spacer" />

  <p>This website is a PoC for using</p>

  <div class="links">
    {#each sites as site}
      <a href={site.url}>{site.display}</a>
    {/each}
  </div>
  <p>for making web applications (and having fun doing so)</p>

  <div class="spacer" />

  <a href="https://git.sr.ht/~korven/svelte-inertia">Source Code</a>
</div>

<style scoped>
  .container {
    font-family: "Montserrat";
    display: grid;
    place-items: center;
    margin-inline: auto;
  }

  p {
    font-size: var(--size-4);
  }

  .spacer {
    padding-block: var(--size-3);
  }

  .links {
    display: flex;
    gap: var(--size-5);
  }

  a {
    color: var(--green-4);
    font-size: var(--size-4);
  }
</style>
