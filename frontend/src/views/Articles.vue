<template>
  <div ref="articles" class="articles">
    <h1>Articles</h1>
    <!--    <ul>-->
    <div v-for="article in articles" :key="article.id">
      <Article :article="article"/>
    </div>
    <form v-if="globalIfLoggedIn()" class="d-flex" @submit.prevent="addNew">
      <button class="btn btn-outline-secondary" type="submit">New Article</button>
    </form>
    <div style="text-align: center">
      <div v-if="pageNumber>1" v-on:click="pageNumberDecrease()">
        previous
      </div>
      {{this.pageNumber}}
      <div v-if="pageNumber*3<articlesNumber" v-on:click="pageNumberIncrease()">
        next
      </div>
    </div>
    <!--    </ul>-->
  </div>
</template>

<script>
import Article from "@/components/Article";

export default {
  name: "Articles",
  components: {Article},
  data() {
    return {
      articles: [],
      pageNumber:{
        type: Number
      },
      articlesNumber:{
        type: Number
      }

    }
  },
  watch:{
    pageNumber(){
      this.$axios.get('/api/articles/pagination',
          {
            params:{
              left:this.pageNumber*3-3,
              right:this.pageNumber*3
            }
          }).then((response) => {
        this.articles = response.data;
      }).then(() => {
            this.articles.sort((a,b) => (a.timeOfWriting < b.timeOfWriting) ? 1 : ((b.timeOfWriting < a.timeOfWriting) ? -1 : 0))
          }

      );
    }
  },
  mounted() {
    // this.$axios.get('/api/articles').then((response) => {
    //   this.articles = response.data;
    // }).then(() => {
    //   this.articles.sort((a,b) => (a.timeOfWriting < b.timeOfWriting) ? 1 : ((b.timeOfWriting < a.timeOfWriting) ? -1 : 0))
    //     }
    //
    // );

    this.$axios.get('/api/articles/count').then((response)=>{
        this.articlesNumber = response.data;
    }).then(()=>{
      //console.log(this.articlesNumber);
    });

    this.pageNumber=1;
    this.$axios.get('/api/articles/pagination',
        {
          params:{
            left:this.pageNumber*3-3,
            right:this.pageNumber*3
          }
        }).then((response) => {
                this.articles = response.data;
              }).then(() => {
                this.articles.sort((a,b) => (a.timeOfWriting < b.timeOfWriting) ? 1 : ((b.timeOfWriting < a.timeOfWriting) ? -1 : 0))
                 }

              );
  },
  beforeRouteEnter: function(to, from, next){
    next(vm => {
      vm.fetch();
    });
  },
  methods:{
    addNew(){
      this.$router.push({name: 'ArticleAdd'});
    },
    async fetch(){
      // this.$axios.get('/api/articles').then((response) => {
      //   this.articles = response.data;
      // }).then(() => {
      //       this.articles.sort((a,b) => (a.timeOfWriting < b.timeOfWriting) ? 1 : ((b.timeOfWriting < a.timeOfWriting) ? -1 : 0))
      //     }
      //
      // );
      this.$axios.get('/api/articles/count').then((response)=>{
        this.articlesNumber = response.data;
      }).then(()=>{
        //console.log(this.articlesNumber);
      });

      this.pageNumber=1;
      this.$axios.get('/api/articles/pagination',
          {
            params:{
              left:this.pageNumber*3-3,
              right:this.pageNumber*3
            }
          }).then((response) => {
        this.articles = response.data;
      }).then(() => {
            this.articles.sort((a,b) => (a.timeOfWriting < b.timeOfWriting) ? 1 : ((b.timeOfWriting < a.timeOfWriting) ? -1 : 0))
          }

      );
    },
    pageNumberIncrease()
    {
      this.pageNumber++;
    },
    pageNumberDecrease()
    {
      this.pageNumber--;
    }
  }

}
</script>

<style scoped>

</style>