<template>
    <div>

      <input ref="email" type="text" >
      <br>
      <input ref="password" type="text"  >
      <br>
      <input ref="passwordConfirm" type="text"  >
      <br>
      <input ref="name" type="text" >
      <br>
      <input ref="surname" type="text"  >
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
      name: "UserAdd",
      data(){
        return{
          user: {}
        }
      },
      methods:{
        confirm(){
          //console.log(this.$refs.category.value);
          if((this.$refs.password.value === this.$refs.passwordConfirm.value) && this.$refs.password.value !== "")
          {
            console.log("okej su");
            this.$axios.post('/api/users',
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