<template>
  <div :ref="this.article.id">

    <input ref="title" type="text" :value="article.title">
    <br>
    <input ref="content" type="text" :value="article.content" >
    <br>
    <select ref="category" >
      <option v-for="category in categories" :key="category.id" :value="category.id">
        {{category.name}}
      </option>
    </select>
    <br>
    <input ref="tagAdd" type="text">
    <form class="d-flex" @submit.prevent="addTag">
      <button class="btn btn-outline-secondary" type="submit">Add Tag</button>
    </form>

    <div v-for="tag in tags" :key="tag.id">
      <Tag :tag="tag" :articleId="article.id"/>
    </div>
    <form class="d-flex" @submit.prevent="confirm">
      <button class="btn btn-outline-secondary" type="submit">Confirm</button>
    </form>
    <hr>
  </div>
</template>

<script>
import Tag from "@/components/Tag";

export default {
  name: "ArticleEdit",
  components:{Tag},
  data() {
    return {
      article: {},
      categories: [],
      tags:[],
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
      //${this.$route.params.id}
    }).then(() => {
      this.$axios.get(`/api/categories`).then((response) => {
        this.categories = response.data;
        //${this.$route.params.id}
      });
      this.$axios.get(`/api/tags/articleId`,
          {
            params:
                {
                  id: this.article.id
                }
          }).then((response) => {
        this.tags = response.data;
        //${this.$route.params.id}
      });
        }

    );
  },
  methods: {
    confirm(){
      //console.log(this.$refs.category.value);

      this.$axios.put('/api/articles/'+this.article.id,
          {
            title: this.$refs.title.value,
            content: this.$refs.content.value,
            timeOfWriting: null,
            numberOfVisits: this.article.numberOfVisits,
            authorId: this.article.authorId,
            categoryId: this.$refs.category.value
          }).then((response) => {
        //console.log(response.status);
        if(response.status == 200)
        {
          this.$router.push({name: 'Articles'});
        }
      });
    },
    addTag(){
      this.$axios.get(`/api/tags/tag`,
          {
            params:
                {
                  name: this.$refs.tagAdd.value
                }
          }).then((response) => {
            //console.log(this.article.id);
            //console.log(response.data.id);
            this.$axios.post(`/api/tags/articleId`,
                {
                  articleId: this.article.id,
                  tagId: response.data.id
                }
                ).then(() => {

                this.tags.push(response.data);
              });
              //this.tags.push(response.data);
            });
    }
  }
}
</script>

<style scoped>

</style>