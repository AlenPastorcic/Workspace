<template>
    <h2>Price: ${{ product.price }}</h2>
    <p>{{ product.description }}</p>
    <input type="button" value="Add to cart" v-on:click="addToCart(product)">
</template>

<script>

export default {
    props: ['product'],
    methods: {
        addToCart(product) {
            this.$store.commit('ADD_TO_CART', product);
        }
    }

}

</script>

<style scoped>
</style>