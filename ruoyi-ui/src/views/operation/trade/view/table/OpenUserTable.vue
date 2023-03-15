<template>
  <el-table
    :data="openUserInfo"
    stripe
    height="645px"
    style="width: 100%">
    <el-table-column
      align="center"
      prop="openUserId"
      label="开户ID">
    </el-table-column>
    <el-table-column
      align="center"
      prop="sellerNick"
      label="用户昵称">
    </el-table-column>
    <el-table-column
      align="center"
        prop="status"
      label="开户状态">
    </el-table-column>
    <el-table-column
      align="center"
      prop="ruleId"
      label="开通规则">
    </el-table-column>
    <el-table-column
      align="center"
      prop="type"
      label="类型">
    </el-table-column>
    <el-table-column
      align="center"
      prop="appName"
      label="应用昵称">
    </el-table-column>
    <el-table-column
      align="center"
      prop="gmtCreate"
      label="创建时间">
    </el-table-column>
    <el-table-column
      align="center"
      prop="gmtModify"
      label="修改时间">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "OpenUserTable",
  data() {
    return {
      openUserInfo: []
    }
  },

  methods: {
    getData(data) {
      if (data == null) {
        this.$message.warning("数据不存在")
      }
      if (data[0] != null) {
        // 添加Table数据
        this.addTableData(data)
      } else {
        this.$message.warning("数据不存在")
      }
    },

    addTableData(data) {
      this.openUserInfo = data;
    },
  }
}
</script>

<style scoped>

</style>
