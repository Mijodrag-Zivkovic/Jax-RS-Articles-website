<template>
  <div>
    <label for="title">Title</label><br>
    <input ref="title" id="title" type="text" >
    <br>
    <label for="content">Content</label><br>
    <input ref="content" id="content" type="text" >
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
      <TagSmarter :tag="tag"/>
    </div>
    <form class="d-flex" @submit.prevent="confirm">
      <button class="btn btn-outline-secondary" type="submit">Confirm</button>
    </form>
    <hr>
  </div>
</template>

<script>
import TagSmarter from "@/components/TagSmarter";

export default {
  name: "ArticleAdd",
  components:{TagSmarter},
  data(){
    return {
      categories:[],
      tags: []
    }
  },
  created() {
    this.$axios.get('api/categories').then((response) => {
      this.categories = response.data;
    });

  },
  // methods:{
  //   addNewArticle(){
  //     this.$axios.post('api/articles',
  //         {
  //           name: this.$refs.title.value,
  //           description: this.$refs.content.value
  //         });
  //     this.$router.push({name: 'Categories'});
  //   },
  // },
  methods: {
    confirm(){
      //console.log(this.$refs.category.value);
      this.$axios.post('/api/articles',
          {
            title: this.$refs.title.value,
            content: this.$refs.content.value,
            timeOfWriting: null,
            numberOfVisits: 0,
            authorId: localStorage.getItem("userId"),
            categoryId: this.$refs.category.value
          }).then((response) => {
        //console.log(response.status);
        if(response.data.id !== null)
        {
          console.log(response.data.id );
          for(let tag of this.tags)
          {
            console.log(tag.name)
            this.$axios.post(`/api/tags/articleId`,
                {
                  articleId: response.data.id,
                  tagId: tag.id
                }
            ).then(() => {


            });
          }
          this.$router.push({name: 'Articles'});
        }
      });
    },
    addTag(){
      // this.$axios.get(`/api/tags/tag`,
      //     {
      //       params:
      //           {
      //             name: this.$refs.tagAdd.value
      //           }
      //     }).then((response) => {
      //   //console.log(this.article.id);
      //   //console.log(response.data.id);
      //   this.$axios.post(`/api/tags/articleId`,
      //       {
      //         articleId: this.article.id,
      //         tagId: response.data.id
      //       }
      //   ).then(() => {
      //
      //     this.tags.push(response.data);
      //   });
      // });
      this.$axios.get(`/api/tags/tag`,
          {
            params:
                {
                  name: this.$refs.tagAdd.value
                }
          }).then((response)=>{
            if(response.data.name !== undefined)
             this.tags.push(response.data);
      });
    }
  }
}
</script>

<style scoped>

</style>