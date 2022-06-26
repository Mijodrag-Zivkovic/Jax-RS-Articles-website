<template>
  <div>
    {{comment.name}}<br>
    {{comment.content}}<br>
    {{comment.dateTime}}<br>
    <hr>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props:{
    comment: {
      type: Object,
      required: true,
    },
  }
}
</script>

<style scoped>

</style>