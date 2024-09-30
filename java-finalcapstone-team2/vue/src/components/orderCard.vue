<template>
    <div class="order-card card">
        <div class="content">
    
                    
                    <div class ="user-id">
                        <p>User ID: {{ order.user_id }}</p>
                    </div>
                    <div class="order-status">
                        <p>Status: {{ order.status }}</p>
                    </div>
                    <div class ="order-comment">
                        <p>Comments/Special Requests:</p>
                        <p>{{ order.comment }}</p>
                    </div>
                    <div class="menu-button-holder">
                        <button class="animatedButton" v-on:click="toggleOrderStatus(order.orderId)">
              {{ order.status==="Completed" ? "In Progress"  : "Completed"}}</button>
                    </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['order'],
    methods: {
        editOrder() {
          
        }
    }
};
</script>

<style scoped>



.order-card {
    justify-content: center;
    overflow: hidden;
    width: 33.3%;
    height: 300px;
    perspective: 1000px; 
    display: inline-flex;
    align-items: center; 
  
  
    
    
}

.content {
    text-align: center;
    width: 100%;
    height: 100%;
    transform-style: preserve-3d;
    transition: transform 300ms;
    box-shadow: 0px 0px 10px 1px #7E91C6;
    border-radius: 5px;
    position: relative;
    justify-content: center;
}

.order-item {
    align-content: center;
}

.order-comment {
    box-shadow: 0px 0px 100px 5px #00000088;
    width: 100%;
    height: auto;
    max-height: 85%;
    padding: 10px; 
    overflow: auto; 
    background-color: #7E91C6;
    backdrop-filter: blur(5px);
    border-radius: 5px;
    color: black;
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center; 
}

.order-item {
    font-size: 15px;
    max-width: 100%;
    display: flex;
    justify-content: space-between;
}


.card-footer {
    color: #ffffff88;
    margin-top: 1px;
    font-size: 8px;
}




#bottom {
    background-color: #bf5600;
    left: 50px;
    top: 0px;
    width: 150px;
    height: 150px;
    animation-delay: -800ms;
}

#right {
    background-color: #7E91C6;
    left: 160px;
    top: -80px;
    width: 30px;
    height: 30px;
    animation-delay: -1800ms;
}

.animated-button {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center; 
    padding: 10px 20px; 
    border: 4px solid;
    border-color: transparent;
    font-size: 16px;
    background-color: inherit;
    border-radius: 50px; 
    font-weight: 600;
    color: rgb(14, 81, 183);
    box-shadow: 0 0 0 2px rgb(26, 65, 89);
    cursor: pointer;
    overflow: hidden;
    transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);
}

.animated-button svg {
    position: absolute;
    width: 24px;
    fill: rgb(152, 159, 202);
    z-index: 9;
    transition: all 0.8s cubic-bezier(0.23, 1, 0.32, 1);
}

.animated-button .arr-1 {
    right: 16px;
}

.animated-button .arr-2 {
    left: -25%;
}

.animated-button .circle {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 20px;
    height: 20px;
    background-color: greenyellow;
    border-radius: 50%;
    opacity: 0;
    transition: all 0.8s cubic-bezier(0.23, 1, 0.32, 1);
}

.animated-button .text {
    position: relative;
    z-index: 1;
    transition: all 0.8s cubic-bezier(0.23, 1, 0.32, 1);
}

.animated-button:hover {
    box-shadow: 0 0 0 12px transparent;
    color: #212121;
    border-radius: 12px;
}

.animated-button:hover .arr-1 {
    right: -25%;
}

.animated-button:hover .arr-2 {
    left: 16px;
}

.animated-button:hover .text {
    transform: translateX(12px);
}

.animated-button:hover svg {
    fill: #212121;
}

.animated-button:active {
    scale: 0.95;
    box-shadow: 0 0 0 4px #7E91C6;
}

.animated-button:hover .circle {
    width: 220px;
    height: 220px;
    opacity: 1;
}

.menu-button-holder {
    display: flex;
    justify-content: center;
    margin: 15px;
}

</style> 
