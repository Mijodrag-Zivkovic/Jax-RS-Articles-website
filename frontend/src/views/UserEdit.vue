<template>
  <div :ref="this.user.id">

    <input ref="email" type="text" :value="user.email">
    <br>
    <input ref="password" type="text"  >
    <br>
    <input ref="passwordConfirm" type="text"  >
    <br>
    <input ref="name" type="text" :value="user.name">
    <br>
    <input ref="surname" type="text" :value="user.surname" >
    <br>
    <select ref="type" >
      <option value="admin">Admin</option>
      <option value="regular">Regular</option>
    </select>
    <br>
    <select ref="status" >
      <option value="active">Active</option>
      <option value="inactive">Inactive</option>
    </select>
    <form class="d-flex" @submit.prevent="confirm">
      <button class="btn btn-outline-secondary" type="submit">Confirm</button>
    </form>
    <hr>
  </div>
</template>

<script>
export default {
  name: "UserEdit",
  data(){
    return{
      user: {}
    }
  },
  created(){
    this.$axios.get(`/api/users/user`,
        {
          params: {
            id: this.$route.query.id
          }
        }).then((response) => {
      this.user = response.data;
      //${this.$route.params.id}
    })
  },
  methods:{
    confirm(){
      //console.log(this.$refs.category.value);
      if((this.$refs.password.value === this.$refs.passwordConfirm.value) && this.$refs.password.value !== "")
      {
        console.log("okej su");
        this.$axios.put('/api/users/'+this.user.id,
            {
              email: this.$refs.email.value,
              password: this.$refs.password.value,
              name: this.$refs.name.value,
              surname: this.$refs.surname.value,
              userType: this.$refs.type.value,
              status: this.$refs.status.value
            }).then((response) => {
          //console.log(response.status);
          if(response.status == 200)
          {
            this.$router.push({name: 'Users'});
          }
        });
      }


    },
  }
}
</script>

<style scoped>

</style>