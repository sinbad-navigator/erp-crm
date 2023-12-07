<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
      <el-form-item label="库存流水ID" prop="detailId">
        <el-input
          v-model="queryParams.detailId"
          clearable
          placeholder="请输入库存流水ID"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开票信息ID" prop="taxInfoId">
        <el-input
          v-model="queryParams.taxInfoId"
          clearable
          placeholder="请输入开票信息ID"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="totalPrice">
        <el-input
          v-model="queryParams.totalPrice"
          clearable
          placeholder="请输入订单金额"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实付金额" prop="actualPayment">
        <el-input
          v-model="queryParams.actualPayment"
          clearable
          placeholder="请输入实付金额"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经手人姓名" prop="handlerName">
        <el-input
          v-model="queryParams.handlerName"
          clearable
          placeholder="请输入经手人姓名"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户ID" prop="clientId">
        <el-input
          v-model="queryParams.clientId"
          clearable
          placeholder="请输入客户ID"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['erp:order:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['erp:order:edit']"
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['erp:order:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['erp:order:export']"
          :loading="exportLoading"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="销售订单ID" prop="id"/>
      <el-table-column align="center" label="库存流水ID" prop="detailId"/>
      <el-table-column align="center" label="开票信息ID" prop="taxInfoId"/>
      <el-table-column align="center" label="订单金额" prop="totalPrice"/>
      <el-table-column align="center" label="实付金额" prop="actualPayment"/>
      <el-table-column align="center" label="经手人姓名" prop="handlerName"/>
      <el-table-column align="center" label="客户ID" prop="clientId"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['erp:order:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['erp:order:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改库存销售订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="库存流水ID" prop="detailId">
          <el-input v-model="form.detailId" placeholder="请输入库存流水ID"/>
        </el-form-item>
        <el-form-item label="开票信息ID" prop="taxInfoId">
          <el-input v-model="form.taxInfoId" placeholder="请输入开票信息ID"/>
        </el-form-item>
        <el-form-item label="订单金额" prop="totalPrice">
          <el-input v-model="form.totalPrice" placeholder="请输入订单金额"/>
        </el-form-item>
        <el-form-item label="实付金额" prop="actualPayment">
          <el-input v-model="form.actualPayment" placeholder="请输入实付金额"/>
        </el-form-item>
        <el-form-item label="经手人姓名" prop="handlerName">
          <el-input v-model="form.handlerName" placeholder="请输入经手人姓名"/>
        </el-form-item>
        <el-form-item label="客户ID" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户ID"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
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
import {listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder} from "@/api/erp/order";

export default {
  name: "Manager",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
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
      // 库存销售订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        detailId: null,
        taxInfoId: null,
        totalPrice: null,
        actualPayment: null,
        handlerName: null,
        clientId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询库存销售订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        detailId: null,
        createTime: null,
        taxInfoId: null,
        totalPrice: null,
        actualPayment: null,
        handlerName: null,
        clientId: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加库存销售订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改库存销售订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除库存销售订单编号为"' + ids + '"的数据项？').then(function () {
        return delOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有库存销售订单数据项？').then(() => {
        this.exportLoading = true;
        return exportOrder(queryParams);
      }).then(response => {
        this.$download.name(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
