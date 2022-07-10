<script>
  let count = 0;

  function increment() {
    count += 1;
  }

  function decrement() {
    count -= 1;
  }
</script>

<div class="container">
  <h1>Counter : {count}</h1>
  <div class="btn-div">
    <button class="btn" on:click={increment}>+</button>
    <button class="btn" on:click={decrement}>-</button>
  </div>
  <p>NOTE: Counter resets on navigating away :)</p>
</div>

<style scoped>
  p {
    font-family: 'Montserrat';
    font-weight: var(--font-weight-6);
  }

  .container {
    display: grid;
    place-items: center;
    gap: var(--size-7);
  }

  .btn-div {
    display: flex;
    gap: var(--size-5);
  }

  .btn {
    padding-block: var(--size-2);
    padding-inline: var(--size-10);
    font: 'Montserrat';
    font-size: var(--font-size-5);
    border-radius: var(--size-4);
    background-color: var(--gray-7);
    border: solid transparent 2px;
  }

  .btn:hover {
    border: solid white 2px;
  }
</style>
