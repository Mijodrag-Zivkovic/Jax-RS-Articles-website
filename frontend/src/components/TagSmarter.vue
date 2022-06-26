<template>
  <div :ref="this.tag.id">
    <div>
      {{this.tag.name}}
    </div>
    <form class="d-flex" @submit.prevent="deleteTag">
      <button class="btn btn-outline-secondary" type="submit">Delete</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "TagSmarter",
  props:{
    tag: {
      type: Object,
      required: true,
    }
  },
  methods:{
    deleteTag(){
        //this.$refs[this.tag.id].remove();
        //console.log(this.$parent.article.id);
        console.log(this.$parent.tags.indexOf(this.tag));
        this.$parent.tags.splice(this.$parent.tags.indexOf(this.tag),1);
    },
  }
}
</script>

<style scoped>

</style>