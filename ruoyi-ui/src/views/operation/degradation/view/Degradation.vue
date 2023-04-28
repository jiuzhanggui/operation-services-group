<template>
  <div style="margin: 20px 20px 0px 20px">
    <el-form :inline="true" :model="taskInfo">
      <el-form-item label="" prop="taskName">
        <el-input
          prefix-icon="el-icon-s-promotion"
          clearable
          type="name"
          placeholder="任务名称"
          v-model="name">
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="button" type="primary" @click="queryTask">查询</el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="button" type="primary" @click="addDialogController">新增</el-button>
      </el-form-item>
    </el-form>
    <DegradationTable :taskList=taskList @queryTask="queryTask" />
    <Paging ref="paging" :total=nowTotal @getPageRequest=getPageRequest />
    <template v-if="isOpenAddDialog">
      <AddDegradation @addDialogController=addDialogController @queryTask="queryTask" />
    </template>
  </div>
</template>

<script>
import DegradationTable from "@/views/operation/degradation/view/DegradationTable.vue";
import {getDegradationTaskListAPI} from "@/api/operation/degradation/degrade";
import AddDegradation from "@/views/operation/trade/view/AddDegradation.vue";
import Paging from "@/views/operation/common/Paging.vue";

export default {
  name: "Degradation",
  components: {DegradationTable, AddDegradation, Paging},
  data() {
    return {
      taskList: [],
      name: '',
      taskInfo: {
        taskName: '',
        startIndex: 0,
        pageSize: 0,
      },
      nowTotal: 0,
      isOpenAddDialog: false
    }
  },

  methods: {
    // 获取分页信息
    getPageRequest(res) {
      let pageRequest = JSON.parse(res);
      this.taskInfo.startIndex = pageRequest.startIndex
      this.taskInfo.pageSize = pageRequest.pageSize
      this.getTaskList()
    },

    queryTask(req) {
      this.taskInfo.taskName = this.name
      // this.taskInfo.startIndex = 0;
      if (req === 0) {
        let number = Math.trunc(Math.floor(this.taskInfo.startIndex / this.taskInfo.pageSize));
        if (number - 1 < 0) {
          this.taskInfo.startIndex = 0
        } else {
          this.taskInfo.startIndex = number - 1
        }


        this.$refs.paging.clearCurrentPage(true)
      } else {
        this.$refs.paging.clearCurrentPage()
      }
      // this.$refs.paging.clearCurrentPage()
      this.getTaskList()
    },

    getTaskList() {
      let taskInfo = this.taskInfo
      getDegradationTaskListAPI(taskInfo).then(res => {
        debugger
        this.taskList = res.body.degradationTaskList
        this.nowTotal = res.body.total
      })
    },

    // 控制添加弹窗
    addDialogController() {
      this.isOpenAddDialog = !this.isOpenAddDialog
    },
  }
}


</script>

<style scoped>

</style>
