<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
      <el-form-item label="日期查询" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" clearable placeholder="选择日期" size="small" type="date"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="线索来源" prop="cluesSource">
        <el-select v-model="queryParams.cluesSource" clearable placeholder="请选择线索来源" size="small">
          <el-option v-for="dict in dict.type.clues_source" :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:pool:add']" icon="el-icon-plus" plain size="mini" type="primary"
                   @click="handleAdd">添加线索
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-hasPermi="['crm:clues:transfer']" :disabled="!selected" icon="el-icon-s-promotion" plain size="mini"
                   type="warning" @click="handleTransfer">转移线索
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :border="true" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="线索名称" prop="cluesName"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="联系电话" prop="phone"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="线索状态" prop="cluesStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.clues_status" :value="scope.row.cluesStatus"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" align="center" label="线索来源" prop="cluesSource">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.clues_source" :value="scope.row.cluesSource"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" align="center" label="地区来源" prop="region">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.region_list" :value="scope.row.region"/>
        </template>
      </el-table-column>
      <el-table-column :show-overflow-tooltip="true" align="center" label="最新跟进记录" prop="lastUpdateRecord"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="创建时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column align="left" class="small-padding" fixed="right" label="操作" width="250px">
        <template slot-scope="scope">
          <el-button v-hasPermi="['crm:clues:transfer']" icon="el-icon-s-promotion" size="mini" type="text"
                     @click="handleTransfer(scope.row)">转移
          </el-button>
          <el-button v-hasPermi="['crm:clues:tocustomer']" icon="el-icon-refresh-left" size="mini" type="text"
                     @click="handleToCustomer(scope.row)">转成客户
          </el-button>
          <el-button v-hasPermi="['crm:clues:edit']" icon="el-icon-edit" size="mini" type="text"
                     @click="handleUpdate(scope.row)">修改
          </el-button>
          <el-button v-hasPermi="['crm:clues:remove']" icon="el-icon-delete" size="mini" type="text"
                     @click="handleDelete(scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :limit.sync="queryParams.pageSize" :page.sync="queryParams.pageNum" :total="total"
                @pagination="getList"/>

    <!-- 添加或修改线索对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" append-to-body width="680px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="线索名称" prop="cluesName">
              <el-input v-model="form.cluesName" placeholder="请输入线索名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地区" prop="region">
              <el-select v-model="form.region" placeholder="请选择地区">
                <el-option v-for="dict in dict.type.region_list" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
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
            <el-form-item label="线索来源" prop="cluesSource">
              <el-select v-model="form.cluesSource" placeholder="请选择线索来源">
                <el-option v-for="dict in dict.type.clues_source" :key="dict.value" :label="dict.label"
                           :value="dict.value"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="线索状态" prop="cluesStatus">
              <el-select v-model="form.cluesStatus" placeholder="请选择线索状态">
                <el-option v-for="dict in dict.type.clues_status" :key="dict.value" :label="dict.label"
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

    <TransferCluesComponent v-if="transferClues" ref="transferClues" @close-dialog="handleTransferCluesClose"/>
    <ToCustomerComponent v-if="toCustomer" ref="toCustomer" @close-dialog="handleToCustomerClose"/>
  </div>
</template>

<script>
import {
  listClues,
  getClues,
  delClues,
  addClues,
  updateClues,
} from '@/api/crm/clues'

import TransferCluesComponent from './Transfer'
import ToCustomerComponent from './ToCustomer'

export default {
  name: 'Clues',
  components: {
    TransferCluesComponent,
    ToCustomerComponent
  },
  dicts: [
    'clues_source',
    'customer_industry',
    'clues_status',
    'customer_rank',
    'region_list',
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
      // 线索表格数据
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
      rules: {
        cluesName: [
          {required: true, message: '线索名称不能为空', trigger: 'blur'},
        ],
        linkman: [
          {required: true, message: '联系人不能为空', trigger: 'blur'},
        ],
        phone: [
          {required: true, message: '联系电话不能为空', trigger: 'blur'},
        ],
      },
      transferClues: false,
      toCustomer: false,
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询线索列表 */
    getList() {
      this.loading = true
      listClues(this.queryParams).then((response) => {
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
        manager: null,
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
      this.title = '添加线索'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getClues(id).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改线索'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateClues(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addClues(this.form).then((response) => {
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
        .confirm('是否确认删除线索编号为"' + ids + '"的数据项？')
        .then(function () {
          return delClues(ids)
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
        '/crm/clues/export',
        {
          ...this.queryParams,
        },
        `我的线索_${new Date().getTime()}.xlsx`
      )
    },
    handleTransfer(row) {
      const ids = row.id || this.ids.join(",")
      this.transferClues = true
      this.$nextTick(() => {
        this.$refs.transferClues.open(ids)
      })
    },
    handleTransferCluesClose() {
      this.transferClues = false;
      this.getList();
    },
    handleToCustomer(row) {
      const id = row.id
      this.toCustomer = true
      this.$nextTick(() => {
        this.$refs.toCustomer.open(id)
      })
    },
    handleToCustomerClose() {
      this.toCustomer = false;
      this.getList();
    },
  },
}
</script>
