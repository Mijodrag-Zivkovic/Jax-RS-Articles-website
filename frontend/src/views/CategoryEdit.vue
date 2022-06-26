<template>
  <div :ref="this.category.id">

    <input ref="nameInput" type="text" :value="category.name">
    <br>
    <input ref="descriptionInput" type="text" :value="category.description" >
    <form class="d-flex" @submit.prevent="confirm">
      <button class="btn btn-outline-secondary" type="submit">Confirm</button>
    </form>
    <hr>
  </div>
</template>

<script>

export default {
  name: "CategoryEdit",
  data() {
    return {
      category: {},
    }
  },
  created() {
    this.$axios.get(`/api/categories/category`,
        {
          params: {
            id: this.$route.query.id
          }
        }).then((response) => {
      this.category = response.data;
      //${this.$route.params.id}
    });
  },
  methods: {
    confirm(){
      this.$axios.put('/api/categories/'+this.category.id,
          {
            name: this.$refs.nameInput.value,
            description: this.$refs.descriptionInput.value
          }).then((response) => {
        console.log(response.status);
        if(response.status == 200)
        {
          this.$router.push({name: 'Categories'});
        }
      });
    }
  }
}
</script>

<style scoped>

</style>