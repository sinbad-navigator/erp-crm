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
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:customer:add']" icon="el-icon-plus" plain size="mini" type="primary"
                   @click="handleAdd">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:customer:toPool']" :disabled="!selected" icon="el-icon-refresh-right" plain size="mini"
                   type="warning" @click="handleToPool">移入公海
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:customer:transfer']" :disabled="!selected" icon="el-icon-s-promotion" plain size="mini"
                   type="warning" @click="handleTransfer">转移客户
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:customer:remove']" icon="el-icon-delete" plain size="mini" type="danger"
                   @click="handleDelete">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:customer:export']" icon="el-icon-download" plain size="mini" type="info"
                   @click="handleExport">导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column :show-overflow-tooltip="true" align="center" type="selection" width="55"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户名称" prop="name"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="地区" prop="region"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="联系人" prop="linkman"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="联系电话" prop="phone"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户级别" prop="customerRank"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="客户行业" prop="customerIndustry"/>
      <el-table-column align="center" label="成交状态" prop="dealStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.deal_status" :value="scope.row.dealStatus"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" align="center" label="最新跟进记录" prop="lastUpdateRecord"
                       width="120px"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="负责人" prop="owner"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="创建时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" class="small-padding" fixed="right" label="操作" width="250px">
        <template slot-scope="scope">
          <el-button v-hasPermi="['crm:customer:followup']" icon="el-icon-edit-outline" size="mini" type="text"
                     @click="handleComment(scope.row)">写跟进
          </el-button>
          <el-button v-hasPermi="['crm:customer:transfer']" icon="el-icon-s-promotion" size="mini" type="text"
                     @click="handleTransfer(scope.row)">转移
          </el-button>
          <el-button v-hasPermi="['crm:customer:edit']" icon="el-icon-edit" size="mini" type="text"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button v-hasPermi="['crm:customer:remove']" icon="el-icon-delete" size="mini" type="text"
                     @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :limit.sync="queryParams.pageSize" :page.sync="queryParams.pageNum" :total="total"
                @pagination="getList"/>

    <!-- 添加或修改客户对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" append-to-body width="680px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入客户名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户编号" prop="code">
              <el-input v-model="form.code" placeholder="请输入客户编号"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="联系人" prop="linkman">
              <el-input v-model="form.linkman" placeholder="请输入联系人"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="地区" prop="region">
              <el-select v-model="form.region" placeholder="请选择地区">
                <el-option v-for="dict in dict.type.region_list" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户行业" prop="customerIndustry">
              <el-select v-model="form.customerIndustry" placeholder="请选择客户行业">
                <el-option v-for="dict in dict.type.customer_industry" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="客户级别" prop="customerRank">
              <el-select v-model="form.customerRank" placeholder="请选择客户级别">
                <el-option v-for="dict in dict.type.customer_rank" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户状态" prop="customerStatus">
              <el-select v-model="form.customerStatus" placeholder="请选择客户状态">
                <el-option v-for="dict in dict.type.customer_status" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入内容" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <TransferCustomerComponent v-if="transferCustomer" ref="transferCustomer"
                               @close-dialog="handleTransferCustomerClose"/>
    <ToPoolComponent v-if="toPool" ref="toPool" @close-dialog="handleToPoolClose"/>
    <CommentComponent v-if="commentCustomer" ref="commentCustomer" @close-dialog="handleCommentClose"/>
  </div>
</template>

<script>
import {
  listCustomer,
  getCustomer,
  delCustomer,
  addCustomer,
  updateCustomer,
} from '@/api/crm/customer'

import TransferCustomerComponent from './Transfer'
import ToPoolComponent from './ToPool'
import CommentComponent from './Comment'

export default {
  name: 'Customer',
  components: {
    TransferCustomerComponent,
    ToPoolComponent,
    CommentComponent
  },
  dicts: [
    'clues_source',
    'customer_industry',
    'clues_status',
    'customer_rank',
    'region_list',
    'customer_status',
    'deal_status'
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
      // 非勾选禁用
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
      commentCustomer: false
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
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        code: null,
        name: null,
        linkman: null,
        phone: null,
        region: null,
        customerIndustry: null,
        customerRank: null,
        customerStatus: null,
        status: '0',
        cluesName: null,
        cluesSource: null,
        cluesStatus: null,
        dealStatus: '0',
        owner: null,
        delFlag: null,
        version: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
      }
      this.resetForm('form')
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加客户'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getCustomer(id).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改客户'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomer(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addCustomer(this.form).then((response) => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal
        .confirm('是否确认删除客户编号为"' + ids + '"的数据项？')
        .then(function () {
          return delCustomer(ids)
        })
        .then(() => {
          this.getList()
          this.$modal.msgSuccess('删除成功')
        })
        .catch(() => {
        })
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
    handleTransfer(row) {
      const ids = row.id || this.ids.join(",")
      this.transferCustomer = true
      this.$nextTick(() => {
        this.$refs.transferCustomer.open(ids)
      })
    },
    handleTransferCustomerClose() {
      this.transferCustomer = false;
      this.getList();
    },
    handleToPool(row) {
      const ids = row.id || this.ids.join(",")
      this.toPool = true
      this.$nextTick(() => {
        this.$refs.toPool.open(ids)
      })
    },
    handleToPoolClose() {
      this.toPool = false;
      this.getList();
    },

    handleComment(row) {
      const id = row.id
      this.commentCustomer = true
      this.$nextTick(() => {
        this.$refs.commentCustomer.open(id)
      })
    },
    handleCommentClose() {
      this.commentCustomer = false;
      this.getList();
    },
  },
}
</script>
