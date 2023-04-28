<template>
  <!--  分页组件-->
  <el-pagination
    background
    :current-page.sync="nowCurrentPage"
    @current-change="currentChange"
    :page-size=this.pageRequest.pageSize
    layout="prev, pager, next"
    :total=nowTotal>
  </el-pagination>
</template>

<script>
export default {
  name: "Paging",
  props: {
    total: {
      type: Number,
      default: 0
    }
  },
  watch: {
    total(newVal, oldVal) {
      this.nowTotal = newVal;
    },
  },
  data() {
    return {
      nowTotal: 0,
      nowCurrentPage: 0,
      pageRequest: {
        startIndex: 0, //开始下标索引
        pageSize: 10 //最大显示条数
      }
    }
  },
  created() {
    this.$emit('getPageRequest', JSON.stringify(this.pageRequest))
  },

  methods: {
    // 当前页跳转（点击页码按钮）
    currentChange(currentPage) {
      this.nowCurrentPage = currentPage
      this.pageRequest.startIndex = (currentPage - 1) * this.pageRequest.pageSize
      this.$emit('getPageRequest', JSON.stringify(this.pageRequest))
    },

    clearCurrentPage(req) {
      if (req)
        this.nowCurrentPage = this.nowCurrentPage - 1
    }
  }
}
</script>

<style scoped>
.el-pagination {
  text-align: right;
}
</style>
