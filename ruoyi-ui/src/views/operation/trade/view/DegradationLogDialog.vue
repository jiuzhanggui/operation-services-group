<template>
  <el-dialog title="降级日志" :visible.sync="isOpenLogDialog" @close="closeDialog" width="45%">
    <el-row style="text-align: left">
      任务名称
      <el-input
        disabled
        v-model="taskInfo.taskName"
        autocomplete="off"
        style="width: 25%;">
      </el-input>
    </el-row>
    <el-row style="text-align: left">
      <el-table size="small" current-row-key="id" :data="logList" stripe highlight-current-row>-->
        <el-table-column prop="gmtCreate" label="任务时间" align="left" width="200%"></el-table-column>
        <el-table-column prop="content" label="任务日志" align="left"></el-table-column>
      </el-table>
      <Paging ref="paging" :total = "nowTotal" @getPageRequest = "getPageRequest" />
    </el-row>
  </el-dialog>
</template>

<script>
// import {getDegradationTaskLog} from "../../api/degradationApi";
import Paging from "@/views/operation/trade/common/Paging";

export default {
  name: "DegradationLogDialog",
  props: {
    taskName: {
      type: String,
      default: ''
    }
  },
  components:{
    Paging
  },

  data() {
    return {
      isOpenLogDialog: true,
      nowTotal:0,
      taskInfo: {
        taskName: this.taskName,
        startIndex: 0,
        pageSize: 0,
      },
      logList: []
    }
  },

  methods: {
    closeDialog() {
      this.$emit('logDialogController')
    },

    getPageRequest(res) {
      let pageRequest = JSON.parse(res);
      this.taskInfo.startIndex = pageRequest.startIndex
      this.taskInfo.pageSize = pageRequest.pageSize
      this.getDegradationTaskLogRecord()
    },

    getDegradationTaskLogRecord(){
      // getDegradationTaskLog(this.taskInfo).then(res =>{
      //   this.logList = res.data.body.content
      //   this.nowTotal = res.data.body.total
      // })
    }

  }
}
</script>

<style scoped>

</style>
