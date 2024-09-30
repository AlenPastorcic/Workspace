<template>
    <div class="main">
        <h2>Product Reviews for {{ name }} </h2>

        <p class="description"> {{ description }}</p>

    </div>
</template>

<script>
export default {
    name: 'product-review',
    data() {
        return {
            name: 'Head First Design Patterns',
            description: 'A brain building guide to use java'
        }
    }
}
</script>


<style scoped>
div.main {
    margin: 1rem 0;
}

</style>