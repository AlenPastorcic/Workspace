<template>
    <table>
        <thead>
            <th>#</th>
            <th>Product</th>
            <th>Price</th>
            <th>Actions</th>
        </thead>
        <tbody v-if="$store.state.products.length > 0">
            <tr v-for="(product, i) in $store.state.products" v-bind:key="product.productId">
                <td width="30">{{ i+1 }}</td>
                <td width="300"><router-link v-bind:to="{ name: 'product-detail', params: { id: product.productId }}">
                                   {{ product.name }}
                                </router-link>
                </td>
                <td>${{ product.price }}</td>
                <td><button>Add to cart</button></td>
            </tr>
        </tbody>
        <tbody v-else>
            <tr>
                <td colspan="4">There are no products</td>
            </tr>
        </tbody>
    </table>
</template>

<script>

export default {

}

</script>

<style scoped>
</style>