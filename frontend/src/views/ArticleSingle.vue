<template>
  <div :ref="this.article.id">
    <h2>
      {{ article.title }}
    </h2>
    <p>
      {{ article.content }} <br>
      {{article.timeOfWriting}}<br>
      {{article.numberOfVisits}}<br>
      {{article.authorId}}<br>

    </p>
    <div v-on:click="visitCategory">
      {{category.name}}<br>
    </div>
    <div v-for="tag in tags" :key="tag.id">
      <Tag :tag="tag" :articleId="article.id"/>
    </div>
    <hr>
    <h4>Add your comment:</h4>
    <input ref="name" type="text" placeholder="Your name">
    <br>
    <input ref="content" type="text" placeholder="Your comment">
    <form class="d-flex" @submit.prevent="addComment">
      <button class="btn btn-outline-secondary" type="submit">Confirm</button>
    </form>
    <hr>
    <h4>Comments:</h4><br>
    <div v-for="comment in comments" :key="comment.id" ref="comments" id="commentsId">
      <Comment :comment="comment"/>
    </div>
    <div id="id2">

    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment";
import Tag from "@/components/Tag";
export default {
  name: "ArticleSingle",
  components:{
    Comment,Tag},
  data(){
    return{
      article: {},
      category: {},
      comments: [],
      tags: []
    }
  },
  created() {
    this.$axios.get(`/api/articles/article`,
        {
          params: {
            id: this.$route.query.id
          }
        }).then((response) => {
      this.article = response.data;
    }).then(() => {
      this.$axios.get(`/api/categories/category`,
          {
            params: {
              id: this.article.categoryId
            }
          }
      ).then((response) => {
        this.category = response.data;
      });
      this.$axios.get(`/api/comments/articleId`,
          {
            params: {
              id: this.article.id
            }
          }
      ).then((response) => {
        this.comments = response.data;
      });
          this.$axios.get(`/api/tags/articleId`,
              {
                params: {
                  id: this.article.id
                }
              }
          ).then((response) => {
            this.tags = response.data;
          });
    }

    );
  },
  methods:{
    visitCategory(){
      this.$router.push('/articles/categoryId/'+this.category.id);
    },
    addComment(){
      this.$axios.post('/api/comments',
          {
            name: this.$refs.name.value,
            content: this.$refs.content.value,
            dateTime: null,
            articleId: this.article.id
          }).then((response) => {
        //console.log(response.status);
        if(response.status == 200)
        {
          //console.log(response.data.name);
          this.comments.push(response.data);
          //this.$refs.comments.
        }
      });
    }
  }
}
</script>

<style scoped>

</style>