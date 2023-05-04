<template>
  <el-dialog title="添加任务" :visible.sync="isOpenAddDialog" @close="cancel">
    <el-form ref="dataAddForm" :model="taskInfo" label-position="right" label-width="100px">
      <el-row>

        <el-col :span="12">
          <el-form-item label="任务名称">
            <el-input v-model="taskInfo.taskName"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="配置地址">
            <el-select v-model="taskInfo.configurationInterface" placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <el-form-item label="描述">
            <el-input v-model="taskInfo.description" type="textarea"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="8">
          <el-form-item label="项目ID">
            <el-input v-model="taskInfo.projectId" placeholder="apollo必填，其他不填"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="操作用户">
            <el-input v-model="taskInfo.userName" placeholder="apollo必填，其他不填"/>
          </el-form-item>
        </el-col>

        <el-col :span="8">
          <el-form-item label="集群名称">
            <el-input v-model="taskInfo.addressName" placeholder="apollo必填，其他不填"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="命名空间">
            <el-input v-model="taskInfo.nameSpace" placeholder="apollo必填，redis对应fild"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="配置Key值">
            <el-input v-model="taskInfo.key" placeholder="mysql对应SQL语句，redis和Apollo对应Key"/>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="自定义开启参数">
            <el-input v-model="taskInfo.openStatusParameter" placeholder="true、on，mysql不填"/>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="自定义关闭参数">
            <el-input v-model="taskInfo.closeStatusParameter" placeholder="false、off，mysql不填"/>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="cancel()">取消</el-button>
      <el-button type="primary" @click="addTask">确定</el-button>
    </div>
  </el-dialog>
</template>

<script>

import {addDegradationTaskAPI} from "@/api/operation/degradation/degrade";

export default {
  name: "AddDegradation",
  data() {
    return {
      isOpenAddDialog: true,
      taskInfo: {
        taskName: '',
        configurationInterface: 'apollo',
        projectId: '',
        userName: '',
        addressName: '',
        nameSpace: '',
        openStatusParameter: '',
        closeStatusParameter: '',
        key: '',

      },
      options: [{
        value: 'userRedis',
        label: 'UserRedis'
      }, {
        value: 'stringRedis',
        label: 'StringRedis'
      }, {
        value: 'apollo',
        label: 'Apollo'
      }, {
        value: 'mysql',
        label: 'mysql'
      },
      ],
    }
  },
  methods: {
    addTask() {
      let taskInfo = this.taskInfo;
      addDegradationTaskAPI(taskInfo).then(res =>{
        if (res.code === 200){
          this.$message({
            message: res.msg,
            type:"success",
            center: true
          })
          this.$emit('queryTask')
          this.$emit('addDialogController')
        }else{
          this.$message({
            message: res.msg,
            type:"warning",
            center: true
          })
        }
      })
    },

    // 关闭弹框
    cancel() {
      this.$emit('addDialogController')
    }

  }
}
</script>

<style scoped>

</style>
