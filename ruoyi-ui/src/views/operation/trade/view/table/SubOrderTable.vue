<template>
  <el-table
    :data="subOrderData"
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
          <el-form-item label="公司 ID : ">
            <span>{{ props.row.corpId }}</span>
          </el-form-item>
          <el-form-item label="商店编号 : ">
            <span>{{ props.row.storeId }}</span>
          </el-form-item>
          <el-form-item label="订单创建时间 : ">
            <span>{{ props.row.created }}</span>
          </el-form-item>
          <el-form-item label="订单结束时间 : ">
            <span>{{ props.row.endTime }}</span>
          </el-form-item>
          <el-form-item label="爱用Tid : ">
            <span>{{ props.row.ayTid }}</span>
          </el-form-item>
          <el-form-item label="爱用Oid : ">
            <span>{{ props.row.ayOid }}</span>
          </el-form-item>
          <el-form-item label="爱用状态 : ">
            <span>{{ props.row.ayStatus }}</span>
          </el-form-item>
          <el-form-item label="交易商品对应类目 : ">
            <span>{{ props.row.cid }}</span>
          </el-form-item>
          <el-form-item label="支付金额 : ">
            <span>{{ props.row.payment }}</span>
          </el-form-item>
          <el-form-item label="单价 : ">
            <span>{{ props.row.price }}</span>
          </el-form-item>
          <el-form-item label="退款ID : ">
            <span>{{ props.row.refundId }}</span>
          </el-form-item>
          <el-form-item label="买家评论 : ">
            <span>{{ props.row.sellerRate }}</span>
          </el-form-item>
          <el-form-item label="买家评论 : ">
            <span>{{ props.row.buyerRate }}</span>
          </el-form-item>
          <el-form-item label="SKU ID : ">
            <span>{{ props.row.skuId }}</span>
          </el-form-item>
          <el-form-item label="SKU值 : ">
            <span>{{ props.row.skuPropertiesName }}</span>
          </el-form-item>
          <el-form-item label="商品标题 : ">
            <span>{{ props.row.title }}</span>
          </el-form-item>
          <el-form-item label="订单来源 : ">
            <span>{{ props.row.taoOrderFrom }}</span>
          </el-form-item>
          <el-form-item label="是否超卖 : ">
            <span>{{ props.row.isOversold }}</span>
          </el-form-item>
          <el-form-item label="是否拆单发货 : ">
            <span>{{ props.row.isSplit }}</span>
          </el-form-item>
          <el-form-item label="是否赠品 : ">
            <span>{{ props.row.isGift }}</span>
          </el-form-item>
          <el-form-item label="是否商家承担手续费 : ">
            <span>{{ props.row.isFqgSFee }}</span>
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
      label="Tid"
      prop="tid">
    </el-table-column>
    <el-table-column
      align="center"
      label="Oid"
      prop="oid">
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
  name: "SubOrderTable",
  data() {
    return{
      subOrderData: []
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
      this.subOrderData = data;
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
