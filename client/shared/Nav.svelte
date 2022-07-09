<script>
  import { Link } from "@inertiajs/inertia-svelte";
</script>

<nav>
  <div>
    <h1 class="title">INERT</h1>
  </div>
  <ul>
    <Link href="/" class="nav-link">Home</Link>
    <Link href="/counter" class="nav-link">Counter</Link>
    <Link href="/404" class="nav-link">404</Link>
  </ul>
</nav>

<style scoped>
  nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: var(--gray-9);
  }

  ul {
    display: flex;
    gap: 2rem;
  }

  .title {
    color: var(--pink-6);
  }
</style>
