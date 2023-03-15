<template>
  <div style="margin-left: 100px; margin-right: 100px; margin-top: 25px;">
    <el-row :gutter="0" style="width:100%;">
      <el-form ref="userInfo" :model="userInfo" :rules="rules" size="medium" label-width="100px">
        <el-col :span="5">
          <el-form-item label="卖家昵称" prop="sellerNick">
            <el-input v-model="userInfo.sellerNick" placeholder="sellerNick" clearable
                      :style="{width: '100%' }"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="订单号" prop="sellerNick">
            <el-input v-model="userInfo.tid" placeholder="tid" clearable
                      :style="{width: '100%' }"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="平台ID" prop="platformId">
            <el-select v-model="userInfo.platformId" placeholder="请选择平台ID" :style="{width: '100%'}">
              <el-option v-for="(item, index) in platformIdOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="应用" prop="appName">
            <el-select v-model="userInfo.appName" placeholder="请选择应用" :style="{width: '100%'}">
              <el-option v-for="(item, index) in appNameOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item size="large" style="margin-right: auto">
            <el-col :span="12">
              <el-button type="primary" @click="toAnalysisResult">订单问题分析</el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="primary" @click="toTable">订单信息查询</el-button>
            </el-col>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-divider content-position="center">结果展示</el-divider>
    <template v-if="sonComponentName=== 'problemAnalysisResults' ">
      <ProblemAnalysisResults :userInfo="userInfo" :analyseType="type"/>
    </template>

    <template v-if="sonComponentName === 'orderInfoList'">
      <OrderInfoList :userInfo="userInfo"/>
    </template>
  </div>
</template>
<script>
import UserInfoList from "@/views/operation/trade/view/UserInfoList";
import ProblemAnalysisResults from "@/views/operation/trade/view/ProblemAnalysisResults";
import OrderInfoList from "@/views/operation/trade/view/OrderInfoList";

export default {
  components: {
    ProblemAnalysisResults,
    OrderInfoList
  },
  props: [],
  data() {
    return {
      userInfo: {
        sellerNick: '路口的明天',
        tid: '3245487375663460955',
        platformId: '淘宝',
        appName: '交易',
      },
      rules: {
        sellerNick: [{
          required: true,
          message: 'sellerNick',
          trigger: 'blur'
        }],
        platformId: [{
          required: true,
          message: '请选择平台ID',
          trigger: 'change'
        }],
        appName: [{
          required: true,
          message: '请选择应用',
          trigger: 'change'
        }],
      },
      platformIdOptions: [{
        "label": "淘宝",
        "value": "TAO"
      }, {
        "label": "拼多多",
        "value": "PDD"
      }, {
        "label": "1688",
        "value": 1688
      }, {
        "label": "抖店",
        "value": "DOUDIAN"
      }, {
        "label": "快手",
        "value": "KUAISHOU"
      }, {
        "label": "视频号",
        "value": "WXVIDEOSHOP"
      }],
      appNameOptions: [{
        "label": "交易",
        "value": "trade"
      }, {
        "label": "商品",
        "value": "item"
      }, {
        "label": "管店",
        "value": "guanDian"
      }],
      analysisResult: '',
      sonComponentName: ''
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
  },
  methods: {
    toAnalysisResult() {
      if (this.userInfo.sellerNick === '') {
        this.$message({
          type: 'warning',
          message: '请输入查询参数',
          center: true
        });
      } else {
        this.sonComponentName = 'problemAnalysisResults';
      }
    },

    toTable() {
      if (this.userInfo.tid === '') {
        this.$message({
          type: 'warning',
          message: '请输入查询参数',
          center: true
        });
      } else {
        this.sonComponentName = 'orderInfoList';
      }
    }
  }
}

</script>
<style>
</style>
