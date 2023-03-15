<template>
  <el-table
    :data="userProductInfo"
    :default-expand-all="true"
    height="645px"
    style="width: 100%;">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="卖家昵称 : " >
            <span>{{ props.row.sellerNick }}</span>
          </el-form-item>
          <el-form-item label="卖家 ID : ">
            <span>{{ props.row.sellerId }}</span>
          </el-form-item>
          <el-form-item label="公司 ID : ">
            <span>{{ props.row.corpId }}</span>
          </el-form-item>
          <el-form-item label="VIP等级 : ">
            <span>{{ props.row.vipflag }}</span>
          </el-form-item>
          <el-form-item label="是否主店 : ">
            <span>{{ props.row.isMain }}</span>
          </el-form-item>
          <el-form-item label="是否多店 : ">
            <span>{{ props.row.isMany }}</span>
          </el-form-item>
          <el-form-item label="是否需要授权 : ">
            <span>{{ props.row.isNeedauth }}</span>
          </el-form-item>
          <el-form-item label="是否沉默 : ">
            <span>{{ props.row.isSilent }}</span>
          </el-form-item>
          <el-form-item label="上一次付费时间 : ">
            <span>{{ props.row.lastPaidTime }}</span>
          </el-form-item>
          <el-form-item label="上一次活动时间 : ">
            <span>{{ props.row.lastactivedt }}</span>
          </el-form-item>
          <el-form-item label="上一次更新时间 : ">
            <span>{{ props.row.lastupdatetime }}</span>
          </el-form-item>
          <el-form-item label="订单结束时间 : ">
            <span>{{ props.row.orderCycleEnd }}</span>
          </el-form-item>
          <el-form-item label="最后修改时间 : ">
            <span>{{ props.row.revivalDate }}</span>
          </el-form-item>
          <el-form-item label="子时间 : ">
            <span>{{ props.row.subdatetime }}</span>
          </el-form-item>
          <el-form-item label="表 ID : ">
            <span>{{ props.row.listId }}</span>
          </el-form-item>
          <el-form-item label="W1到期时间 : ">
            <span>{{ props.row.w1Deadline }}</span>
          </el-form-item>
          <el-form-item label="访问令牌 : ">
            <span>{{ props.row.accessToken }}</span>
          </el-form-item>
          <el-form-item label="刷新令牌 : ">
            <span>{{ props.row.refreshToken }}</span>
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
      label="卖家ID"
      prop="sellerId">
    </el-table-column>
    <el-table-column
      align="center"
      label="第一次使用爱用时间"
      prop="createDate">
    </el-table-column>
    <el-table-column
      align="center"
      label="VIP等级"
      prop="vipflag">
    </el-table-column>
    <el-table-column
      align="center"
      label="是否多店"
      prop="isMany">
    </el-table-column>
    <el-table-column
      align="center"
      label="是否主店铺"
      prop="isMain">
    </el-table-column>
    <el-table-column
      align="center"
      label="w1授权到期时间"
      prop="w1Deadline">
    </el-table-column>
    <el-table-column
      align="center"
      label="w2授权到期时间"
      prop="w2Deadline">
    </el-table-column>
    <el-table-column
      align="center"
      label="订购到期时间"
      prop="orderCycleEnd">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "UserProductInfoTable",
  data() {
    return{
      userProductInfo: []
    }
  },
  methods:{
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
      this.userProductInfo = data;
    },
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
