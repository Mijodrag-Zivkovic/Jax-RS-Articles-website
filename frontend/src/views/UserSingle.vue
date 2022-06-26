<template>
  <div >
    <h2>
      {{ user.email }}
    </h2>
    <p>
      {{ user.name }} <br>
      {{ user.surname }} <br>
      {{ user.userType }} <br>
      {{ user.status }} <br>
    </p>
    <hr>
  </div>
</template>

<script>
export default {
  name: "UserSingle",
  data(){
    return{
      user:{}
    }
  },
  mounted(){
    this.$axios.get(`/api/users/user`,
        {
          params: {
            id: this.$route.query.id
          }
        }).then((response) => {
      this.user = response.data;
    });
  }
}
</script>

<style scoped>

</style>