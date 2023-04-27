<template>
  <div style="margin-left: 100px; margin-right: 100px; margin-top: 25px; height: 100%">
    <el-row :gutter="0" style="width:100%;">
      <el-form ref="userInfo" :model="userInfo" :rules="rules" size="medium" label-width="100px">
        <el-col :span="6">
          <el-form-item label="卖家昵称" prop="sellerNick">
            <el-input v-model="userInfo.sellerNick" placeholder="sellerNick" clearable
                      :style="{width: '80%' }"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="平台ID" prop="platformId">
            <el-select v-model="userInfo.platformId" placeholder="请选择平台ID" :style="{width: '80%'}">
              <el-option v-for="(item, index) in platformIdOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="应用" prop="appName">
            <el-select v-model="userInfo.appName" placeholder="请选择应用" :style="{width: '80%'}">
              <el-option v-for="(item, index) in appNameOptions" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item size="large" style="margin-right: auto">
            <el-col :span="12">
              <el-button type="primary" @click="toAnalysisResult">用户问题分析</el-button>
            </el-col>
            <el-col :span="12" >
              <el-button type="primary" @click="toTable" >用户信息查询</el-button>
            </el-col>
          </el-form-item>
        </el-col>
      </el-form>

    </el-row>
    <el-divider content-position="center">结果展示</el-divider>
    <div style="height: 100%">
      <template v-if="sonComponentName ==='problemAnalysisResults'" >
        <ProblemAnalysisResults :userInfo="this.userInfo" :analyseType="type" />
      </template>

      <template v-if="sonComponentName ==='userInfoList'" >
        <UserInfoList :userInfo="this.userInfo"/>
      </template>
    </div>
  </div>
</template>
<script>
import UserInfoList from "@/views/operation/trade/view/UserInfoList";
import ProblemAnalysisResults from "@/views/operation/trade/view/ProblemAnalysisResults";

export default {
  components: {
    UserInfoList,
    ProblemAnalysisResults
  },
  props: [],
  data() {
    return {
      userInfo: {
        sellerNick: '',
        platformId: 'TAO',
        appName: 'trade',
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
      analysisResult:'',
      sonComponentName: ''
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    toAnalysisResult() {
      this.$refs['userInfo'].validate(valid => {
        if (!valid) return
        this.sonComponentName = "problemAnalysisResults"
      })
    },

    toTable() {
      this.$refs['userInfo'].validate(valid => {
        if (!valid) return
        this.sonComponentName = "userInfoList"
      })
    },
  }
}

</script>
<style>
</style>
