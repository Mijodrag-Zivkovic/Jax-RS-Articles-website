<template>
  <div>
    <h1>{{this.category.name}}</h1>
    {{this.$route.params.id}}
    <div v-for="article in articles" :key="article.id">
      <Article :article="article"/>
    </div>
  </div>
</template>

<script>
import Article from "@/components/Article";

export default {
  name: "ArticlesByCategory",
  components: {Article},
  data() {
    return {
      articles: [],
      category: {}
    }
  },
  created() {
     this.$axios.get(`/api/categories/category`,
         {
           params:{
             id: this.$route.params.id
           }
         }
     ).then((response) => {
       this.category = response.data;
     });
    this.$axios.get(`/api/articles/categoryId`,
        {
          params:{
            id: this.$route.params.id
          }
        }
    ).then((response) => {
      this.articles = response.data;
    });
  }
}
</script>

<style scoped>

</style>