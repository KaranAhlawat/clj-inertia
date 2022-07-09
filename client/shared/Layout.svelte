<script>
  import Nav from "./Nav.svelte";
</script>

<div class="outer">
  <Nav />
  <div class="main">
    <slot />
  </div>
</div>

<style scoped>
  .outer {
    padding: var(--size-5);
    width: 100vw;
  }

  .main {
    padding: var(--size-10);
  }
</style>
