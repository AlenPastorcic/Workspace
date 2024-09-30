<template>
    <div class="container">
      <table id="tblOrders">
        <thead>
          <tr>
            <th>&nbsp;</th>
            <th>Order Id</th>
            <th>User Id</th>
            <th>Item Id</th>
            <th>Comment</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <input type="checkbox" id="selectAll" v-on:change="selectAll($event)" v-bind:checked = "selectedOrderIds.length === orders.length"/>
            </td>
            <td>
              <input type="text" id="orderIdFilter" v-model="filter.orderId" />
            </td>
            <td>
              <input type="text" id="userIdFilter" v-model="filter.userId" />
            </td>
            <td>
              <input type="text" id="itemIdFilter" v-model="filter.itemId" />
            </td>
            <td>
              <input type="text" id="comment" v-model="filter.comment" />
            </td>
            <td>
              <input type="text" id="status" v-model="filter.status" />
            </td>
            <td>
              <select id="statusFilter" v-model="filter.status">
                <option value>Show All</option>
                <option value="Complete">Active</option>
                <option value="In Progress">Inactive</option>
              </select>
            </td>
            <td>&nbsp;</td>
          </tr>
          <tr
            v-for="order in filteredList"
            v-bind:key="order.orderId"
            v-bind:class="{ inProgress: order.status === 'In Progress' }"
          >
            <td>
             <input type="checkbox" v-bind:id="order.orderId" v-bind:value="order.orderId" v-model="selectedOrderIds" />
            </td>
            <td>{{ order.orderId }}</td>
            <td>{{ order.userId }}</td>
            <td>{{ order.itemId }}</td>
            <td>{{ order.comment }}</td>
            <td>{{ order.status }}</td>
            <td>{{ order.eventId }}</td>
            <td>
              <button class="btnCompleteIncomplete" v-on:click="toggleOrderStatus(order.orderId)">
              {{ order.status==="Completed" ? "In Progress"  : "Completed"}}</button>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="all-actions">
        <button v-on:click="completeSelectedOrders()" v-bind:disabled="!actionButtonEnabled">Mark Orders Complete</button>
        <button v-on:click="markInProgressSelectedOrders()" v-bind:disabled="!actionButtonEnabled">Mark Orders In Progress</button>
        <button v-on:click="deleteSelectedOrders()" v-bind:disabled="!actionButtonEnabled">Delete Orders</button>
        <OrderManagement />
      </div>
      </div>
  </template>

  <script>

import OrderManagement from './OrderManagement.vue'
export default {

    components: {
    OrderManagement
  },
  
  methods: {
    addNewOrder() {
      this.newOrder.orderId = this.getNextOrderId();
      this.orders.push(this.newOrder );
      this.clearForm();
    },
    clearForm() {
      this.newOrder.userId = "";
      this.newOrder.itemId = "";
      this.newOrder.comment = "";
      this.newOrder.status = "In Progress";
    },
    deleteOrder(id) {
      this.orders = this.orders.filter((order) => {
     return order.orderId !== id;
      });
    },
    getNextOrderId() {
      return this.nextOrderId++;
    },
    toggleOrderStatus(id) {
      const orderIndex = this.findOrderById(id);
      if (orderIndex !== -1) {
        this.orders[orderIndex].status = this.orders[orderIndex].status === "Completed" ? "In Progress" : "Completed";
      }
    },
    selectAll(order) {
      if (order.target.checked) {
        this.selectedOrderIds = this.orders.map(order => order.orderId);
      }
      else {
        this.selectedOrderIds = [];
      }
    },
    completeSelectedOrders() {
      this.selectedOrderIds.forEach((id) => {
        this.orders[this.findOrderById(id)].status = "Completed";
      });
      this.clearSelectedOrders();
    },
    markInProgressSelectedOrders() {
      this.selectedOrderIds.forEach((id) => {
        this.orders[this.findOrderById(id)].status = "In Progress";
      });
      this.clearSelectedOrders();
    },
    deleteSelectedOrders() {
      this.selectedOrderIds.forEach((id) => {
        this.deleteOrder(id);
      });
      this.clearSelectedOrders();
    },
    clearSelectedOrders(){
      this.selectedOrderIds = [];
    },
    findOrderById (id) {
    return this.orders.findIndex((order) => order.orderId==id)
    },
  },
  computed: {
    filteredList() {
      let filteredOrders = this.orders;
      if (this.filter.orderId != "") {
        filteredOrders = filteredOrders.filter((order) =>
          order.orderId
            .includes(this.filter.orderId)
        );
      }
      if (this.filter.userId != "") {
        filteredOrders = filteredOrders.filter((order) =>
          order.userId
            .includes(this.filter.userId)
        );
      }
      if (this.filter.itemId != "") {
        filteredOrders = filteredOrders.filter((order) =>
          order.itemId
            .includes(this.filter.itemId)
        );
      }
      if (this.filter.comment != "") {
        filteredOrders = filteredOrders.filter((order) =>
          order.comment
            .toLowerCase()
            .includes(this.filter.comment.toLowerCase())
        );
      }
      if (this.filter.status != "") {
        filteredOrders = filteredOrders.filter((order) =>
          order.status === this.filter.status
        );
      }
      return filteredOrders;
    },
    actionButtonEnabled (){
      return this.selectedOrderIds.length > 0;
    }
  }
};
</script>

<style>
form {
  max-width: 400px;
  margin: 0 auto;
}
form div {
  margin-bottom: 15px;
}
label {
  display: block;
  margin-bottom: 5px;
}
input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}
button {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}
</style>