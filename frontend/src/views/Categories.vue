<template>
  <div ref="categories" class="categories">
    <h1>Categories</h1>
<!--    <ul>-->
      <div v-for="category in categories" :key="category.id">
        <Category :category="category"/>
      </div>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="addNew">
      <button class="btn btn-outline-secondary" type="submit">New Category</button>
    </form>
<!--    </ul>-->
  </div>
</template>

<script>
import Category from "../components/Category";
//import CategoryAdd from "@/views/CategoryAdd";

export default {
  components: {Category},
  filters: {
    shortText(value) {
      if (value.length < 30) {
        return value;
      }
      return value.slice(0, 30) + '...'
    }
  },
  data() {
    return {
      selectedCategory: null,
      categories: []
    }
  },
  mounted() {
    this.$axios.get('/api/categories').then((response) => {
      this.categories = response.data;
    });
  },
  beforeRouteEnter: function(to, from, next){
    //console.log(to);
    //console.log(from);
    //console.log(next);
    next(vm => {
      vm.fetch();
    });
  },
  methods:{
    addNew(){
      this.$router.push({name: 'CategoryAdd'});
    },
    async fetch(){
      this.$axios.get('/api/categories').then((response) => {
        this.categories = response.data;
      });
    }
  }
}
</script>

<style scoped>

</style>