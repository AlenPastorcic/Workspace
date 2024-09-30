<template>
    <table>
        <thead>
            <th>Product</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Total Price</th>
        </thead>
        <tbody v-if="$store.state.shoppingCart.length > 0">
            <tr v-for="(sp, i) in $store.state.shoppingCart" v-bind:key="sp.productId">
                <td width="300"><router-link v-bind:to="{ name: 'product-detail', params: { id: products[i].productId }}">
                                   {{ products[i].name }}
                                </router-link>
                </td>
                <td>{{ sp.qty }}</td>
                <td>${{ products[i].price }}</td>
                <td>${{ sp.qty*products[i].price }}</td>
            </tr>
        </tbody>
        <tbody v-else>
            <tr>
                <td colspan="5">Your Cart is Empty</td>
            </tr>
        </tbody>
    </table>
    <form v-on:submit="saveOrder">
    </form>
</template>

<script>

export default {
    computed: {
        products() {
            return this.$store.state.shoppingCart.map((shopProduct) => {
                return this.$store.state.products.find((prod) => {
                    return prod.id == shopProduct.productId;
                })
            });
        }
    },
    methods: {
        addToCart(product) {
            this.$store.commit('ADD_TO_CART', product);
        },
        removeFromCart(product) {
            this.$store.commit('REMOVE_FROM_CART', product);
        }
    }

}

</script>

<style scoped>
</style>