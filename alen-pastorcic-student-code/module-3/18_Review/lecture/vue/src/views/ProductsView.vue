<template>
    <h1>Products</h1>
    <nav class="top-level-nav">
        <router-link v-bind:to="{ name: 'checkout'}">Checkout</router-link>
        &nbsp;
        <router-link v-bind:to="{ name: 'shopping-cart'}">Go to cart</router-link>
    </nav>
    <div class="cart-summary" align="right">
        <cart-summary />
    </div>
    <product-list />
</template>

<script>
import CartSummary from '../components/CartSummary.vue';
import ProductList from '../components/ProductList.vue';
import productService from '../services/ProductService';

export default {
    components: {
        CartSummary,
        ProductList
    },
    data() {
        return {
            isLoading: true
        }
    },
    methods: {
        retrieveProducts() {
            productService.getProducts()
                .then(response => {
                    this.$store.commit('SET_PRODUCTS', response.data);
                    this.isLoading = false;
                })
                .catch(error => {
                    if (error.response) {
                        this.$store.commit('SET_NOTIFICATION',
                        "Error getting products. Response received was '" + error.response.statusText + "'.");
                    } else if (error.request) {
                        this.$store.commit('SET_NOTIFICATION', "Error getting products. Server could not be reached.");
                    } else {
                        this.$store.commit('SET_NOTIFICATION', "Error getting products. Request could not be created.");
                    }
                });
            },        
    },
    created() {
        this.retrieveProducts();
    }

}

</script>

<style scoped>

</style>