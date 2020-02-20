<template>
    <div class="rumor-intro">
        <div class="rumor-detail-info">
            <div class="rumor-title">{{rumor.title}}</div>
            <div class="rumor-description">{{rumor.mainSummary}}</div>
        </div>
    </div>
</template>

<script type="text/ecmascript-6">

  export default {
    props: {
        rumor: {
        type: Object
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
.rumor-intro
  .rumor-detail-info
    display: inline-block
    .rumor-title
      display: inline-block
      width: 960px
      vertical-align: top
      color: #fff
      font-weight: 700
      font-size: 16px
      white-space nowrap
      margin: 15px 10px 20px 0
      overflow: hidden
      text-align left
      text-overflow: ellipsis
    .rumor-description
      height: 74px
      margin: 5px 10px 5px 0
      font-size: 14px
      text-align left
      overflow: hidden
      text-overflow: ellipsis
</style>
