<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :lg="24" :sm="24">
        <blockquote class="text-warning" style="font-size: 14px">

          <br/>
          <el-link
            href=""
            target="_blank"
            type="primary"
          >
          </el-link
          >
          <br/>

          <br/>
          <el-link
            href=""
            target="_blank"
            type="primary"
          >
          </el-link
          >
          <br/>

          <el-link href="" target="_blank" type="primary"
          >
          </el-link
          >
          &nbsp;&nbsp;
          <el-link href="" target="_blank" type="primary"
          >
          </el-link
          >
          <br/>
          <h4 class="text-danger">

          </h4>
        </blockquote>

        <hr/>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :lg="12" :sm="24" style="padding-left: 20px">
        <h2>ERP-CRM一体化管理系统</h2>
        <p>
          ERP-CRM一体化管理系统,SaaS化实现
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger"></el-tag>
        </p>
        <p>
          <el-button
            icon="el-icon-cloudy"
            plain
            size="mini"
            type="primary"
            @click="goTarget('')"
          >
          </el-button
          >
          <el-button
            icon="el-icon-s-home"
            plain
            size="mini"
            @click="goTarget('')"
          >
          </el-button
          >
        </p>
      </el-col>

      <el-col :lg="12" :sm="24" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2></h2>
          </el-col>
        </el-row>
        <el-row>

        </el-row>
      </el-col>
    </el-row>
    <el-divider/>
    <el-row :gutter="20">
      <el-col :lg="8" :md="12" :sm="24" :xs="24">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span></span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i>
              <el-link
                href=""
                target="_blank"
              >
              </el-link
              >
            </p>
            <p>

            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> <a
              href="javascript:;"
            >/ *</a
            >
            </p>
            <p>
              <i class="el-icon-money"></i> <a
              class=""
              href="javascript:;"
            >/ *</a
            >
            </p>
          </div>
        </el-card>
      </el-col>

      <el-col :lg="8" :md="12" :sm="24" :xs="24">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span></span>
          </div>
          <div class="body">
            <img
              alt="donate"
              src=""
              width="100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
            ></span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.9.2",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style lang="scss" scoped>
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

