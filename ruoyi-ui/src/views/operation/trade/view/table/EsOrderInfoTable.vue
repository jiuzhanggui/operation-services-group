<template>
  <el-table
    :data="esOrderInfo"
    :default-expand-all="true"
    height="645px"
    style="width: 100%; align-items: center;">
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="卖家昵称 : ">
            <span>{{ props.row.sellerNick }}</span>
          </el-form-item>
          <el-form-item label="卖家 ID : ">
            <span>{{ props.row.sellerId }}</span>
          </el-form-item>
          <el-form-item label="订单状态 : ">
            <span>{{ props.row.taoStatus }}</span>
          </el-form-item>
          <el-form-item label="退款状态 : ">
            <span>{{ props.row.refundStatus }}</span>
          </el-form-item>
          <el-form-item label="售后状态 : ">
            <span>{{ props.row.afterSaleRefundStatus }}</span>
          </el-form-item>
          <el-form-item label="买家昵称 : ">
            <span>{{ props.row.buyerNick }}</span>
          </el-form-item>
          <el-form-item label="商品标题 : ">
            <span>{{ props.row.title }}</span>
          </el-form-item>
          <el-form-item label="子单信息 : ">
            <span>{{ props.row.subOrders }}</span>
          </el-form-item>
          <el-form-item label="收件人信息 : ">
            <span>{{ props.row.receiver }}</span>
          </el-form-item>
          <el-form-item label="收件人姓名 : ">
            <span>{{ props.row.receiverName }}</span>
          </el-form-item>
          <el-form-item label="SKU名称 : ">
            <span>{{ props.row.skuName }}</span>
          </el-form-item>
          <el-form-item label="卖家是否评论 : ">
            <span>{{ props.row.sellerRate }}</span>
          </el-form-item>
          <el-form-item label="买家是否评论 : ">
            <span>{{ props.row.buyerRate }}</span>
          </el-form-item>
          <el-form-item label="是否风控订单 : ">
            <span>{{ props.row.isRiskControl }}</span>
          </el-form-item>
          <el-form-item label="是否手动合单 : ">
            <span>{{ props.row.isManual }}</span>
          </el-form-item>
          <el-form-item label="是否合单主单 : ">
            <span>{{ props.row.mergeTradeStatus }}</span>
          </el-form-item>
          <el-form-item label="是否合单子单 : ">
            <span>{{ props.row.isCombine }}</span>
          </el-form-item>
          <el-form-item label="合单Tid : ">
            <span>{{ props.row.mergeTid }}</span>
          </el-form-item>
          <el-form-item label="合单爱用Tid : ">
            <span>{{ props.row.mergeAyTid }}</span>
          </el-form-item>
          <el-form-item label="Oid : ">
            <span>{{ props.row.oid }}</span>
          </el-form-item>
          <el-form-item label="物流公司 : ">
            <span>{{ props.row.logisticsCompany }}</span>
          </el-form-item>
          <el-form-item label="运单号 : ">
            <span>{{ props.row.invoiceNo }}</span>
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
      label="tid"
      prop="tid">
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
      label="是否退款订单"
      prop="isRefund">
    </el-table-column>
    <el-table-column
      align="center"
      label="订单状态"
      prop="taoStatus">
    </el-table-column>
    <el-table-column
      align="center"
      label="退款状态"
      prop="refundStatus">
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  name: "EsOrderInfoTable",
  data() {
    return {
      esOrderInfo: []
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
      this.esOrderInfo = data;
    },
    dateFormat(row, column, cellValue, index) {
      if (cellValue instanceof Date) {
        if (cellValue != null) {
          //若传入的dateTime为字符串类型，需要进行转换成数值，若不是无需下面注释代码
          //var time = parseInt(dateTime)
          let date = new Date(cellValue);
          //获取年份
          let YY = date.getFullYear();
          //获取月份
          let MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
          //获取日期
          let DD = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());
          let hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours());
          let mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
          let ss = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
          return YY + '-' + MM + '-' + DD + ' ' + hh + ':' + mm + ':' + ss;
        }
      }
    }
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
