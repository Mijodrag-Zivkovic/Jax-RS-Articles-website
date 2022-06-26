<template>
  <div :ref="this.user.id">
    <h2 v-on:click="visitUser">
      {{ user.email }}
    </h2>
    <p>
      {{ user.name }} <br>
    </p>
    <form class="d-flex" @submit.prevent="edit">
      <button class="btn btn-outline-secondary" type="submit">Edit</button>
    </form>
    <form class="d-flex" @submit.prevent="deleteUser">
      <button class="btn btn-outline-secondary" type="submit">Delete</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: "User",
  props:{
    user: {
      type: Object,
      required: true,
    },
  },
  methods:{
    edit(){
      //console.log(this.user.id);
      this.$router.push({
        name: 'UserEdit',
        query: { id: this.user.id }
      });
    },
    deleteUser(){
      //console.log('/api/categories/'+this.category.id)
      this.$axios.delete('/api/users/'+this.user.id);
      this.$refs[this.user.id].remove();
      this.$parent.users.splice(this.$parent.users.indexOf(this.user),1);
    },
    visitUser(){
      this.$router.push({
        name: 'UserSingle',
        query: { id: this.user.id }
      });
    }
  }
}
</script>

<style scoped>

</style>