<template>
  <el-table
    :data="tcOrderData"
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
          <el-form-item label="订单创建时间 : ">
            <span>{{ props.row.created }}</span>
          </el-form-item>
          <el-form-item label="Tid : ">
            <span>{{ props.row.tid }}</span>
          </el-form-item>
          <el-form-item label="买家 ID : ">
            <span>{{ props.row.buyerNick }}</span>
          </el-form-item>
          <el-form-item label="爱用状态 : ">
            <span>{{ props.row.ayStatus }}</span>
          </el-form-item>
          <el-form-item label="是否退款 : ">
            <span>{{ props.row.isRefund }}</span>
          </el-form-item>
          <el-form-item label="付款时间 : ">
            <span>{{ props.row.payTime }}</span>
          </el-form-item>
          <el-form-item label="收件人姓名 : ">
            <span>{{ props.row.receiverName }}</span>
          </el-form-item>
          <el-form-item label="收件人地址 : ">
            <span>{{ props.row.receiverAddress }}</span>
          </el-form-item>
          <el-form-item label="收件人城市 : ">
            <span>{{ props.row.receiverCity }}</span>
          </el-form-item>
          <el-form-item label="收件人国家 : ">
            <span>{{ props.row.receiverCountry }}</span>
          </el-form-item>
          <el-form-item label="收件人城镇 : ">
            <span>{{ props.row.receiverDistrict }}</span>
          </el-form-item>
          <el-form-item label="货到付款状态 : ">
            <span>{{ props.row.codStatus }}</span>
          </el-form-item>
          <el-form-item label="卖家评论状态 : ">
            <span>{{ props.row.sellerRate }}</span>
          </el-form-item>
          <el-form-item label="买家评论状态 : ">
            <span>{{ props.row.buyerRate }}</span>
          </el-form-item>
          <el-form-item label="承诺服务类型 : ">
            <span>{{ props.row.promiseServiceType }}</span>
          </el-form-item>
          <el-form-item label="平台补贴费用 : ">
            <span>{{ props.row.platformSubsidyFee }}</span>
          </el-form-item>
          <el-form-item label="是否信用支付 : ">
            <span>{{ props.row.isCreditPay }}</span>
          </el-form-item>
          <el-form-item label="爱用链路状态 : ">
            <span>{{ props.row.ayOrderType }}</span>
          </el-form-item>
          <el-form-item label="服务标签 : ">
            <span>{{ props.row.serviceTags }}</span>
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
      label="买家ID"
      prop="sellerId">
    </el-table-column>
    <el-table-column
      align="center"
      label="Tid"
      prop="tid">
    </el-table-column>
    <el-table-column
      align="center"
      label="订单状态"
      prop="taoStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="创建时间"
      prop="created">
    </el-table-column>
    <el-table-column
      align="center"
      label="修改时间"
      prop="modified">
    </el-table-column>
    <el-table-column
      align="center"
      label="平台ID"
      prop="storeId">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "TcOrderTable",
  data() {
    return{
      tcOrderData: []
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
      this.tcOrderData = data;
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
