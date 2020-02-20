<template>
  <div class="newpage-intro">
    <div class="newpage-detail-info">
      <a :href="newpage.link" class="news-title">{{newpage.title}}</a>
      <div class="news-description">{{newpage.description}}</div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    props: {
      newpage: {
        type: Object
      }
    }
  }
</script>

<style lang="stylus" rel="stylesheet/stylus" scoped>
.newpage-intro
  .newpage-detail-info
    display: inline-block
    .news-title
      display: inline-block
      width: 500px
      vertical-align: top
      color: #777777
      font-weight: 700
      font-size: 18px
      white-space: nowrap
      margin: 15px 10px 5px 10px
      overflow: hidden
      text-overflow: ellipsis
    .news-description
      height: 74px
      margin: 5px 0
      font-size: 14px
      text-align left
      overflow: hidden
      text-overflow: ellipsis
</style>
