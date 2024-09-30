<template>
    <router-link v-bind:to="{ name: 'shopping-cart'}">
    <table class="cart-summary-table">
        <tr>
            <td>{{ numberInCart }} items in cart</td>
        </tr>
        <tr>
            <td>Current total: ${{ totalPriceInCart }}</td>
        </tr>

    </table>
</router-link>
</template>

<script>

export default {
    computed: {
        numberInCart() {
            let sum = this.$store.state.shoppingCart.reduce((currentSum, sp) => {
                return currentSum += sp.qty;
            }, 0);
            return sum;
        },
        totalPriceInCart() {
            let total = this.$store.state.shoppingCart.reduce((currentSum, sp) => {
                let product = this.$store.state.products.find((p) => {
                    return p.productId == sp.productId;
                });
                if (product) {
                    return currentSum += (product.price * sp.qty);
                }
            }, 0);
            return total;
        }

    }
}
</script>

<style scoped>
table {
    border: 1px solid red;
}
</style>