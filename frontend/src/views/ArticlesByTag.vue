<template>
  <div>
    <h1>{{this.tag.name}}</h1>
    {{this.$route.params.id}}
    <div v-for="article in articles" :key="article.id">
      <Article :article="article"/>
    </div>
  </div>
</template>

<script>
import Article from "@/components/Article";

export default {
  name: "ArticlesByTag",
  components: {Article},
  data() {
    return {
      articles: [],
      tag: {}
    }
  },
  created() {
    this.$axios.get(`/api/tags/tag`,
        {
          params:{
            id: this.$route.params.id
          }
        }
    ).then((response) => {
      this.tag = response.data;
    });
    this.$axios.get(`/api/articles/tagId`,
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