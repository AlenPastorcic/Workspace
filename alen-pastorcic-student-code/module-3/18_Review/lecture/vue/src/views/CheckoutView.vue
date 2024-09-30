<template>
    <h1>Checkout</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'products'}">Back to shopping</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'shopping-cart'}">Go to cart</router-link>
    </nav>
    <checkout />
    
</template>

<script>
import Checkout from '../components/Checkout.vue';

export default {
    components: {
        Checkout
    }

}

</script>

<style scoped>
</style>