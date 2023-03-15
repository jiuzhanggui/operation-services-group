<template>
  <el-table
    :data="refundOrderData"
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
          <el-form-item label="订单创建时间 : ">
            <span>{{ props.row.created }}</span>
          </el-form-item>
          <el-form-item label="订单修改时间 : ">
            <span>{{ props.row.modified }}</span>
          </el-form-item>
          <el-form-item label="不需要客服介入 : ">
            <span>{{ props.row.csStatus }}</span>
          </el-form-item>
          <el-form-item label="退款说明 : ">
            <span>{{ props.row.desc }}</span>
          </el-form-item>
          <el-form-item label="买家是否需要退款 : ">
            <span>{{ props.row.hasGoodReturn }}</span>
          </el-form-item>
          <el-form-item label="商品数字ID : ">
            <span>{{ props.row.numIid }}</span>
          </el-form-item>
          <el-form-item label="商品状态 : ">
            <span>{{ props.row.goodStatus }}</span>
          </el-form-item>
          <el-form-item label="商品外部商家编码 : ">
            <span>{{ props.row.outerId }}</span>
          </el-form-item>
          <el-form-item label="支付给卖家的金额 : ">
            <span>{{ props.row.payment }}</span>
          </el-form-item>
          <el-form-item label="商品价格 : ">
            <span>{{ props.row.price }}</span>
          </el-form-item>
          <el-form-item label="退款原因 : ">
            <span>{{ props.row.reason }}</span>
          </el-form-item>
          <el-form-item label="退款金额 : ">
            <span>{{ props.row.refundFee }}</span>
          </el-form-item>
          <el-form-item label="退款阶段 : ">
            <span>{{ props.row.refundPhase }}</span>
          </el-form-item>
          <el-form-item label="退款版本号 : ">
            <span>{{ props.row.refundVersion }}</span>
          </el-form-item>
          <el-form-item label="SKU名称 : ">
            <span>{{ props.row.sku }}</span>
          </el-form-item>
          <el-form-item label="商品标题 : ">
            <span>{{ props.row.title }}</span>
          </el-form-item>
          <el-form-item label="总金额 : ">
            <span>{{ props.row.totalFee }}</span>
          </el-form-item>
          <el-form-item label="地址 : ">
            <span>{{ props.row.address }}</span>
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
      label="退款状态"
      prop="refundStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="订单状态"
      prop="status">
    </el-table-column>
    <el-table-column
      align="center"
      label="退款ID"
      prop="refundId">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "RefundApiTable",
  data() {
    return{
      refundOrderData: []
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
      this.refundOrderData = data;
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
