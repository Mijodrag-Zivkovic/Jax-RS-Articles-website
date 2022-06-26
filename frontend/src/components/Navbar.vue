<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Some site name</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link to="/" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Home'}">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'Categories'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Categories'}">Categories</router-link>
            </li>
            <li class="nav-item">
              <router-link :to="{name: 'Articles'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Articles'}">Articles</router-link>
            </li>
            <li class="nav-item">
              <router-link v-if="globalIfLoggedIn() && globalIfAdminLoggedIn()" :to="{name: 'Users'}" tag="a" class="nav-link" :class="{active: this.$router.currentRoute.name === 'Users'}">Users</router-link>
            </li>
          </ul>
          <form v-if="canLogout" class="d-flex" @submit.prevent="logout">
            <button class="btn btn-outline-secondary" type="submit">{{ifLogged()}}</button>
          </form>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "Navbar",
  computed: {
    canLogout() {
      return this.$route.name !== 'Login';
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('jwt');
      localStorage.removeItem('userId');
      this.$router.push({name: 'Login'});
    },
    ifLogged(){
      if(localStorage.getItem("jwt"))
      {
        return "Log out"
      }
      else return "Log in"
    },
    ifLoggedIn(){
      if(localStorage.getItem("jwt"))
      {
        return true;
      }
      else return false;
    }

  }
}
</script>

<style scoped>

</style>
