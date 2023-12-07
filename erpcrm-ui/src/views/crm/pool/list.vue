<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
      <el-form-item label="日期查询" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" clearable placeholder="选择日期" size="small" type="date"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="客户名称" prop="name">
        <el-input v-model="queryParams.name" clearable placeholder="请输入客户名称" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="queryParams.phone" clearable placeholder="请输入联系电话" size="small"
                  @keyup.enter.native="handleQuery"/>
      </el-form-item>

      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="left" label="客户名称" prop="name"/>
      <el-table-column align="left" label="公海类型" prop="poolType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pool_type" :value="scope.row.poolType"/>
        </template>
      </el-table-column>
      <el-table-column align="left" label="客户级别" prop="customerRank">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_rank" :value="scope.row.customerRank"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="最后跟进记录" prop="lastUpdateRecord"/>
      <el-table-column align="center" label="最后跟进时间" prop="lastFollowupTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastFollowupTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="转公海时间" prop="toPoolTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.toPoolTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="前负责人" prop="preOwner"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button v-hasPermi="['crm:pool:receive']" icon="el-icon-finished" size="mini" type="text"
                     @click="handleReceive(scope.row)">领取
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :limit.sync="queryParams.pageSize" :page.sync="queryParams.pageNum" :total="total"
                @pagination="getList"/>
  </div>
</template>

<script>
import {listCustomer, receiveCustomer} from '@/api/crm/pool'

export default {
  name: 'Pool',
  dicts: [
    'clues_source',
    'customer_industry',
    'clues_status',
    'customer_rank',
    'region_list',
    'customer_status',
    'pool_type',
  ],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户表格数据
      customerList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        name: null,
        linkman: null,
        phone: null,
        region: null,
        customerIndustry: null,
        customerRank: null,
        customerStatus: null,
        cluesName: null,
        cluesSource: null,
        cluesStatus: null,
        manager: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询客户列表 */
    getList() {
      this.loading = true
      listCustomer(this.queryParams).then((response) => {
        this.customerList = response.rows
        this.total = response.total
        this.loading = false
      })
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        'crm/pool/export',
        {
          ...this.queryParams,
        },
        `客户公海_${new Date().getTime()}.xlsx`
      )
    },
    handleReceive(row) {
      const id = row.id;
      receiveCustomer(id).then((response) => {
        this.$modal.msgSuccess('领取成功')
        this.getList()
      })
    }
  },
}
</script>
