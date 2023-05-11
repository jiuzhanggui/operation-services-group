<template>
  <el-table
    :data="extData"
    :default-expand-all="true"
    height="645px"
    style="width: 100%; align-items: center;">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="卖家昵称 : " >
            <span>{{ props.row.sellerNick }}</span>
          </el-form-item>
          <el-form-item label="卖家 ID : ">
            <span>{{ props.row.sellerId }}</span>
          </el-form-item>
          <el-form-item label="数据库 ID : ">
            <span>{{ props.row.dbId }}</span>
          </el-form-item>
          <el-form-item label="数据库状态 : ">
            <span>{{ props.row.dbStatus }}</span>
          </el-form-item>
          <el-form-item label="Top状态 : ">
            <span>{{ props.row.topStatus }}</span>
          </el-form-item>
          <el-form-item label="拉单状态 : ">
            <span>{{ props.row.pullStatus }}</span>
          </el-form-item>
          <el-form-item label="拉单开始时间 : ">
            <span>{{ props.row.pullStartDateTime }}</span>
          </el-form-item>
          <el-form-item label="拉单结束时间 : ">
            <span>{{ props.row.pullEndDateTime }}</span>
          </el-form-item>
          <el-form-item label="公司 ID : ">
            <span>{{ props.row.corpId }}</span>
          </el-form-item>
          <el-form-item label="降级标签 : ">
            <span>{{ props.row.downgradeTag }}</span>
          </el-form-item>
          <el-form-item label="创建时间 : ">
            <span>{{ props.row.gmtCreate }}</span>
          </el-form-item>
          <el-form-item label="最后修改时间 : ">
            <span>{{ props.row.gmtModified }}</span>
          </el-form-item>
          <el-form-item label="灰度标识 : ">
            <span>{{ props.row.grayLevel }}</span>
          </el-form-item>
          <el-form-item label="表 ID : ">
            <span>{{ props.row.listId }}</span>
          </el-form-item>
          <el-form-item label="打开应用程序昵称 : ">
            <span>{{ props.row.openedAppNames }}</span>
          </el-form-item>
          <el-form-item label="拉单端点 : ">
            <span>{{ props.row.pullEndPoint }}</span>
          </el-form-item>
          <el-form-item label="搜索库 ID : ">
            <span>{{ props.row.searchdbId }}</span>
          </el-form-item>
          <el-form-item label="店铺 ID : ">
            <span>{{ props.row.storeId }}</span>
          </el-form-item>
          <el-form-item label="近三个月订单数 : ">
            <span>{{ props.row.topTradeCount }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      label="卖家昵称"
      prop="sellerNick">
    </el-table-column>
    <el-table-column
      align="center"
      label="卖家 ID"
      prop="sellerId">
    </el-table-column>
    <el-table-column
      align="center"
      label="数据库 ID"
      prop="dbId">
    </el-table-column>
    <el-table-column
      align="center"
      label="数据库状态"
      prop="dbStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="Top状态"
      prop="topStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="拉单状态"
      prop="pullStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="拉单开始时间"
      prop="pullStartDateTime">
    </el-table-column>
    <el-table-column
      align="center"
      label="拉单结束时间"
      prop="pullEndDateTime">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "ExtTable",
  data() {
    return{
      extData: []
    }
  },
  methods:{
    getData(data) {
      if (data == null) {
        this.$message.warning("数据不存在")
      }
      if (data[0] != null || data.length() > 0) {
        // 添加Table数据
        this.addTableData(data)
      } else {
        this.$message.warning("数据不存在")
      }
    },

    addTableData(data) {
      this.extData = data;
    },
  }
}
</script>

<style scoped>
/*.demo-table-expand {*/
/*  font-size: 0;*/
/*}*/
/*.demo-table-expand label {*/
/*  width: 90px;*/
/*  color: #99a9bf;*/
/*}*/
/*.demo-table-expand .el-form-item {*/
/*  margin-right: 0;*/
/*  margin-bottom: 0;*/
/*  width: 50%;*/
/*}*/
</style>
