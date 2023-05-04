<template>
  <el-dialog title="定时降级" :visible.sync="isOpenTimeDialog" @close="closeDialog" width="30%">
    <el-form :model="timedInfo">
      <el-row>
        <el-form-item label="任务名称：" style="text-align: left">
          <el-input
            disabled
            v-model="timedInfo.taskName"
            autocomplete="off"
            style="width: 35%;">
          </el-input>
        </el-form-item>
      </el-row>

      <el-row>
        <el-col :span="18">
          <el-form-item label="开启降级时间：" style="text-align: left">
            <div class="block">
              <el-date-picker
                v-model="openTime"
                type="datetime"
                value-format="yyyy-MM-dd HH:mm:ss"
                :picker-options="pickerOptions"
                placeholder="选择日期时间">
              </el-date-picker>
            </div>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-button class="button" type="info" @click="cancelTaskTimer">取消定时任务</el-button>
        </el-col>

      </el-row>

      <el-row>
        <el-form-item label="恢复降级时间：" style="text-align: left">
          <div class="block">
            <el-date-picker
              v-model="closeTime"
              type="datetime"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择日期时间">
            </el-date-picker>
          </div>
        </el-form-item>
      </el-row>
    </el-form>

    <div slot="footer" class="dialog-footer">
      <el-button @click="closeDialog">取 消</el-button>
      <el-button class="button" type="primary" @click="setTimedTask">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {cancelDegradationTaskTimer, degradationTaskTimer} from "@/api/operation/degradation/degrade";

export default {
  name: "DegradationTimerDialog",
  props: {
    taskName: {
      type: String,
      default: ''
    }
  },

  data() {
    return {
      isOpenTimeDialog: true,
      openTime: '',
      closeTime: '',
      timedInfo: {
        taskName: this.taskName,
        startTime: '',
        endTime: ''
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() < Date.now()
        }
      },
      value: []
    }
  },

  methods: {
    closeDialog() {
      // 子组件向父组件传值
      this.$emit('timerDialogController')
    },

    refresh() {
      this.$emit('refreshTable')
    },

    setTimedTask() {

      if (this.closeTime=== '' &&  this.openTime=== '') {
        this.$message({
          type: 'warning',
          message: '开始结束时间不能都为空',
          center: true
        });
        return
      }

      this.timedInfo.startTime = this.openTime
      this.timedInfo.endTime = this.closeTime

      degradationTaskTimer(this.timedInfo).then(res => {
        if (res.code === 200) {
          this.$message({
            type: 'success',
            message: res.msg,
            center: true
          });
          this.closeDialog()
          this.refresh()
        } else {
          this.$message({
            type: 'warning',
            message: res.msg,
            center: true
          });
        }
      })
    },

    getLastTime() {

    },

    cancelTaskTimer() {
      this.$confirm('取消定时任务, 是否继续?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        cancelDegradationTaskTimer(this.timedInfo).then(res => {
          if (res.code === 200) {
            this.$message({
              type: 'success',
              message: res.msg,
              center: true
            });
            this.refresh()
          } else {
            this.$message({
              type: 'warning',
              message: res.msg,
              center: true
            });
          }
        })

      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作',
          center: true
        });
      })


    }
  }
}
</script>

<style scoped>

</style>
