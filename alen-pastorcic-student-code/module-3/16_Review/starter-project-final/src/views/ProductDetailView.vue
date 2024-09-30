<template>
    <h1>{{ product.name }}</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'products'}">Back to products</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'shopping-cart'}">Go to cart</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'checkout'}">Checkout</router-link>
    </nav>
    <product-detail v-bind:product="product" />
    <cart-summary />

</template>

<script>
import ProductDetail from '../components/ProductDetail.vue';
import CartSummary from '../components/CartSummary.vue';

export default {
    components: {
        ProductDetail,
        CartSummary
    },
    computed: {
        product() {
            return this.$store.state.products.find((p) => {
                return p.id == this.$route.params.id;
            });
        }
    }

}

</script>

<style scoped>
</style>