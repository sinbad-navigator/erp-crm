<template>
  <!-- 添加或修改库存销售订单对话框 -->
  <el-dialog :title="title" :visible.sync="visible" append-to-body width="1200px" @close="$emit('close')">
    <el-card lass="box-card">
      <div slot="header" class="clearfix">
        <span>基础信息</span>
        <el-button v-show="edit" style="float: right; padding: 3px 0" type="text">确认</el-button>
        <el-button v-show="readOnly" style="float: right; padding: 3px 0" type="text">编辑</el-button>
      </div>

      <el-form ref="form" :inline="true" :model="form">
        <el-row justify="space-between" type="flex">
          <el-col :span="6">
            <el-form-item label="客户姓名" label-width="auto" prop="clientNickname">
              <el-autocomplete
                v-model="clientInfo.clientNickname"
                :disabled="readOnly"
                :fetch-suggestions="queryClientNameAsync"
                :trigger-on-focus="false"
                placeholder="请输入客户姓名"
                size="small"
                style="width:218px"
                @select="handleSelect"
              ></el-autocomplete>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="客户手机号" label-width="auto" prop="clientPhone">
              <el-input v-model="clientInfo.clientPhone" :disabled="readOnly" placeholder="请输入客户手机号" size="small"
                        style="width:169px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="送货地址" label-width="auto" prop="shippingAddress">
              <el-input v-model="clientInfo.shippingAddress" :disabled="readOnly" max="100" placeholder="请输入送货地址"
                        size="small" style="width:342px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="实付金额" label-width="auto" prop="actualPayment">
              <el-input v-model="form.actualPayment" :disabled="readOnly" placeholder="请输入实付金额" size="small"
                        style="width:125px"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row justify="space-between" type="flex">
          <el-col :span="6">
            <el-form-item label="订单名称" label-width="auto" prop="orderName">
              <el-input v-model="form.orderName" :disabled="readOnly" placeholder="请输入订单名称" size="small"
                        style="width:218px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="经手人" label-width="auto" prop="handlerName">
              <el-input v-model="form.handlerName" :disabled="readOnly" placeholder="请输入经手人" size="small"
                        style="width:169px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="9">
            <el-form-item label="订单备注" label-width="auto" prop="remark">
              <el-input v-model="form.remark" :disabled="readOnly" placeholder="请输入备注" size="small"
                        style="width:342px"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="开票" label-width="auto" style="display: flex; flex-direction: column;">
              <el-switch v-model="form.taxNeed" :disabled="readOnly"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>

    <div style="margin-top: 10px"></div>

    <transition name="fade">
      <el-card v-show="form.taxNeed" lass="box-card">
        <div slot="header" class="clearfix">
          <span>开票信息</span>
          <el-button v-show="edit" style="float: right; padding: 3px 0" type="text">确认</el-button>
          <el-button v-show="readOnly" style="float: right; padding: 3px 0" type="text">编辑</el-button>
        </div>

        <el-form ref="form" :inline="true" :model="taxInfo" label-width="auto">
          <el-row justify="space-between" type="flex">
            <el-col :span="6">
              <el-form-item label="纳税人识别号" prop="taxpayerId">
                <el-input v-model="taxInfo.taxpayerId" :disabled="readOnly" placeholder="请输入纳税人识别号" size="small"
                          style="width:170px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="公司名称" prop="companyName">
                <el-input v-model="taxInfo.companyName" :disabled="readOnly" placeholder="请输入公司名称" size="small"
                          style="width:250px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="公司地址" prop="companyAddress">
                <el-input v-model="taxInfo.companyAddress" :disabled="readOnly" placeholder="请输入公司地址" size="small"
                          style="width:340px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row justify="space-between" type="flex">
            <el-col :span="6">
              <el-form-item label="联系电话" prop="contactTel">
                <el-input v-model="taxInfo.contactTel" :disabled="readOnly" placeholder="请输入联系电话" size="small"
                          style="width:170px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="银行账户" label-width="auto" prop="bankAccount">
                <el-input v-model="taxInfo.bankAccount" :disabled="readOnly" placeholder="请输入银行账户" size="small"
                          style="width:250px"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="开户行" label-width="auto" prop="depositBank">
                <el-input v-model="taxInfo.depositBank" :disabled="readOnly" placeholder="请输入开户行" size="small"
                          style="width:340px"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>
      </el-card>
    </transition>

    <div style="margin-top: 10px"></div>

    <el-card lass="box-card">
      <div slot="header" class="clearfix">
        <span>产品清单</span>
        <el-button v-show="edit" style="float: right; padding: 3px 0" type="text">确认</el-button>
        <el-button v-show="readOnly" style="float: right; padding: 3px 0" type="text">编辑</el-button>
      </div>

      <el-form v-show="create || edit" ref="form" :inline="true" :model="productQueryParams"
               label-position="left" style="display: flex; justify-content: space-evenly;">
        <el-form-item label="产品名称" label-width="auto" prop="productName">
          <el-autocomplete
            v-model="productQueryParams.productName"
            :fetch-suggestions="queryProductAsync"
            :trigger-on-focus="false"
            placeholder="支持标签(使用空格分割)"
            size="small"
            style="width:218px"
            @select="handleProductNameSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="产品型号" label-width="auto" prop="productModel">
          <el-input v-model="productQueryParams.productModel" placeholder="请输入产品型号" size="small"
                    style="width:218px"></el-input>
        </el-form-item>
        <el-form-item label="产品数量" label-width="auto" prop="productAmount">
          <el-input v-model.number="productQueryParams.productAmount" placeholder="请输入产品数量" size="small"
                    style="width:218px"></el-input>
        </el-form-item>
        <!-- <el-form-item label="产品备注" prop="productRemark" label-width="auto">
          <el-input v-model="form.productRemark" placeholder="请输入产品备注" size="small"></el-input>
        </el-form-item> -->
        <el-form-item>
          <el-button
            v-hasPermi="['erp:order:add']"
            icon="el-icon-plus"
            plain
            size="small"
            type="primary"
            @click="handleAdd"
          >新增
          </el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="productList">
        <el-table-column align="center" label="名称" prop="productName"></el-table-column>
        <el-table-column align="center" label="型号" prop="productModel"></el-table-column>
        <el-table-column align="center" label="数量" prop="productAmount">
          <template slot-scope="scope">{{scope.row.productAmount}} {{scope.row.productUnit}}</template>
        </el-table-column>
        <el-table-column align="center" label="金额" prop="productPrice">
          <template slot-scope="scope">{{scope.row.productPrice | toFixed(2)}}</template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="50">
          <template slot-scope="scope">
            <el-button size="small" type="text" @click="handleItemDeleteClick(scope.row)">删除</el-button>
            <!-- <el-button type="text" size="small">编辑</el-button> -->
          </template>
        </el-table-column>

        <el-table-column fixed="right" label="" width="60">
          <template slot-scope="scope">
            <el-button size="small" type="text" @click="handleItemDownClick(scope.row)">
              <i class="el-icon-arrow-down"/>
            </el-button>
            <el-button size="small" type="text" @click="handleItemUpClick(scope.row)">
              <i class="el-icon-arrow-up"/>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin-top: 30px"></div>
      <h2>合计：{{totalPrice | toFixed(2)}}</h2>
    </el-card>

    <div v-show="edit || create" slot="footer" class="dialog-footer">
      <el-button @click="restForm">清 除</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {listClient} from '@/api/erp/client'
