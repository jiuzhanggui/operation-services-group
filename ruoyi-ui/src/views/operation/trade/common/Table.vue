<template>
  <el-col>
    <el-table
      :data="tableData"
      stripe
      height="645px"
      :formatter="dateFormatData"
      style="width: 100%; height: 100%; align-items: center;">
      <!--      动态列-->
      <el-table-column v-for="(item,index) in tableTitle" align="center" :prop="item" :label="item" :key="index"
                       min-width="160px"
                       resizable
                       :show-overflow-tooltip="true"
                       style=" white-space: pre-line;">
      </el-table-column>
    </el-table>
  </el-col>
</template>
<script>
export default {
  name: "Table",

  data() {
    return {
      /**
       * 动态列的名称数据
       */
      tableTitle: [],
      /**
       * 数据内容
       */
      tableData: []
    }
  },
  methods: {
    // 获取父组件传的数据
    getData(data) {
      if (data == null) {
        this.$message.warning("数据不存在")
      }
      if (data[0] != null) {
        // 获取所有列名
        let tableTitles = Object.keys(data[0]);
        // 动态添表头
        this.addTableTitle(tableTitles);
        // 添加Table数据
        this.addTableData(data)
      } else {
        this.$message.warning("数据不存在")
      }
    },

    /**
     * 添加表头
     * @param tableTitles 表头参数数组
     */
    addTableTitle(tableTitles) {
      this.tableTitle = [];
      for (let i = 0; i < tableTitles.length; i++) {
        this.tableTitle.push(tableTitles[i]);
      }
    },

    /**
     * 添加表中数据
     * @param data
     */
    addTableData(data) {
      this.tableData = data;
    },

    /**
     * 时间格式化处理
     * @param row
     */
    dateFormatData(row, column, cellValue, index) {
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

</style>
