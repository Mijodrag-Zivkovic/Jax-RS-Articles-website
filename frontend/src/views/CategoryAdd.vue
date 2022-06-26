<template>
  <div>
      <label for="name">Name</label><br>
      <input ref="nameInput" id="name" type="text" >
      <br>
      <label for="desc">Description</label><br>
      <input ref="descriptionInput" id="desc" type="text" >
      <form class="d-flex" @submit.prevent="addNewCategory">
        <button class="btn btn-outline-secondary" type="submit">Confirm</button>
      </form>
      <hr>
  </div>
</template>

<script>
export default {
  name: "CategoryAdd",
  methods:{
    addNewCategory(){
      this.$axios.post('api/categories',
          {
            name: this.$refs.nameInput.value,
            description: this.$refs.descriptionInput.value
          });
      this.$router.push({name: 'Categories'});
    },
  }
}
</script>

<style scoped>

</style>