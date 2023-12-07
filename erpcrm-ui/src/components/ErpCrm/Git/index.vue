<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'ErpCrmGit',
  data() {
    return {
      url: 'https://gitee.com/jundee/ErpCrm',
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    },
  },
}
</script>
