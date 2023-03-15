<template>
  <el-table
    :data="userProductInfo"
    height="645px"
    style="width: 100%;">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="卖家昵称 : " >
            <span>{{ props.row.nick }}</span>
          </el-form-item>
          <el-form-item label="应用昵称 : ">
            <span>{{ props.row.articleName }}</span>
          </el-form-item>
          <el-form-item label="商品昵称 : ">
            <span>{{ props.row.articleItemName }}</span>
          </el-form-item>
          <el-form-item label="订单 ID : ">
            <span>{{ props.row.orderId }}</span>
          </el-form-item>
          <el-form-item label="应用收费代码 : ">
            <span>{{ props.row.bizOrderId }}</span>
          </el-form-item>
          <el-form-item label="创建时间 : ">
            <span>{{ props.row.createDate }}</span>
          </el-form-item>
          <el-form-item label="事件名 : ">
            <span>{{ props.row.eventName }}</span>
          </el-form-item>
          <el-form-item label="推广位 : ">
            <span>{{ props.row.extension }}</span>
          </el-form-item>
          <el-form-item label="原价(分) : ">
            <span>{{ props.row.fee }}</span>
          </el-form-item>
          <el-form-item label="来自应用 : ">
            <span>{{ props.row.from }}</span>
          </el-form-item>
          <el-form-item label="优惠金额(分) : ">
            <span>{{ props.row.promFee }}</span>
          </el-form-item>
          <el-form-item label="是否为活动 : ">
            <span>{{ props.row.hasAct }}</span>
          </el-form-item>
          <el-form-item label="实付金额(分) : ">
            <span>{{ props.row.totalPayFee }}</span>
          </el-form-item>
          <el-form-item label="退款金额(分) : ">
            <span>{{ props.row.refundFee }}</span>
          </el-form-item>
          <el-form-item label="创意 ID : ">
            <span>{{ props.row.openCid }}</span>
          </el-form-item>
          <el-form-item label="推广位 : ">
            <span>{{ props.row.originality }}</span>
          </el-form-item>
          <el-form-item label="提示类型 : ">
            <span>{{ props.row.secondaryClass }}</span>
          </el-form-item>
          <el-form-item label="一级分类 : ">
            <span>{{ props.row.primaryClass }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      label="卖家昵称"
      prop="nick">
    </el-table-column>
    <el-table-column
      align="center"
      label="应用昵称"
      prop="articleName">
    </el-table-column>
    <el-table-column
      align="center"
      label="商品模型名称"
      prop="articleItemName">
    </el-table-column>
    <el-table-column
      align="center"
      label="订购周期"
      prop="orderCycle">
    </el-table-column>
    <el-table-column
      align="center"
      label="订购开始时间"
      prop="orderCycleStart">
    </el-table-column>
    <el-table-column
      align="center"
      label="订购结束时间"
      prop="orderCycleEnd">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "OrderSearchTable",
  data() {
    return{
      userProductInfo: []
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
