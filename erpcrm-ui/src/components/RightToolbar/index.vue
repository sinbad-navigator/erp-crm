<template>
  <div class="top-right-btn">
    <el-row>
      <el-tooltip
        :content="showSearch ? '隐藏搜索' : '显示搜索'"
        class="item"
        effect="dark"
        placement="top"
      >
        <el-button
          circle
          icon="el-icon-search"
          size="mini"
          @click="toggleSearch()"
        />
      </el-tooltip>
      <el-tooltip class="item" content="刷新" effect="dark" placement="top">
        <el-button
          circle
          icon="el-icon-refresh"
          size="mini"
          @click="refresh()"
        />
      </el-tooltip>
      <el-tooltip
        v-if="columns"
        class="item"
        content="显隐列"
        effect="dark"
        placement="top"
      >
        <el-button
          circle
          icon="el-icon-menu"
          size="mini"
          @click="showColumn()"
        />
      </el-tooltip>
    </el-row>
    <el-dialog
      :close-on-click-modal="false"
      :title="title"
      :visible.sync="open"
      append-to-body
    >
      <el-transfer
        v-model="value"
        :data="columns"
        :titles="['显示', '隐藏']"
        @change="dataChange"
      ></el-transfer>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: 'RightToolbar',
  data() {
    return {
      // 显隐数据
      value: [],
      // 弹出层标题
      title: '显示/隐藏',
      // 是否显示弹出层
      open: false,
    }
  },
  props: {
    showSearch: {
      type: Boolean,
      default: true,
    },
    columns: {
      type: Array,
    },
  },
  created() {
    // 显隐列初始默认隐藏列
    for (let item in this.columns) {
      if (this.columns[item].visible === false) {
        this.value.push(parseInt(item))
      }
    }
  },
  methods: {
    // 搜索
    toggleSearch() {
      this.$emit('update:showSearch', !this.showSearch)
    },
    // 刷新
    refresh() {
      this.$emit('queryTable')
    },
    // 右侧列表元素变化
    dataChange(data) {
      for (let item in this.columns) {
        const key = this.columns[item].key
        this.columns[item].visible = !data.includes(key)
      }
    },
    // 打开显隐列dialog
    showColumn() {
      this.open = true
    },
  },
}
</script>
<style lang="scss" scoped>
::v-deep .el-transfer__button {
  border-radius: 50%;
  padding: 12px;
  display: block;
  margin-left: 0px;
}

::v-deep .el-transfer__button:first-child {
  margin-bottom: 10px;
}
</style>
