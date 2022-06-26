<template>
  <div ref="users" class="users">
    <h1>Users</h1>
    <!--    <ul>-->
    <div v-for="user in users" :key="user.id">
      <User :user="user"/>
    </div>
    <form class="d-flex" @submit.prevent="addNew">
      <button class="btn btn-outline-secondary" type="submit">New User</button>
    </form>
    <!--    </ul>-->
  </div>
</template>

<script>
import User from "@/components/User";

export default {
  name: "Users",
  components:{User},
  data(){
    return{
      users:[]
    }
  },
  mounted() {
    this.$axios.get('/api/users').then((response) => {
      this.users = response.data;
    }).then(() => {
        }

    );
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
      this.$router.push({name: 'UserAdd'});
    },
    async fetch(){
      this.$axios.get('/api/users').then((response) => {
        this.users = response.data;
      }).then(() => {

          }

      );
    }
  }
}
</script>

<style scoped>

</style>