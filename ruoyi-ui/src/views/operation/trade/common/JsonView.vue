<template>
  <div style="overflow: auto">
    <json-viewer
      :value="jsonData"
      :expand-depth=5
      style="height: 600px"
    ></json-viewer>
  </div>
</template>

<script>

export default {
  name: "JsonView",

  data() {
    return {
      jsonData: []
    }
  },

  methods: {
    getData(data) {
      this.jsonData = data
    }
  }
}
</script>

<style scoped>

</style>
