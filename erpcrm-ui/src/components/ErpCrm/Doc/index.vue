<template>
  <div>
    <svg-icon icon-class="question" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'ErpCrmDoc',
  data() {
    return {
      url: 'http://doc.erpcrm.vip/erpcrm-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
