<template>
  <div :ref="this.article.id">
    <h2 v-on:click="visitArticle">
      {{ article.title }}
    </h2>
    <p>
      {{ article.content }} <br>
      {{article.timeOfWriting}}<br>
      {{article.numberOfVisits}}<br>
      {{article.authorId}}<br>
      {{article.categoryId}}<br>

    </p>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="edit">
      <button class="btn btn-outline-secondary" type="submit">Edit</button>
    </form>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="deleteArticle">
      <button class="btn btn-outline-secondary" type="submit">Delete</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: "Article",
  props:{
    article: {
      type: Object,
      required: true,
    },
  },
  methods:{
    edit(){
      console.log(this.article.id);
      this.$router.push({
        name: 'ArticleEdit',
        query: { id: this.article.id }
      });
    },
    deleteArticle(){
      //console.log('/api/categories/'+this.category.id)
      this.$axios.delete('/api/articles/'+this.article.id);
      this.$refs[this.article.id].remove();
    },
    visitArticle(){
      this.$router.push({
        name: 'ArticleSingle',
        query: { id: this.article.id }
      });
    }
  }
}
</script>

<style scoped>

</style>