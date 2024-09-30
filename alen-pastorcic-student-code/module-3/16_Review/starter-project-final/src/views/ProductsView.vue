<template>
    <h1>Products</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'checkout'}">Checkout</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'shopping-cart'}">Go to cart</router-link>
    </nav>
    <product-list />
</template>

<script>
import ProductList from '../components/ProductList.vue';

export default {
    components: {
        ProductList
    }

}

</script>

<style scoped>
</style>