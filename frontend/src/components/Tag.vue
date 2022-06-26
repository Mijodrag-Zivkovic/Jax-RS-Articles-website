<template>
  <div :ref="this.tag.id">
    <div v-on:click="articlesByTag">
    {{this.tag.name}}
    </div>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="deleteTag">
      <button class="btn btn-outline-secondary" type="submit">Delete</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "Tag",
  props:{
    tag: {
      type: Object,
      required: true,
    },
    articleId: {
      type: Number,
      required: true
    }
  },
  methods:{
    deleteTag(){
      this.$axios.delete('/api/tags/articleId',
          {
            params: {
              articleId: this.articleId,
              tagId: this.tag.id
            }
          }
      ).then(() => {
        this.$refs[this.tag.id].remove();
        //console.log(this.$parent.article.id);
        console.log(this.$parent.tags.indexOf(this.tag));
        this.$parent.tags.splice(this.$parent.tags.indexOf(this.tag),1);
      });

    },
    articlesByTag(){
      this.$router.push('/articles/tagId/'+this.tag.id);
    }
  }
}
</script>

<style scoped>

</style>