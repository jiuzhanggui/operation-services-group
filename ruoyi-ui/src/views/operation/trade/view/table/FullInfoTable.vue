<template>
  <el-table
    :data="fullInfoData"
    :default-expand-all="true"
    height="645px"
    style="width: 100%; align-items: center;">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="Oid : " >
            <span>{{ props.row.oid }}</span>
          </el-form-item>
          <el-form-item label="oidStr : ">
            <span>{{ props.row.oidStr }}</span>
          </el-form-item>
          <el-form-item label="商品价格 : ">
            <span>{{ props.row.price }}</span>
          </el-form-item>
          <el-form-item label="折扣费用 : ">
            <span>{{ props.row.discountFee }}</span>
          </el-form-item>
          <el-form-item label="分摊之后的实付金额 : ">
            <span>{{ props.row.divideOrderFee }}</span>
          </el-form-item>
          <el-form-item label="支付金额 : ">
            <span>{{ props.row.payment }}</span>
          </el-form-item>
          <el-form-item label="总费用 : ">
            <span>{{ props.row.totalFee }}</span>
          </el-form-item>
          <el-form-item label="发票编号 : ">
            <span>{{ props.row.invoiceNo }}</span>
          </el-form-item>
          <el-form-item label="商品数字ID : ">
            <span>{{ props.row.numIid }}</span>
          </el-form-item>
          <el-form-item label="子订单预计发货时间 : ">
            <span>{{ props.row.estimateConTime }}</span>
          </el-form-item>
          <el-form-item label="是否代销 : ">
            <span>{{ props.row.isDaixiao }}</span>
          </el-form-item>
          <el-form-item label="子订单交易结束时间 : ">
            <span>{{ props.row.endTime }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      label="Oid"
      prop="oid">
    </el-table-column>
    <el-table-column
      align="center"
      label="订单状态"
      prop="status">
    </el-table-column>
    <el-table-column
      align="center"
      label="退款状态"
      prop="refundStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="商品标题"
      prop="title">
    </el-table-column>
    <el-table-column
      align="center"
      label="快递公司"
      prop="logisticsCompany">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "FullInfoTable",
  data() {
    return{
      fullInfoData: []
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
      this.fullInfoData = data;
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
