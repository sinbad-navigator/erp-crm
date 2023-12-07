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
      <el-form-item label="客户级别" prop="customerRank">
        <el-select v-model="queryParams.customerRank" clearable placeholder="请选择客户级别" size="small">
          <el-option v-for="dict in dict.type.customer_rank" :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="客户状态" prop="customerStatus">
        <el-select v-model="queryParams.customerStatus" clearable placeholder="请选择客户状态" size="small">
          <el-option v-for="dict in dict.type.customer_status" :key="dict.value" :label="dict.label"
                     :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="业务员" prop="owner">
        <el-select v-model="form.owner" placeholder="请选择业务员">
          <el-option v-for="owner in ownerList" :key="owner.userName" :label="owner.nickName"
                     :value="owner.userName"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column :show-overflow-tooltip="true" align="center" type="selection" width="55"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户名称" prop="name"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="地区" prop="region"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="联系人" prop="linkman"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="联系电话" prop="phone"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户级别" prop="customerRank"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户行业" prop="customerIndustry"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="最新跟进记录" prop="lastUpdateRecord"
                       width="120px"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="负责人" prop="owner"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="创建时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :limit.sync="queryParams.pageSize" :page.sync="queryParams.pageNum" :total="total"
                @pagination="getList"/>
  </div>
</template>

<script>
import {
  listDealCustomer,
} from '@/api/crm/customer'
import {
  listOwner
} from '@/api/crm/owner'

export default {
  name: 'Customer',
  dicts: [
    'clues_source',
    'customer_industry',
    'clues_status',
    'customer_rank',
    'deal_status',
    'customer_status',
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
      // 是否勾选
      selected: false,
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
        owner: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: '客户名称不能为空', trigger: 'blur'},
        ],
        linkman: [
          {required: true, message: '联系人不能为空', trigger: 'blur'},
        ],
        phone: [
          {required: true, message: '联系电话不能为空', trigger: 'blur'},
        ],
      },
      transferCustomer: false,
      toPool: false,
    }
  },
  created() {
    this.getList();
    this.getOwnerList();
  },
  methods: {
    /** 查询成交客户列表 */
    getList() {
      this.loading = true
      listDealCustomer(this.queryParams).then((response) => {
        this.customerList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    getOwnerList() {
      this.loading = true
      listOwner(this.queryParams).then((response) => {
        this.ownerList = response.data
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
      this.selected = selection.length
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        'crm/customer/export',
        {
          ...this.queryParams,
        },
        `customer_${new Date().getTime()}.xlsx`
      )
    },
  },
}
</script>
