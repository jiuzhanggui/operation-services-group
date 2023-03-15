<template>
  <el-tabs type="border-card">
    <el-tab-pane>
      <div slot="label" @click="getUserExtInfo">ext用户存单状态表</div>
      <ExtTable ref="extParams"/>
    </el-tab-pane>
    <el-tab-pane label="user_productInfo用户信息表">
      <div slot="label" @click="getUserProductInfo">user_productInfo用户信息表</div>
      <UserProductInfoTable ref="userProductParams"></UserProductInfoTable>
    </el-tab-pane>
    <el-tab-pane label="order_search订购记录表">
      <div slot="label" @click="getOrderSearchInfo">order_search订购记录表</div>
      <OrderSearchTable ref="OrderSearchParams"/>
    </el-tab-pane>
    <el-tab-pane label="open_user开户操作日志表">
      <div slot="label" @click="getOpenUserInfo">open_user开户操作日志表</div>
      <OpenUserTable ref="openUserParams"/>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import Table from "@/views/operation/trade/common/Table";
// import {getExInfoDataAPI, getUserProductDataAPI, getOrderSearchDataAPI, getOpenUserDataAPI} from "@/api/api";
import ExtTable from "./table/ExtTable";
import UserProductInfoTable from "@/views/operation/trade/view/table/UserProductInfoTable";
import OrderSearchTable from "@/views/operation/trade/view/table/OrderSearchTable";
import OpenUserTable from "@/views/operation/trade/view/table/OpenUserTable";

export default {
  name: "UserInfoList",
  components: {
    Table, ExtTable, UserProductInfoTable, OrderSearchTable, OpenUserTable
  },

  props: {
    userInfo: {
      type: Object,
      default: null
    }
  },

  data() {
    return {
      userResultList: [],
    }
  },

  created() {
    this.getUserExtInfo()
  },

  methods: {
    /**
     * 获取用户开户记录
     */
    getOpenUserInfo() {
      let userInfo = this.userInfo;
      if (userInfo == null) {
        this.$message({
          type: 'warning',
          message: '请输入查询参数',
          center: true
        });
      } else {

        // getOpenUserDataAPI(userInfo).then(res => {
        //   let body = res.data.body;
        //   this.userResultList = body
        //   this.$refs.openUserParams.getData(body)
        // })
      }
    },

    /**
     * 获取用户订购信息
     */
    getOrderSearchInfo() {
      let userInfo = this.userInfo;
      if (userInfo == null) {
        this.$message({
          type: 'warning',
          message: '请输入查询参数',
          center: true
        });
      } else {
        // getOrderSearchDataAPI(userInfo).then(res => {
        //   let body = res.data.body;
        //   this.userResultList = body
        //   this.$refs.OrderSearchParams.getData(body)
        // })
      }
    },

    /**
     * 获取用户信息
     */
    getUserProductInfo() {
      let userInfo = this.userInfo;
      if (userInfo == null) {
        this.$message({
          type: 'warning',
          message: '请输入查询参数',
          center: true
        });
      } else {
        // getUserProductDataAPI(userInfo).then(res => {
        //   let body = res.data.body;
        //   this.userResultList = body
        //   this.$refs.userProductParams.getData(body)
        // })
      }
    },

    /**
     * 获取EXT表信息
     */
    getUserExtInfo() {
      let userInfo = this.userInfo;
      // 调用api获取数据
      // getExInfoDataAPI(userInfo).then(res => {
      //   let body = res.data.body;
      //   this.userResultList = body
      //   this.$refs.extParams.getData(body)
      // })

      // 模拟数据
      // let body = res.data.body;
      // this.userResultList = body

      setTimeout(() => {
        let user = new Object({"sellerNick": "dcd222ding", "sellerId": "123455"});
        let array = new Array(user);
        this.userResultList = array
        // 调用extParams的getData()方法
        this.$refs.extParams.getData(array)
      }, 0)



    }
  }
}
</script>

<style scoped>

</style>
