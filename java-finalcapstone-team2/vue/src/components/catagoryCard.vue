<template>
    <div class="catagory-card card">
        <div class="content">
            <!-- Back Side -->
            <div class="back">
                <div class="back-content">
                    <div class="circle" id="bottom"></div>
                    <div class="circle" id="right"></div>
                    <img :src="menuItem.imgUrl" alt="Category item image" class="menu-card-img img" />
                    <p>{{ menuItem.title }}</p>
                </div>
            </div>

            <!-- Front Side -->
            <div class="front">
                <div class="front-content">
                    <div class="category-title">
                        <p>{{ menuItem.title }}</p>
                    </div>
                    <div class="category-description">
                        <p>{{ menuItem.description }}</p>
                    </div>
                    <div class="menu-button-holder">
                        <button class="animated-button" @click="addToOrder">Add</button>
                    </div>

                    <div class="card-footer">
                        Hover to flip
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: ['menuItem'],
    methods: {
        addToOrder() {
            this.$emit('add-to-cart', this.menuItem); // Emit the event with the item as payload
        }
    }
};
</script>

<style scoped>



.catagory-card {
    overflow: hidden;
    width: 300px;
    height: 400px;
    perspective: 1000px; 
    
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

.front, .back {
    position: absolute;
    width: 100%;
    height: 100%;
    backface-visibility: hidden;
    border-radius: 5px;
    overflow: hidden;
}

.back {
    background-color: #ffffff;
    transform: rotateY(0deg);
    color: black;
}

.front {
    background-color: #ffffff;
    transform: rotateY(180deg);
    color: black;
    text-align: center;
}

.card:hover .content {
    transform: rotateY(-180deg); 
}

.card:hover .back {
    visibility: hidden;
    opacity: 0;
}

.card:hover .front {
    visibility: visible;
    opacity: 1;
}

.back::before {
    position: absolute;
    content: ' ';
    display: block;
    width: 160px;
    height: 160%;
    background: linear-gradient(90deg, transparent, #7E91C6, #7E91C6, #7E91C6, #7E91C6, transparent);
    animation: rotation_481 5000ms infinite linear;
}

.back-content {
    position: absolute;
    width: 99%;
    height: 99%;
    background-color: #151515;
    border-radius: 5px;
    color: #bec1ca;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 30px;
}

@keyframes rotation_481 {
    0% {
        transform: rotateZ(0deg);
    }
    100% {
        transform: rotateZ(360deg);
    }
}

/* Front Side Styling */
.front-content {
    position: absolute;
    width: 65%;
    height: 100%;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    top: 50%; 
    left: 50%; 
    transform: translate(-50%, -50%); 
    text-align: center; 
}


.category-description {
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

.category-description p {
    margin: 0; /* Removes default margin from paragraphs */
    text-align: center; /* Ensures text within any paragraph tags is centered */
}

.category-description::-webkit-scrollbar{
    display: none;
}

.category-title {
    font-size: 15px;
    max-width: 100%;
    display: flex;
    justify-content: space-between;
}

.category-title p {
    width: 50%;
}

.card-footer {
    color: #ffffff88;
    margin-top: 5px;
    font-size: 8px;
}

.menu-card-img {
    position: fixed;
    width: auto;
    height: 45%;
    object-fit: cover;
    object-position: center;
}

.circle {
    width: 90px;
    height: 90px;
    border-radius: 50%;
    background-color: #7E91C6;
    position: relative;
    filter: blur(15px);
    animation: floating 2600ms infinite linear;
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

@keyframes floating {
    0% {
        transform: translateY(0px);
    }
    50% {
        transform: translateY(10px);
    }
    100% {
        transform: translateY(0px);
    }
}

.animated-button {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center; /* Center text horizontally and vertically */
    padding: 10px 20px; /* Adjusted padding for better appearance */
    border: 4px solid;
    border-color: transparent;
    font-size: 16px;
    background-color: inherit;
    border-radius: 50px; /* Adjusted for rounded button */
    font-weight: 600;
    color: rgb(207, 79, 0);
    box-shadow: 0 0 0 2px rgb(207, 79, 0);
    cursor: pointer;
    overflow: hidden;
    transition: all 0.6s cubic-bezier(0.23, 1, 0.32, 1);
}

.animated-button svg {
    position: absolute;
    width: 24px;
    fill: greenyellow;
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

.cart {
    width: 100%;
    padding: 10px;
    background-color: #f4f4f4;
    box-shadow: 0px 0px 10px 1px #7E91C6;
    margin-bottom: 20px;
    text-align: center;
}

.cart h3 {
    margin: 0;
    font-size: 20px;
    color: #7E91C6;
}

.cart ul {
    list-style-type: none;
    padding: 0;
}

.cart ul li {
    padding: 5px 0;
    border-bottom: 1px solid #ddd;
}


</style> 
