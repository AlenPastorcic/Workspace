<template>
    <h1>Checkout</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'products'}">Back to shopping</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'shopping-cart'}">Go to cart</router-link>
    </nav>
</template>

<script>

export default {

}

</script>

<style scoped>
</style>