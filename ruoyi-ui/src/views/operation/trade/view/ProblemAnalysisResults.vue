<template>
  <el-table
    header-align="center"
    v-loading="loading"
    align="center"
    :data="tableData"
    style="width: 100%;"
    max-height="740px"
    :row-class-name="tableRowClassName">
    <el-table-column
      prop="paramStatusName"
      label="状态名"
      width="180">
    </el-table-column>
    <el-table-column
      prop="paramStatusValue"
      label="状态结果"
      min-width = "180px"
      >
    </el-table-column>
    <el-table-column
      label="状态标识"
      v-if='false'
      prop="paramStatusTab">
    </el-table-column>
  </el-table>
</template>

<script>
// import {getUserAnalysisResultAPI, getOrderAnalysisResultAPI} from '@/api/api'

export default {
  name: "ProblemAnalysisResults",
  data(){
    return{
      tableData:[],
      loading: true
    }
  },

  created() {},

  mounted() {
    // this.getData();
  },

  props: {
    userInfo:{
      type: Object,
      default: null
    },
    analyseType:{
      type:String,
      default: ''
    }
  },

  methods: {
    tableRowClassName({row, rowIndex}) {
      if (row.paramStatusTab === 0){
        return 'error-row';
      } else {
        return 'success-row';
      }
    },
    //
    // getData(type){
    //   if (this.userInfo == null){
    //     this.loading = false
    //   }else {
    //     this.loading = true
    //     if (this.analyseType =='user'){
    //       getUserAnalysisResultAPI(this.userInfo).then(res => {
    //         let data = res.data.body.params;
    //         this.tableData =data;
    //       }).finally(() =>{
    //         this.loading = false
    //       })
    //     } else if (this.analyseType == 'order'){
    //       getOrderAnalysisResultAPI(this.userInfo).then(res => {
    //         let data = res.data.body.params;
    //         this.tableData = data;
    //       }).finally(() =>{
    //         this.loading = false
    //       })
    //     }
    //   }
    // }
  }
}
</script>

<style scoped>
</style>

<style>
.el-table .error-row {
  background: #ffdede;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
