<template>
  <div class="app-container">
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList">
      <el-table-column align="center" type="selection" width="55"/>
      <!-- <el-table-column label="销售订单ID" align="center" prop="id" /> -->
      <el-table-column align="center" label="订单名称" prop="orderName"/>
      <!-- <el-table-column label="库存流水ID" align="center" prop="detailId" />
      <el-table-column label="开票信息ID" align="center" prop="taxInfoId" /> -->
      <el-table-column align="center" label="客户姓名" prop="clientName">
        <template slot-scope="scope">{{scope.row.clientInfo.clientNickname}}</template>
      </el-table-column>
      <el-table-column align="center" label="客户手机" prop="clientPhone">
        <template slot-scope="scope">{{scope.row.clientInfo.clientPhone}}</template>
      </el-table-column>
      <el-table-column align="center" label="送货地址" prop="shippingAddress" width="300">
        <template slot-scope="scope">{{scope.row.clientInfo.shippingAddress}}</template>
      </el-table-column>
      <el-table-column align="center" label="经手人姓名" prop="handlerName"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" label="订单金额" prop="totalPrice">
        <template slot-scope="scope">{{scope.row.totalPrice | toFixed(2) }}</template>
      </el-table-column>
      <el-table-column align="center" label="实付金额" prop="actualPayment">
        <template slot-scope="scope">{{scope.row.actualPayment | toFixed(2) }}</template>
      </el-table-column>
      <!-- <el-table-column label="客户ID" align="center" prop="clientId" /> -->
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="handleCheckInfoClick(scope.row)">查看</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['erp:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['erp:order:remove']"
          >删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <order-dialog :mode="mode" :order-id="orderID" :title="title" v-bind:visible="open" @close="cancel"></order-dialog>

  </div>
</template>

<script>
import {listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder} from "@/api/erp/order";
import orderDialog from "./orderDialog.vue"

export default {
  name: "Bussiness",
  components: {
    orderDialog
  },

  data() {
    return {
      // 库存销售订单表格数据
      orderList: [],
      // 是否显示弹出层
      open: false,
      // 订单详情 dialog 模式 创建:0 查看:1
      mode: 0,
      // 订单ID
      orderID: 0
    }
  },

  created() {
    this.getList();
  },

  methods: {
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
    /** 查询库存销售订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      // this.reset();
      this.open = true;
      this.title = "添加库存销售订单";
      this.mode = 0
    },

    handleCheckInfoClick(e) {
      this.open = true;
      this.title = "订单详情";
      this.orderID = e.id
      this.mode = 1
    }
  }
}
</script>

<style>

</style>
