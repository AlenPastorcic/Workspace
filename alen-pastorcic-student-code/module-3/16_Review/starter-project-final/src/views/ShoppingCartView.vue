<template>
    <h1>Shopping Cart</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'products'}">Back to shopping</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'checkout'}">Checkout</router-link>
    </nav>
    <shopping-cart />
</template>

<script>
import ShoppingCart from '../components/ShoppingCart.vue';

export default {
    components: {
        ShoppingCart
    }

}

</script>

<style scoped>
</style>