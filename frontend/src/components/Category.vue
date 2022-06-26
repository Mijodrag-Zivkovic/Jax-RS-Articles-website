<template>
  <div :ref="this.category.id">
    <h2 v-on:click="visitArticles">
      {{ category.name }}
    </h2>
    <p>
      {{ category.description }} <br><br>
    </p>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="edit">
      <button class="btn btn-outline-secondary" type="submit">Edit</button>
    </form>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="deleteCategory">
      <button class="btn btn-outline-secondary" type="submit">Delete</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: "Category",
  props:{
    category: {
      type: Object,
      required: true,
    },
  },
  data(){
    return{
      articles:[]
    }
  },
  methods:{
    edit(){
      console.log(this.category.id);
      this.$router.push({
        name: 'CategoryEdit',
        query: { id: this.category.id }
      });
    },
    deleteCategory(){
      //console.log('/api/categories/'+this.category.id)
      this.$axios.get('/api/articles/categoryId',
          {
            params: {
              id: this.category.id
            }
          }
      ).then((response) => {
        // this.articles = JSON.parse(JSON.stringify(response.data));
        this.articles=response.data;
      }).then(() => {
        //console.log(this.articles);
        if(this.articles.length==0)
        {
          //console.log("0");
          this.$axios.delete('/api/categories/'+this.category.id);
          this.$refs[this.category.id].remove();
        }
      });



    },
    visitArticles(){
      this.$router.push('/articles/categoryId/'+this.category.id);
    }
  }
}
</script>

<style scoped>

</style>