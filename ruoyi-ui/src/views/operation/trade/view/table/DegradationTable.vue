<template>
  <div>
    <el-table
      :data="degradationList"
      :header-cell-style="{'text-align':'center'}"
      :cell-style="{'text-align':'center'}"
      :row-class-name="tableRow"
      @row-click="onRowClick"
      stripe
      style="width: 100%;">
      <el-table-column
        type="index"
        label="序号"
        width="100%">
      </el-table-column>

      <el-table-column
        prop="name"
        label="任务名称"
        width="200%">
      </el-table-column>

      <el-table-column
        prop="description"
        label="描述"
        width="500%">
      </el-table-column>

      <el-table-column
        prop="timerStatus"
        label="定时任务启用状态"
        width="250%">
        <template slot-scope="scope">
          <el-tag class="degradation-tag"
                  :type="scope.row.timerStatus === 0 ? 'info' : scope.row.timerStatus === 1 ? 'success' : 'warning' ">
            {{ scope.row.timerStatus === 0 ? '无定时任务' : scope.row.timerStatus === 1 ? '定时任务已开启' : '定时任务准备中' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="isOpenDegradationTask"
        label="是否降级"
        width="150%">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.switchStatus"
            @change="degradationControl(scope.row)"
            active-text="开启"
            :active-value="1"
            active-color="#13ce66"
            inactive-text="恢复"
            :inactive-value="0"
            inactive-color="#f4f4f5">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column
        prop="degradationOperation"
        label="操作">
        <template slot-scope="scope">
          <el-button class="button" type="success" @click="timerDialogController(scope.row.name)">定时降级</el-button>
          <el-button class="button" type="success" @click="logDialogController(scope.row.name)">降级日志</el-button>
          <el-button type="danger" @click="deleteDegradationTask(scope.row.name)">删除任务</el-button>


        </template>
      </el-table-column>

    </el-table>
    <template>
      <el-button type="text" @click="openMessageBox"></el-button>
    </template>
    <!--设置定时任务弹窗-->
    <template v-if="isOpenTimerDialog">
      <DegradationTimerDialog ref="degradationTimerDialog" :taskName="taskInfo.taskName"
                              @timerDialogController="timerDialogController" @refreshTable="refreshTable" />
    </template>
    <template v-if="isOpenLogDialog">
      <DegradationLogDialog ref="log" :taskName="taskInfo.taskName" @logDialogController="logDialogController" />
    </template>
  </div>
</template>

<script>
import DegradationTimerDialog from "@/views/operation/trade/view/DegradationTimerDialog";
import DegradationLogDialog from "@/views/operation/trade/view/DegradationLogDialog";
import Paging from "@/views/operation/trade/common/Paging";
// import {degradationTaskControl, deleteDegradationTaskAPI} from "../../api/degradationApi";

export default {
  name: "DegradationTable",
  components: {DegradationLogDialog, DegradationTimerDialog, Paging},
  data() {
    return {
      degradationList: [],
      isOpenTimerDialog: false,
      isOpenLogDialog: false,
      currentRowIndex: 0,
      taskInfo: {
        taskName: '',
        switchStatus: ''
      }
    }
  },

  props: {
    taskList: {
      default: []
    }
  },

  watch: {
    taskList(newVal, oldVal) {
      // this.degradationList = newVal
      // for (let i = 0; i < this.degradationList.length; i++) {
      //   if (newVal[i].startTime == null && newVal[i].endTime == null) {
      //     this.degradationList[i].timerStatus = 0
      //   } else {
      //
      //     let startTime = new Date(newVal[i].startTime.replace(/-/g, '/')).getTime();
      //     let endTime = new Date(newVal[i].endTime.replace(/-/g, '/')).getTime();
      //     if (startTime != null && endTime != null) {
      //       if (startTime > Date.now()) {
      //         this.degradationList[i].timerStatus = 2
      //       } else if (startTime < Date.now() && endTime > Date.now()) {
      //         this.degradationList[i].timerStatus = 2
      //       } else if (startTime < Date.now() && endTime < Date.now()) {
      //         this.degradationList[i].timerStatus = 0
      //       }
      //     } else {
      //       let time = 0;
      //       if (startTime != null) {
      //         time = startTime
      //       } else {
      //         time = endTime
      //       }
      //       if (time < Date.now()) {
      //         this.degradationList[i].timerStatus = 2
      //       } else {
      //         this.degradationList[i].timerStatus = 0
      //       }
      //     }
      //   }
      //
      // }
    }
  },

  methods: {

    refreshTable(req) {
      this.$emit('queryTask', req)
    },
    deleteDegradationTask(req) {
      // this.$confirm('此操作将删除该任务, 是否继续?', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // }).then(() => {
      //   this.taskInfo.taskName = req
        // deleteDegradationTaskAPI(this.taskInfo).then(res => {
        //   if (res.data.code == 200) {
        //     this.$message({
        //       message: res.data.body,
        //       type:"success",
        //       center: true
        //     })
        //     this.refreshTable(this.currentRowIndex)
        //   } else {
        //     this.$message({
        //       message: res.data.message,
        //       type:"error",
        //       center: true
        //     })
        //   }
        // }).catch(() => {
        //   this.$message({
        //     type: 'info',
        //     message: '已取消操作'
        //   });
        //   req.switchStatus = +!req.switchStatus
        // });
      // })
    },

    degradationControl(req) {
      this.openMessageBox(req)
    },

    openMessageBox(req) {
      // this.$confirm('执行降级操作, 是否继续?', '告警', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // }).then(() => {
      //   this.taskInfo.switchStatus = req.switchStatus
      //   this.taskInfo.taskName = req.name
        // degradationTaskControl(this.taskInfo).then(res =>{
        //   if (res.data.code == 200){
        //     this.$message({
        //       message:res.data.message,
        //       type: 'success',
        //       center: true
        //     })
        //     this.refreshTable(this.currentRowIndex)
        //   } else {
        //     this.$message({
        //       message:res.data.message,
        //       type: 'error',
        //       center: true
        //     })
        //     req.switchStatus = +!req.switchStatus
        //   }
      //   // })
      // }).catch(() => {
      //   this.$message({
      //     type: 'info',
      //     message: '已取消操作',
      //     center: true
      //   });
      //   req.switchStatus = +!req.switchStatus
      // });
    },

    timerDialogController(res) {
      this.taskInfo.taskName = res
      this.isOpenTimerDialog = !this.isOpenTimerDialog
    },

    logDialogController(res) {
      this.taskInfo.taskName = res
      this.isOpenLogDialog = !this.isOpenLogDialog
    },

    tableRow({row, rowIndex}) {
      row.row_index = rowIndex;
    },

    onRowClick(row, event, column) {
      this.currentRowIndex = row.row_index;
    }
  }
}
</script>

<style scoped>
.degradation-tag {

}
</style>
