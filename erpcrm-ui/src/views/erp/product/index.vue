<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
      <el-form-item label="名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          clearable
          placeholder="请输入名称"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="型号" prop="productModel">
        <el-input
          v-model="queryParams.productModel"
          clearable
          placeholder="请输入型号"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="productUnit">
        <el-input
          v-model="queryParams.productUnit"
          clearable
          placeholder="请输入单位"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销售价格" prop="productPrice">
        <el-input
          v-model="queryParams.productPrice"
          clearable
          placeholder="请输入销售价格"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="productRemark">
        <el-input
          v-model="queryParams.productRemark"
          clearable
          placeholder="请输入备注"
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
          v-hasPermi="['erp:product:export']"
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

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="产品ID" prop="id"/>
      <el-table-column align="center" label="名称" prop="productName"/>
      <el-table-column align="center" label="型号" prop="productModel"/>
      <el-table-column align="center" label="单位" prop="productUnit"/>
      <el-table-column align="center" label="销售价格" prop="productPrice"/>
      <el-table-column align="center" label="备注" prop="productRemark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
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

    <!-- 添加或修改库存产品对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="productName">
          <el-input v-model.trim="form.productName" placeholder="请输入名称"/>
        </el-form-item>
        <el-form-item label="型号" prop="productModel">
          <el-input v-model.trim="form.productModel" placeholder="请输入型号"/>
        </el-form-item>
        <el-form-item label="单位" prop="productUnit">
          <el-input v-model.trim="form.productUnit" placeholder="请输入单位"/>
        </el-form-item>
        <el-form-item label="销售价格" prop="productPrice">
          <el-input v-model.trim="form.productPrice" placeholder="请输入销售价格"/>
        </el-form-item>
        <el-form-item label="备注" prop="productRemark">
          <el-input v-model.trim="form.productRemark" placeholder="请输入备注"/>
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
import {listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct} from "@/api/erp/product";

export default {
  name: "Product",
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
      // 库存产品表格数据
      productList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productName: null,
        productModel: null,
        productUnit: null,
        productPrice: null,
        productRemark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productName: [
          {required: true, message: "请输入产品名称", triger: "blur"}
        ],
        productModel: [
          {required: true, message: "请输入产品型号", triger: "blur"}
        ],
        productUnit: [
          {required: true, message: "请输入产品单位", triger: "blur"}
        ],
        productPrice: [
          {required: true, message: "请输入销售价格", triger: "blur"},
          {
            validator: (rule, value, callback) => {
              if (value.indexOf('.') > 0) {
                if (value.split(".")[1].length < 2) callback(new Error('销售价格只能为两位小数'))
              } else {
                callback()
              }
            },
            triger: "change"
          }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询库存产品列表 */
    getList() {
      this.loading = true;
      listProduct(this.queryParams).then(response => {
        this.productList = response.rows;
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
        productName: null,
        productModel: null,
        productUnit: null,
        productPrice: null,
        productRemark: null
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
      this.title = "添加库存产品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getProduct(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改库存产品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateProduct(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProduct(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除库存产品编号为"' + ids + '"的数据项？').then(function () {
        return delProduct(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$modal.confirm('是否确认导出所有库存产品数据项？').then(() => {
        this.exportLoading = true;
        return exportProduct(queryParams);
      }).then(response => {
        this.$download.name(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    }
  }
};
</script>
