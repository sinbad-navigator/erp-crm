<template>
  <div class="app-container">
    <el-form
      v-show="showSearch"
      ref="queryForm"
      :inline="true"
      :model="queryParams"
      label-width="68px"
    >
      <el-form-item label="租户ID" prop="tenant">
        <el-input
          v-model="queryParams.tenant"
          clearable
          placeholder="请输入租户ID"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="tenant">
        <el-input
          v-model="queryParams.username"
          clearable
          placeholder="请输入用户名"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          clearable
          placeholder="租户状态"
          size="small"
        >
          <el-option
            v-for="dict in dict.type.sys_normal_disable"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          icon="el-icon-search"
          size="mini"
          type="primary"
          @click="handleQuery"
        >搜索
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
            <el-button
                  type="danger"
                  plain
                  icon="el-icon-delete"
                  size="mini"
                  :disabled="multiple"
                  @click="handleDelete"
                  v-hasPermi="['system:tenant:remove']"
                >删除</el-button>
      </el-col>

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="postList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="租户ID" prop="tenant"/>
      <el-table-column align="center" label="数据库连接URL" prop="url" width="300"/>
      <el-table-column align="center" label="数据库名" prop="databaseName"/>
      <el-table-column align="center" label="用户名" prop="username"/>

      <el-table-column align="center" label="数据库主机名" prop="hostName"/>

      <el-table-column
        align="center"
        label="到期日期"
        prop="expirationDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="center"
        class-name="small-padding fixed-width"
        label="操作"
      >
        <template slot-scope="scope">
              <el-button
            v-hasPermi="['system:tenant:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button
          >
          <el-button
            v-hasPermi="['system:tenant:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改租户对话框 -->
    <el-dialog
      :close-on-click-modal="false"
      :title="title"
      :visible.sync="open"
      append-to-body
      width="500px"
    >

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="租户ID" prop="tenant">
          <el-input v-model="form.tenant" placeholder="请输入租户ID"/>
        </el-form-item>
        <el-form-item label="到期日期">
          <el-date-picker v-model="form.expirationDate" clearable placeholder="选择日期" size="small" type="date"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listPost,
  getPost,
  delPost,
  addPost,
  updatePost,
} from '@/api/system/tenant'

export default {
  name: 'Post',
  dicts: ['sys_normal_disable'],
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
      // 租户表格数据
      postList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        postCode: undefined,
        postName: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        postName: [
          {required: true, message: '租户名称不能为空', trigger: 'blur'},
        ],
        postCode: [
          {required: true, message: '租户编码不能为空', trigger: 'blur'},
        ],
        postSort: [
          {required: true, message: '租户顺序不能为空', trigger: 'blur'},
        ],
      },
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询租户列表 */
    getList() {
      this.loading = true
      listPost(this.queryParams).then((response) => {
        this.postList = response.rows
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
        postId: undefined,
        postCode: undefined,
        postName: undefined,
        postSort: 0,
        status: '0',
        remark: undefined,
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
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加租户'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getPost(id).then((response) => {
        this.form = response.data
        this.open = true
        this.title = '修改租户'
      })
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id != undefined) {
            updatePost(this.form).then((response) => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addPost(this.form).then((response) => {
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
      const tenatIds = row.id || this.ids
      this.$modal
        .confirm('是否确认删除租户号为"' + tenatIds + '"的数据项？')
        .then(function () {
          return delPost(tenatIds)
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
        'system/tenant/export',
        {
          ...this.queryParams,
        },
        `post_${new Date().getTime()}.xlsx`
      )
    },
  },
}
</script>