import {querPorductByTags} from '@/api/erp/product'
import {addOrder, getOrder} from '@/api/erp/order'
import Vue from "vue"

export default {
  name: "order-dialog",
  props: ['visible', 'title', 'mode', 'orderId'],
  data() {
    return {
      // 产品清单表格数据
      productList: [],
      // 表单参数
      form: {
        taxNeed: false
      },
      // 客户信息
      clientInfo: {},
      // 开票信息
      taxInfo: {},
      // 产品查询参数
      productQueryParams: {
        productName: "",
        productAmount: null
      },
      // 列表加载动画
      loading: false
    }
  },
  methods: {
    async getOrderDetailInfo(id) {
      this.loading = true
      let response = await getOrder(id)
      this.loading = false
      this.clientInfo = response.data.clientInfo
      this.taxInfo = response.data.taxInfo
      this.productList = response.data.productList
      this.form.taxNeed = response.data.taxNeed == 1
      this.form.orderName = response.data.orderName
      this.form.handlerName = response.data.handlerName
      this.form.remark = response.data.remark
      this.form.actualPayment = parseFloat(response.data.actualPayment).toFixed(2)
    },
    async queryProductAsync(queryString, cb) {
      let tags = queryString.split(" ").map(item => new Object({tagName: item}))
      let response = await querPorductByTags({tags: tags});
      let result = response.data.map(item => new Object({value: item.productName, info: item}))
      result.sort((a, b) => a.info.num - b.info.num)
      console.log(result)
      cb(result)
    },
    handleProductNameSelect(item) {
      console.log(item)
      this.productQueryParams.productName = item.value
      this.productQueryParams.productModel = item.info.productModel
      this.productQueryParams.productAmount = 1
      this.productQueryParams.info = item.info
    },
    async queryClientNameAsync(queryString, cb) {
      let response = await listClient({clientNickname: queryString})
      let result = response.rows.map(item => new Object({value: item.clientNickname, info: item}))
      cb(result)
    },
    handleSelect(item) {
      console.log(item)
      this.clientInfo.clientNickname = item.value
      this.clientInfo.id = item.info.id
      this.clientInfo.clientPhone = item.info.clientPhone
      this.clientInfo.shippingAddress = item.info.shippingAddress
    },
    handleAdd() {
      let productAmount = this.productQueryParams.productAmount
      let info = Object.assign({}, this.productQueryParams.info) // 拷贝产品信息为独立实体
      this.productList.push(Object.assign(info, {
        productAmount: productAmount,
        productPrice: productAmount * info.productPrice
      }))
    },
    handleItemDeleteClick(e) {
      let index = this.productList.findIndex(item => Object.is(e, item))
      this.productList.splice(index, 1)
    },
    handleItemDownClick(e) {
      let index = this.productList.findIndex(item => Object.is(e, item))
      if (this.productList.length == 1 || index == this.productList.length - 1) return
      let target = Object.assign({}, this.productList[index + 1])
      Vue.set(this.productList, index + 1, e) // 修改目标下标对象引用为当前对象
      Vue.set(this.productList, index, target) // 修改原下标对象引用为目标对象
    },
    handleItemUpClick(e) {
      let index = this.productList.findIndex(item => Object.is(e, item))
      if (this.productList.length == 1 || index == 0) return
      let target = Object.assign({}, this.productList[index - 1])
      Vue.set(this.productList, index - 1, e) // 修改目标下标对象引用为当前对象
      Vue.set(this.productList, index, target) // 修改原下标对象引用为目标对象
    },
    async submitForm() {
      this.loading = true
      this.mode = 1
      let response = await addOrder(Object.assign(this.form, {
        totalPrice: this.totalPrice,
        clientInfo: this.clientInfo,
        productList: this.productList,
        taxInfo: this.taxInfo,
        taxNeed: this.form.taxNeed ? 1 : 0
      }))
      this.restForm()
      this.mode = 0
      this.loading = false
      console.log(response)
    },
    restForm() {
      this.form = {taxNeed: false}
      this.clientInfo = {}
      this.taxInfo = {}
      this.productList = []
      this.productQueryParams = {
        productName: "",
        productAmount: null
      }
    }
  },
  computed: {
    totalPrice() {
      if (this.productList.length > 0) return this.productList.map(item => item.productPrice).reduce((prev, cur, index, arr) => prev + cur)
    },
    readOnly() {
      return this.mode == 1
    },
    edit() {
      return this.mode == 2
    },
    create() {
      return this.mode == 0
    }
  },
  watch: {
    visible(newVal, oldVal) {
      console.log(`newVal: ${newVal}, oldVal: ${oldVal}`)
      // 打开时
      if (newVal == true && oldVal == false) {
        if (this.mode == 1) this.getOrderDetailInfo(this.orderId)
      }
    }
  }
}
</script>

<style lang="scss">
.el-dialog__close {
  padding: 5px;
  transition: background-color .2s;
  border-radius: 10%;

  &:hover {
    background: #E81123;

    &::before {
      color: #fff;
    }
  }
}

</style>
